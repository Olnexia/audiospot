package com.epam.audiospot.entity;

public class User implements Entity {
    private static final long serialVersionUID = 646513000665422990L;

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
    private Role role;
    private boolean active;
    private float discount;

    public User(Long id, String name, String surname, String login, String password,
                boolean active, float discount, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.active = active;
        this.discount = discount;
        this.role = role;
    }

    public static User client(String login, String name, String surname, String password) {
        return new User(null, name, surname, login, password, true, 0, Role.CLIENT);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        User user = (User) object;
        return id.equals(user.getId())
                && name.equals(user.getName())
                && surname.equals(user.getSurname())
                && login.equals(user.getLogin())
                && password.equals(user.getPassword())
                && active == user.isActive()
                && discount == user.getDiscount()
                && role.equals(user.getRole());
    }

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + name.hashCode();
        result = prime * result + surname.hashCode();
        result = prime * result + login.hashCode();
        result = prime * result + password.hashCode();
        result = prime * result + Boolean.hashCode(active);
        result = prime * result + Float.hashCode(discount);
        result = prime * result + role.hashCode();
        return result;
    }

    @Override
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

    public float getDiscount() {
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

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
