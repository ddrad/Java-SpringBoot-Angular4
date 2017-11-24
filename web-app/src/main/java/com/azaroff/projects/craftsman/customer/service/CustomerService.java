package com.azaroff.projects.craftsman.customer.service;

import java.util.List;

/**
 * Created by AzarovD on 25.08.2016.
 */


public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer findById(int id);

    Customer findById(String id);

    List<Customer> findAllEmployees();

    void updateCustomer(Customer customer);

}
