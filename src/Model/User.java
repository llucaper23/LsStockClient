package Model;

import java.io.Serializable;

public class User implements Serializable {
    private String nickName;
    private String password;
    private String email;
    private int money;
    private int user_id;

    public User(String nickName, String password, String email, int money, int user_id) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.money = money;
        this.user_id = user_id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
