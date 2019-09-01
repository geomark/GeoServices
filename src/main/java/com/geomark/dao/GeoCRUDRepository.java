package com.geomark.dao;

import com.geomark.jpa.DBPoint;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface GeoCRUDRepository extends CrudRepository<DBPoint, Long>{

}
