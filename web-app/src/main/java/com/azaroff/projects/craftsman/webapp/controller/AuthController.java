package com.azaroff.projects.craftsman.webapp.controller;

import com.azaroff.projects.craftsman.authorization.service.Authorization;
import com.azaroff.projects.craftsman.authorization.service.AuthorizationService;
import com.azaroff.projects.craftsman.customer.service.Customer;
import com.azaroff.projects.craftsman.customer.service.CustomerService;
import com.azaroff.projects.craftsman.customer.service.constant.CustomerType;
import com.azaroff.projects.craftsman.token.service.TokenData;
import com.azaroff.projects.craftsman.token.service.TokenService;
import com.azaroff.projects.craftsman.webapp.model.LoginRequest;
import com.azaroff.projects.craftsman.webapp.model.LoginResponse;
import com.azaroff.projects.craftsman.webapp.model.RegisterRequest;
import com.azaroff.projects.craftsman.webapp.model.constant.LoginStatus;
import com.azaroff.projects.craftsman.webapp.model.permission.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by user on 16.10.2017.
 */
@RestController
@RequestMapping("/app-auth")
public class AuthController {

    @Autowired
    @Qualifier("authorizationService")
    private AuthorizationService authorizationService;
    @Autowired
    @Qualifier("tokenService")
    private TokenService tokenService;
    @Autowired
    @Qualifier("customerService")
    private CustomerService customerService;

    @RequestMapping(value = "/sign-in", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String signIn(@RequestBody LoginRequest request) throws JsonProcessingException {
        LoginResponse response = new LoginResponse();
        Authorization authData = authorizationService.findByAuthData(request.getEmail(), request.getPassword());

        if (authData == null) {
            return buildResponse(LoginStatus.NOT_FOUND, null, null, "User Not Found");
        }

        Customer customer = customerService.findById(authData.getCustomerId());

        TokenData tokenData = tokenService.generateToken(UUID.nameUUIDFromBytes(request.getEmail().getBytes()).
                toString(), customer, new DateTime(), true);
        return buildResponse(LoginStatus.ACTIVE, tokenData.getAlias(), customer.getType(), "");
    }

    @RequestMapping(value = "/sign-up", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody RegisterRequest request) throws JsonProcessingException {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(request.getFirstName());
        newCustomer.setLastName(request.getLastName());
        newCustomer.setMiddleName(request.getMiddleName());
        newCustomer.setType(CustomerType.valueOf(request.getCustomerType()));
        Customer createdCustomer = customerService.saveCustomer(newCustomer);

        Authorization authorization = new Authorization();
        authorization.setCustomerId(createdCustomer.getId());
        authorization.setLogin(request.getEmail());
        authorization.setPassword(request.getPassword());
        Authorization authorizationData = authorizationService.addAuthorizationData(authorization);

        TokenData tokenData = tokenService.generateToken(UUID.nameUUIDFromBytes(request.getEmail().getBytes()).
                toString(), createdCustomer, new DateTime(), true);

        return buildResponse(LoginStatus.ACTIVE, tokenData.getAlias(), createdCustomer.getType(), "");
    }

    @RequestMapping(value = "/get-customer-id", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomerId(@RequestBody BaseRequest request) throws JsonProcessingException {

        TokenData tokenData = tokenService.findByAlias(request.getTokenAlias());
        Customer customer = (Customer) tokenData.getData();
        return new ObjectMapper().writeValueAsString(customer.getId());

    }

    private String buildResponse(LoginStatus status, String alias, CustomerType customerType, String message) throws JsonProcessingException {
        LoginResponse response = new LoginResponse();
        response.setStatus(status);
        response.setToken(alias);
        response.setMessage(message);
        response.setCustomerType(customerType);
        return new ObjectMapper().writeValueAsString(response);
    }
}
