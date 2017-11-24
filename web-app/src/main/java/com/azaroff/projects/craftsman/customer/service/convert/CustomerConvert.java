package com.azaroff.projects.craftsman.customer.service.convert;

import com.azaroff.projects.craftsman.customer.service.Customer;
import com.azaroff.projects.craftsman.customer.datalayer.entity.CustomerEntity;
import org.springframework.stereotype.Service;

/**
 * Created by AzarovD on 25.08.2016.
 */

@Service("customerConvert")
public class CustomerConvert {

    public CustomerEntity toCustomerEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setMiddleName(customer.getMiddleName());
        entity.setType(customer.getType());
        return entity;
    }

    public Customer toCustomer(CustomerEntity entity) {
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setFirstName(entity.getFirstName());
        customer.setLastName(entity.getLastName());
        customer.setMiddleName(entity.getMiddleName());
        customer.setType(entity.getType());
        return customer;
    }
}
