package com.azaroff.projects.craftsman.authorization.datalayer.entity;

import javax.persistence.*;

/**
 * Created by Dmitiy on 28.08.2016.
 */
@Entity
@Table(name = "authorization_data")
public class AuthorizationEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "authorization_data_id_seq", sequenceName = "authorization_data_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorization_data_id_seq")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "login")
//    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "customer_id")
    private int customerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
