package com.azaroff.projects.craftsman.authorization.service;

/**
 * Created by AzarovD on 25.08.2016.
 */

public class Authorization {

    private String email;
    private String password;
    private int customerId;

    public String getLogin() {
        return email;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
