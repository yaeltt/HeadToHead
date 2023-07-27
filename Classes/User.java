package project.classes;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String userName;
    private String password;
    private int points;
    

    public User( int id,String userName, String password, int points) {
        this.id=id;
        this.userName = userName;
        this.password = password;
        this.points = points;
    }

    public User() {
    }
    

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}
