package com.geomark.controllers;


import com.geomark.dao.GeoCRUDRepository;
import com.geomark.dao.GeoRepository;
import com.geomark.jpa.DBPoint;
import com.geomark.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

/**
 * Controller exposing SOAP endpoints
 *
 * @author Georgios Markakis
 */
@Endpoint
public class GeoController {

    /**
     * LOGGER
     */
    Logger logger = LoggerFactory.getLogger(GeoController.class);


    private static final String NAMESPACE_URI = "http://www.geomark.com/model";

    @Autowired
    private GeoRepository geoRepository;

    @Autowired
    private GeoCRUDRepository geoCRUDRepository;

    /**
     * Get Closest Points Soap Request endpoint
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClosestPointsRequest")
    @ResponsePayload
    public GetClosestPointsResponse getClosestPoints(@RequestPayload GetClosestPointsRequest request) {
        GetClosestPointsResponse response = new GetClosestPointsResponse();

        geoRepository
                .findClosestNeighbour(request.getLat(), request.getLong())
                .map(this::incrementAndSave)
                .map(GeoController::dbPoint2point)
                .ifPresent(dbvalue -> {
                    response.setPoint(dbvalue);
                    logger.debug("Value found - " + dbvalue);
                });

        return response;
    }


    /**
     * Get getMostPopularPoints Soap Request endpoint
     *
     * @param request
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMostPopularPointsRequest")
    @ResponsePayload
    public GetMostPopularPointsResponse getMostPopularPoints(@RequestPayload GetMostPopularPointsRequest request) {

        GetMostPopularPointsResponse response = new GetMostPopularPointsResponse();

        response.getPoint()
                .addAll(geoRepository
                  .findmostVisited(request.getThreshold())
                  .stream()
                  .map(GeoController::dbPoint2point)
                        .collect(Collectors.toList()));

        return response;
    }



    private DBPoint incrementAndSave(DBPoint point){

        long count = point.getCounter();
        point.setCounter(++count);

        logger.info(point.toString());

        geoCRUDRepository.save(point);

        return point;
    }

    /**
     * Utility function converter
     *
     * @param dbpoint
     * @return
     */
    private static Point dbPoint2point(DBPoint dbpoint){
                            Point retPoint = new Point();
                    retPoint.setName(dbpoint.getName());
                    retPoint.setCounter(dbpoint.getCounter());
                    retPoint.setLat(dbpoint.getLat());
                    retPoint.setLong(dbpoint.getLat());
                    return retPoint;
    }
}
