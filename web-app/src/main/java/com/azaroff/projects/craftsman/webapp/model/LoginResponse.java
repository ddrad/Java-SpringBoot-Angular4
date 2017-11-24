package com.azaroff.projects.craftsman.webapp.model;

import com.azaroff.projects.craftsman.customer.service.constant.CustomerType;
import com.azaroff.projects.craftsman.webapp.model.constant.LoginStatus;

/**
 * Created by user on 16.10.2017.
 */
public class LoginResponse {
    private LoginStatus status;
    private String token;
    private CustomerType customerType;
    private String message;


    public LoginStatus getStatus() {
        return status;
    }

    public void setStatus(LoginStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
