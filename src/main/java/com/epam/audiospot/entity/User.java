package com.epam.audiospot.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Entity, Serializable {
    public static final String ID_LABEL = "user_id";
    public static final String NAME_LABEL = "name";
    public static final String SURNAME_LABEL = "surname";
    public static final String LOGIN_LABEL = "login";
    public static final String PASSWORD_LABEL = "password";
    public static final String ACCOUNT_LABEL = "account";
    public static final String ROLE_LABEL = "role";

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private BigDecimal account;
    private Role role;

    public User(Long id, String name, String surname, String login, String password,
                BigDecimal account, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.account = account;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
