package com.azaroff.projects.craftsman.customer.datalayer.entity;

/**
 * Created by AzarovD on 25.08.2016.
 */

import com.azaroff.projects.craftsman.customer.service.constant.CustomerType;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
}