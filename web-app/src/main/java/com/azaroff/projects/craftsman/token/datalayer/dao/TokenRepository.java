package com.azaroff.projects.craftsman.token.datalayer.dao;

import com.azaroff.projects.craftsman.token.datalayer.entity.TokenDataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 13.10.2017.
 */
@Repository
public interface TokenRepository extends CrudRepository<TokenDataEntity, Integer>, TokenDataCustomRepository {

    TokenDataEntity findByAlias(String alias);

    void deleteById(int id);

    void deleteByAlias(String alias);
}
