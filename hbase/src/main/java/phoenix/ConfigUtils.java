package phoenix;

/**
 * Created by shejiewei on 2019/8/21.
 */
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {
    public static Properties p = new Properties();

    static {
        try {
            p.load(ClassLoader.getSystemResourceAsStream("phoenix.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getDriver() throws IOException {
        p.load(ClassLoader.getSystemResourceAsStream("phoenix.properties"));
        return p.getProperty("phoenix.driver");
    }

    public static String getUrl() {
        return p.getProperty("phoenix.url");
    }

    public static String getUserName() {
        return p.getProperty("phoenix.user");
    }

    public static String getPassWord() {
        return p.getProperty("phoenix.password");
    }

    public static void main(String[] args) throws IOException {


        System.out.println(getDriver());
        System.out.println(getUrl());
        System.out.println(getUserName());
        System.out.println(getPassWord());
    }
}
