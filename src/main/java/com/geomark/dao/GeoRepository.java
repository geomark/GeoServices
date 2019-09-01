package com.geomark.dao;

import com.geomark.jpa.DBPoint;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 *
 */
public interface GeoRepository extends Repository<DBPoint, Long> {

    @Cacheable("points")
    @Query(value = "SELECT * FROM  public.\"POINTS_TABLE_GEO\" WHERE name = :id ;",
            nativeQuery=true
    )
    Optional<DBPoint>  findPoint(@Param("id") String point);


    @Cacheable("points")
    @Query(value = "SELECT * FROM  public.\"POINTS_TABLE_GEO\" ORDER BY ST_Distance(ST_MakePoint(longitude,lattitude)::geometry, 'POINT ( :lattitude :longitude )'::geometry);\n",
            nativeQuery=true
    )
    Optional<DBPoint> findClosestNeighbour(@Param("lattitude") double lattitude,@Param("longitude") double longitude);
}
