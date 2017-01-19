package com.goeuro.bus.route.service;

import java.io.FileNotFoundException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goeuro.bus.route.model.Route;
import com.goeuro.bus.route.repository.BusRouteRepository;
import com.goeuro.bus.route.utility.parser.FileParser;

@Service
public class BusRouteDataFileService {

	private static final Logger log = LoggerFactory.getLogger(BusRouteDataFileService.class);

	@Autowired
	private BusRouteRepository busRouteRepository;
	
	@Autowired
	private FileParser fileParser;

	public void loadBusRouteData(String filePath) throws FileNotFoundException {
		log.info("BusRouteDataFileService --> refreshBusRouteData --> filePath --> {}", filePath);
		Set<Route> routes  = fileParser.parseRoutes(filePath);
		busRouteRepository.setRoutes(routes);
	}

}
