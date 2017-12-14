package com.azaroff.projects.craftsman.exception;

import com.azaroff.projects.craftsman.webapp.model.constant.LoginStatus;

/**
 * Copyright DonRiver Inc. All Rights Reserved.
 * <p>
 * Author: Dmitry Azarov
 * Created: 05.12.2017.
 */
public class DAOException extends RuntimeException {

    private String message = null;
    private LoginStatus status = null;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
        this.message = message;
    }

    public DAOException(LoginStatus status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return status + " " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public LoginStatus getStatus() {
        return status;
    }
}
