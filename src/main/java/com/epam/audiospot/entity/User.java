package com.epam.audiospot.entity;

import java.io.Serializable;

public class User implements Entity, Serializable {
    private final String login;
    private final String role;

    public User(String login,String role){
        this.login = login;
        this.role = role;
    }

//    public User(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }
}
