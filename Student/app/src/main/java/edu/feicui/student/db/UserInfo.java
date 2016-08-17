package edu.feicui.student.db;

/**
 * Created by DELL on 2016/6/29.
 */
public class UserInfo {

    private String username;
    private String password;
    private String power;

    public UserInfo(String username, String password, String power) {
        this.username = username;
        this.password = password;
        this.power = power;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
