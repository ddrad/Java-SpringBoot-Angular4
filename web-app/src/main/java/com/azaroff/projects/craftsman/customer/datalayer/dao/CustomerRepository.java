package com.azaroff.projects.craftsman.customer.datalayer.dao;

import com.azaroff.projects.craftsman.customer.datalayer.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AzarovD on 25.08.2016.
 */

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

}
