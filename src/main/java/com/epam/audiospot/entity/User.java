package com.epam.audiospot.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Entity, Serializable {
    private final Long id;
    private final Long playlistId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private BigDecimal account;
    private Role role;

    public User(Long id, String name, String surname, String login, String password,
                BigDecimal account, Role role, Long playlistId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.account = account;
        this.role = role;
        this.playlistId = playlistId;
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

    public Long getPlaylistId() {
        return playlistId;
    }
}
