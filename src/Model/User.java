package Model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int userId;
    private String nickName;
    private String email;
    private String password;
    private float money;
    private boolean isLogged;

    public User(int userId, String nickName, String email, String password, float money, boolean isLogged) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.money = money;
        this.isLogged = isLogged;
    }

    public User(String nickName, String email, String password, float money, boolean isLogged) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.money = money;
        this.isLogged = isLogged;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    @Override
    public String toString() {
        return "'" + nickName + '\'' +
                ", '" + email + '\'' +
                ", '" + password + '\'' +
                ", " + money +
                ", " + isLogged;
    }
}
