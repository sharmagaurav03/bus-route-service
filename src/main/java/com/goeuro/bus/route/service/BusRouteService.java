package com.goeuro.bus.route.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goeuro.bus.route.model.BusStopPair;
import com.goeuro.bus.route.repository.BusRouteRepository;

@Service
public class BusRouteService {

	private static final Logger log = LoggerFactory.getLogger(BusRouteService.class);

	@Autowired
	private BusRouteRepository busRouteRepository;

	public boolean isDirectRouteExists(BusStopPair busStopPair) {
		log.info("BusRouteService --> directRouteExists --> busStopPair --> {}", busStopPair);
		return busRouteRepository.isDirectRouteExists(busStopPair);
	}

}
