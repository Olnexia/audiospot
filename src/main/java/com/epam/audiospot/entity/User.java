package com.epam.audiospot.entity;

import java.io.Serializable;

public class User implements Entity, Serializable {
    public static final String ID_LABEL = "user_id";
    public static final String NAME_LABEL = "name";
    public static final String SURNAME_LABEL = "surname";
    public static final String LOGIN_LABEL = "login";
    public static final String PASSWORD_LABEL = "password";
    public static final String ACTIVE_LABEL = "active";
    public static final String DISCOUNT_LABEL = "discount";
    public static final String ROLE_LABEL = "role";

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private boolean active;
    private int discount;
    private Role role;

    public User(Long id, String name, String surname, String login, String password,
                boolean active, int discount, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.active = active;
        this.discount = discount;
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

    public int getDiscount() {
        return discount;
    }

    public boolean isActive() {
        return active;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
