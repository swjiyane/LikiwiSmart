package com.example.codetribe.likiwismart;

/**
 * Created by CodeTribe on 7/27/2017.
 */

public class  User {
    private String userName;
    private String nexOfKin1;
    private String nexOfKin2;
    private String cell1;
    private String cell2;
    private String userEmail;
    private String userPassword;

    public User() {
    }

    public User(String userName, String nexOfKin1, String nexOfKin2, String cell1, String cell2, String userEmail, String userPassword) {
        this.userName = userName;
        this.nexOfKin1 = nexOfKin1;
        this.nexOfKin2 = nexOfKin2;
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNexOfKin1() {
        return nexOfKin1;
    }

    public void setNexOfKin1(String nexOfKin1) {
        this.nexOfKin1 = nexOfKin1;
    }

    public String getNexOfKin2() {
        return nexOfKin2;
    }

    public void setNexOfKin2(String nexOfKin2) {
        this.nexOfKin2 = nexOfKin2;
    }

    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}