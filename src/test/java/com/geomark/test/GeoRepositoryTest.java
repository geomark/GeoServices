package com.geomark.test;


import com.geomark.config.Application;
import com.geomark.dao.GeoCRUDRepository;
import com.geomark.dao.GeoRepository;
import com.geomark.jpa.DBPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.h2gis.functions.factory.H2GISFunctions.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)

public class GeoRepositoryTest {

    Logger logger = LoggerFactory.getLogger(GeoRepositoryTest.class);

    @Autowired
    private GeoRepository geoRepository;

    @Autowired
    private GeoCRUDRepository geoCRUDRepository;

    @Before
    public void init(){
        logger.info("Initializing Test");
    }


    @Test
    @Sql({"/init.sql"})
    public void testFindByname(){
        logger.info("XXXXXXXX");
        long items = geoCRUDRepository.count();

        assertEquals(0,items);

        DBPoint item = new DBPoint();
        item.setLat(38.021641);
        item.setLong(24.004971);
        item.setName("Rafina");

        DBPoint res = geoCRUDRepository.save(item);
        logger.info("Created:" + res);
        assertNotNull(res);


        Optional<DBPoint> foundpoint = geoRepository.findPoint("Rafina");
        assertNotNull(foundpoint.get());



//        geoRepository.findClosestNeighbour(38.021641,24.004971);

    }

}
