package com.exuberant.code2create.models;

import java.util.List;

public class UserModel {

    List<User> userList;

    public UserModel(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
