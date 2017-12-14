package com.azaroff.projects.craftsman.exception;

import com.azaroff.projects.craftsman.webapp.model.constant.LoginStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright DonRiver Inc. All Rights Reserved.
 * <p>
 * Author: Dmitry Azarov
 * Created: 05.12.2017.
 */

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ DAOException.class })
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        DAOException de = (DAOException) e;

        ErrorInfo error = new ErrorInfo(de.getStatus().name(), de.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error,  headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({ ControllerException.class })
    protected ResponseEntity<Object> handleJsonProcessingException(RuntimeException e, WebRequest request) {
        ControllerException ce = (ControllerException) e;

        ErrorInfo error = new ErrorInfo(ce.getStatus().name(), ce.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error,  headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
