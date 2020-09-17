package sink.mysql_sink;

/**
 * Created by shejiewei on 2020/5/11.
 */
public class Student {
   //id,name,password,age
    String id;
    String name;
    String password;
    String age;

    public Student(String id, String name, String password, String age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
