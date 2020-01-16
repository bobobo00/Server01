import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Auther danni
 * @Date 2020/1/5 21:16]
 * @Version 1.0
 **/

public class User implements Serializable {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
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
}
