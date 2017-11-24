package com.azaroff.projects.craftsman.ad.datalayer.dao;

import com.azaroff.projects.craftsman.ad.datalayer.entity.AdEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AzarovD on 25.08.2016.
 */
@Repository
public interface AdRepository extends CrudRepository<AdEntity, Integer> {

    List<AdEntity> findByTitle(String title);

    List<AdEntity> findByAuthor(int author);
}
