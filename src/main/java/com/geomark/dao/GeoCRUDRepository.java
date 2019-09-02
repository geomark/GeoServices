package com.geomark.dao;

import com.geomark.jpa.DBPoint;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data Repository providing basic CRUD operations on DBPoint JPA Entity
 *
 * @author Georgios Markakis
 */
public interface GeoCRUDRepository extends CrudRepository<DBPoint, Long>{

}
