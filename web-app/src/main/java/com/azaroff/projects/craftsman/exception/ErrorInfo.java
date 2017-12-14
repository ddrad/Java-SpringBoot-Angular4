package com.azaroff.projects.craftsman.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Copyright DonRiver Inc. All Rights Reserved.
 * <p>
 * Author: Dmitry Azarov
 * Created: 05.12.2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorInfo {

    private String code;
    private String message;

    public ErrorInfo() {
    }

    public ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
