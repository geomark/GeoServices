package com.geomark.dao;

import com.geomark.jpa.DBPoint;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data Repository providing custom functions for geolocation operations
 *
 * @author Georgios Markakis
 */
public interface GeoRepository extends Repository<DBPoint, Long> {

    /**
     * Custom query for finding a point
     *
     * @param point
     * @return
     */
    @Cacheable("points")
    @Query(value = "SELECT * FROM  points_table_geo WHERE name = :id ;",
            nativeQuery=true
    )
    Optional<DBPoint>  findPoint(@Param("id") String point);


    /**
     * Custom Query that returns the closest point to a set of coordinates (uses PostGIS extensions)
     *
     * @param lat
     * @param lon
     * @return
     */
    @Cacheable("points")
    @Query(value = "SELECT * FROM  points_table_geo ORDER BY ST_Distance(ST_MakePoint(longitude,lattitude)\\:\\:geometry, ST_MakePoint(:lon,:lat)\\:\\:geometry) LIMIT 1;\n",
            nativeQuery=true
    )
    Optional<DBPoint> findClosestNeighbour(@Param("lat") double lat,@Param("lon") double lon);


    /**
     * Returns the most "visited" (retrieved by findClosestNeighbour method) Point
     *
     * @return
     */
    @Query(value = "SELECT * FROM  points_table_geo WHERE counter >= :threshold ORDER BY counter;\n",
    nativeQuery=true
    )
    List<DBPoint> findmostVisited(@Param("threshold") long threshold);

}
