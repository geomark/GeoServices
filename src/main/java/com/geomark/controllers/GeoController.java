package com.geomark.controllers;

import com.geomark.dao.GeoCRUDRepository;
import com.geomark.dao.GeoRepository;
import com.geomark.jpa.DBPoint;
import com.geomark.model.GetClosestPointsRequest;
import com.geomark.model.GetClosestPointsResponse;
import com.geomark.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

/**
 *
 */
@Endpoint
public class GeoController {

    private static final String NAMESPACE_URI = "http://www.geomark.com/model";

    @Autowired
    private GeoRepository geoRepository;

    @Autowired
    private GeoCRUDRepository geoCRUDRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClosestPointsRequest")
    @ResponsePayload
    public GetClosestPointsResponse getClosestPoints(@RequestPayload GetClosestPointsRequest request) {
        GetClosestPointsResponse response = new GetClosestPointsResponse();

        geoRepository
                .findClosestNeighbour(request.getLat(),request.getLong())
                .ifPresent(dbvalue -> {
            Point retPoint = new Point();
                    retPoint.setName(dbvalue.getName());
                    retPoint.setCounter(dbvalue.getCounter());
                    retPoint.setLat(dbvalue.getLat());
                    retPoint.setLong(dbvalue.getLat());
                    response.setPoint(retPoint);

            System.out.println("Value found - " + retPoint);
        });

        return response;
}
}
