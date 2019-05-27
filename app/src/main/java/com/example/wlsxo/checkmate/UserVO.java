package com.example.wlsxo.checkmate;

import android.graphics.Bitmap;

public class UserVO {

    private String userID;
    private String userPassword;
    private String userName;
    private String userAge;
    private String userDept;
    private String userHobby;
    private String userHeight;
    private String userBody;
    private String userCharm;
    private String userKakao;
    private String userSex;
    private String userArea;
    private Bitmap userPicture;

    public UserVO() {
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getUserHobby() {
        return userHobby;
    }

    public void setUserHobby(String userHobby) {
        this.userHobby = userHobby;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserBody() {
        return userBody;
    }

    public void setUserBody(String userBody) {
        this.userBody = userBody;
    }

    public String getUserCharm() {
        return userCharm;
    }

    public void setUserCharm(String userCharm) {
        this.userCharm = userCharm;
    }

    public String getUserKakao() {
        return userKakao;
    }

    public void setUserKakao(String userKakao) {
        this.userKakao = userKakao;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }

    public Bitmap getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(Bitmap userPicture) {
        this.userPicture = userPicture;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userID='" + userID + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userAge='" + userAge + '\'' +
                ", userDept='" + userDept + '\'' +
                ", userHobby='" + userHobby + '\'' +
                ", userHeight='" + userHeight + '\'' +
                ", userBody='" + userBody + '\'' +
                ", userCharm='" + userCharm + '\'' +
                ", userKakao='" + userKakao + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userArea='" + userArea + '\'' +
                ", userPicture=" + userPicture +
                '}';
    }
}
