package com.epam.audiospot.entity;

public class User implements Entity {
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
