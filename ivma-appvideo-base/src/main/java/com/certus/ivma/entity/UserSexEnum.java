package com.certus.ivma.entity;

/**
 * Created by 123 on 2019/2/21.
 */
public class UserSexEnum {

    private String userSex;
    private String nickName;

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "UserSexEnum{" +
                "userSex='" + userSex + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
