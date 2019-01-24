package 注解.V2;

/**
 * Created by shejiewei on 2019/1/23.
 */
/**
 * 用户实体使用注解
 * @author Yuwl
 */
@Table("user")
public class User {

    @Column("id")
    private int id;

    @Column("userName")
    private String userName;

    @Column("sex")
    private int sex;

    @Column("mobile")
    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
