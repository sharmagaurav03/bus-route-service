package com.goeuro.bus.route.repository;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.goeuro.bus.route.model.BusStopPair;
import com.goeuro.bus.route.model.Route;

@Repository
public class BusRouteRepository {

	private static final Logger log = LoggerFactory.getLogger(BusRouteRepository.class);

	private volatile Set<Route> routes;

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}

	public boolean isDirectRouteExists(BusStopPair busStopPair) {
		log.info("BusRouteService --> directRouteExists --> busStopPair --> {}", busStopPair);
		if(routes == null)
			throw new IllegalStateException("Bus route data is not initialized.");
		
		return routes.stream().anyMatch(route -> doesRouteContainsBusStopPair(route, busStopPair));
	}

	private boolean doesRouteContainsBusStopPair(Route route, BusStopPair busStopTuple) {
		return route.getStations().contains(busStopTuple.getDepartureStopId())
				&& route.getStations().contains(busStopTuple.getArrivalStopId());
	}

}
