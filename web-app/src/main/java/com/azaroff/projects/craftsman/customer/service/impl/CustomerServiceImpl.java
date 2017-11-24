package com.azaroff.projects.craftsman.customer.service.impl;

import com.azaroff.projects.craftsman.customer.datalayer.dao.CustomerRepository;
import com.azaroff.projects.craftsman.customer.datalayer.entity.CustomerEntity;
import com.azaroff.projects.craftsman.customer.service.Customer;
import com.azaroff.projects.craftsman.customer.service.CustomerService;
import com.azaroff.projects.craftsman.customer.service.convert.CustomerConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AzarovD on 25.08.2016.
 */

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    @Qualifier("customerConvert")
    private CustomerConvert convert;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Customer saveCustomer(Customer customer) {

        CustomerEntity entity = repository.save(convert.toCustomerEntity(customer));

        return convert.toCustomer(entity);
    }

    @Override
    @Transactional
    public Customer findById(int id) {
        CustomerEntity customerEntity = repository.findById(id).get();
        if (customerEntity == null) {
            return null;
        }
        return convert.toCustomer(customerEntity);
    }

    @Override
    @Transactional
    public Customer findById(String id) {
        int intId = Integer.parseInt(id);
        return findById(intId);
    }

    @Override
    @Transactional
    public List<Customer> findAllEmployees() {
        Iterable<CustomerEntity> entities = repository.findAll();
        List<Customer> customers = new ArrayList<Customer>();

        for (CustomerEntity entity : entities) {
            customers.add(convert.toCustomer(entity));
        }

        return customers;
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
       
    }

}
