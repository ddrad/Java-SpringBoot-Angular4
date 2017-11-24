package com.azaroff.projects.craftsman.authorization.datalayer.dao;

import com.azaroff.projects.craftsman.authorization.datalayer.entity.AuthorizationEntity;
import com.azaroff.projects.craftsman.customer.datalayer.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dmitiy on 27.08.2016.
 */

@Repository
public interface AuthorizationRepository extends CrudRepository<AuthorizationEntity, Integer> {

    AuthorizationEntity findById(int id);

    AuthorizationEntity findByEmailAndPassword(String email, String password);
}
