import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivilegedExceptionAction;


public class  HdfsApi {
    // initialization
    //读取配置文件
    static Configuration conf = new Configuration();
    static FileSystem hdfs;

    static {
//root是你主节点虚机的用户名
        UserGroupInformation ugi = UserGroupInformation
                .createRemoteUser("root");
        try {
            ugi.doAs(new PrivilegedExceptionAction<Void>() {
                public Void run() throws Exception {
                    Configuration conf = new Configuration();
//"hdfs://lyz01:9000/"对应的是你自己的网址
                    conf.set("fs.default.name", "hdfs://192.168.93.131:8020/");
                    //conf.set("hadoop.job.ugi", "root");
                    //以下两行是支持 hdfs的追加 功能的：hdfs.append()
                    conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
                    conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
                    Path path = new Path("hdfs://192.168.93.131:8020/");
                    //如果在本地测试，需要使用此种方法获取文件系统
                    hdfs = FileSystem.get(path.toUri(), conf);
                    //hdfs = path.getFileSystem(conf); // 这个也可以
                    //如果在Hadoop集群下运行，使用此种方法可以直接获取默认文件系统
                    //hdfs = FileSystem.get(conf); //这个不行，这样得到的hdfs所有操作都是针对本地文件系统，而不是针对hdfs的，原因不太清楚
                    return null;
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 创建hdfs目录
    @Test
    public void createDir() throws IOException {
        String dir = "/test4/";
        Path path = new Path(dir);
        if (hdfs.exists(path)) {
            System.out.println("dir \t" + conf.get("fs.default.name") + dir
                    + "\t already exists");
            return;
        }
        hdfs.mkdirs(path);
        System.out.println("new dir \t" + conf.get("fs.default.name") + dir);
    }

    // 文件重命名
    @Test
    public void renameFile() throws IOException{
        String oldName = "/reduceJoin/2.txt";
        String newName = "/reduceJoin/tb_b.txt";
        Path oldPath = new Path(oldName);
        Path newPath = new Path(newName);
        if (hdfs.exists(oldPath)){
            hdfs.rename(oldPath,newPath);
            System.out.println("rename成功！");
        }else{
            System.out.println("文件不存在!rename失败!");
        }
    }

    // 读取文件
    @Test
    public void readFile() throws IOException{
        String uri = "/output2017_11_12_12_57_04/part-r-00000";
        //判断文件是否存在
        if(!hdfs.exists(new Path(uri))){
            System.out.println("Error ; the file not exists.");
            return;
        }
        InputStream in = null;
        try {
            in = hdfs.open(new Path(uri));
            //BufferedReader bf =new BufferedReader(new InputStreamReader(in,"GB2312"));//防止中文乱码
            //复制到标准输出流
            IOUtils.copyBytes(in, System.out, 4096,false);
            /*String line = null;
            while((line = bf.readLine()) != null){
                System.out.println(line);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            IOUtils.closeStream(in);
        }
    }

    // 从本地往HDFS上传文件
    @Test
    public void copyFile() throws IOException {
        String localSrc = "D:/group_max.txt";
        String hdfsDst = "/group/";
        Path src = new Path(localSrc);
        Path dst = new Path(hdfsDst);
        //本地文件不存在
        if (!(new File(localSrc)).exists()) {
            System.out.println("Error: local dir \t" + localSrc
                    + "\t not exists.");
            return;
        }
        //hdfs路径不存在
        if (!hdfs.exists(dst)) {
            System.out.println("Error: dest dir \t" + dst.toUri()
                    + "\t not exists.");
            return;
        }
        String dstPath = dst.toUri() + "/" + src.getName();
        //System.out.println(dstPath);//   "/test1/3931.jpg"
        //判断上传的文件 hdfs的目录下是否存在
        if (hdfs.exists(new Path(dstPath))) {
            System.out.println("Warn: dest file \t" + dstPath
                    + "\t already exists.");
        }else{
            //本地文件上传hdfs
            hdfs.copyFromLocalFile(src, dst);
            // list all the files in the current direction
            //遍历文件
            FileStatus files[] = hdfs.listStatus(dst);
            System.out.println("Upload to \t" + conf.get("fs.default.name")
                    + hdfsDst);
            for (FileStatus file : files) {
                System.out.println(file.getPath());
            }
        }
    }

    // 从HDFS 下载文件 到本地
    @Test
    public void downloadFile() throws IllegalArgumentException,IOException{
        String hdfsDst = "/test2/2_1";
        String localSrc = "D:/hadfs";
        Path dst = new Path(hdfsDst);
        Path src = new Path(localSrc);
        //本地的路径 + hdfs下载的文件名
        String localFile = localSrc + "/" + dst.getName();
        //如果HDFS路径不存在
        if(!hdfs.exists(dst.getParent())){
            System.out.println("Error : the HDFS directory:\t" + dst.getParent() + "\tdoes not exist. Please check it!");
            return;
        }
        //如果本地目录不存在，则创建
        if(!new File(localSrc).exists()){
            new File(localSrc).mkdirs();
            System.out.println("Warn : The local directory does not exist. It has been automatically created for you!");
        }
        // 如果本地文件存在
        if(new File(localFile).exists()){
            System.out.println("Error : the localSrc: \t" + localFile + "\t already exists.");
            return;
        }
        //如果HDFS文件不存在
        if(!hdfs.exists(new Path(hdfsDst))){
            System.out.println("Error : the HDFS file: \t" + hdfsDst + "\t not exists.");
        }else{
            //HDFS下载文件到本地
            hdfs.copyToLocalFile(false,dst,src,true);
            System.out.println("successful ：download successful! please look at: \t" + localSrc);
        }
    }


    // create a new file
    @Test
    public void createFile()
            throws IOException {
        String fileName = "/test3/b.txt";
        String fileContent = "";
        Path dst = new Path(fileName);
        //判断 新建的文件在hdfs上是否存在
        if(hdfs.exists(dst)){
            System.out.println("Error : the hdfs file exists.");
        }else {
            byte[] bytes = fileContent.getBytes();
            FSDataOutputStream output = hdfs.create(dst);
            output.write(bytes);
            System.out.println("new file \t" + conf.get("fs.default.name")
                    + fileName);
        }
    }

    // 追加内容到文件
    @Test
    public void appendFile()
            throws IOException {
        String fileName = "/test2/file2.txt";
        String fileContent = "你好 世界";
        Path dst = new Path(fileName);
        byte[] bytes = fileContent.getBytes();
        //如果文件不存在
        if (!hdfs.exists(dst)) {
            System.out.println("Error : the file not exists");
            return;
        }
        FSDataOutputStream output = hdfs.append(dst);
        output.write(bytes);
        System.out.println("successful: append to file \t" + conf.get("fs.default.name")
                + fileName);
    }


    // 列出所有文件
    @Test
    public void listFiles() throws IOException {
        String dirName = "/test1";
        Path f = new Path(dirName);
        FileStatus[] status = hdfs.listStatus(f);
        System.out.println(dirName + " has all files:");
        if (status.length == 0) {
            System.out.println("nothing !");
        } else {
            for (int i = 0; i < status.length; i++) {
                System.out.println(status[i].getPath().toString());
            }
        }
    }

    // 判断文件是否存在，存在即删除
    @Test
    public void deleteFile() throws IOException {
        String fileName = "/test2";
        Path f = new Path(fileName);
        boolean isExists = hdfs.exists(f);
        if (isExists) { // if exists, delete
            boolean isDel = hdfs.delete(f, true);
            System.out.println(fileName + "  delete? \t" + isDel);
        } else {
          //  System.out.println(fileName + "  exist? \t" + notExists);
        }
    }
}
