package com.goeuro.bus.route.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.goeuro.bus.route.dto.BusRouteQueryResponse;
import com.goeuro.bus.route.model.BusStopPair;
import com.goeuro.bus.route.service.BusRouteService;

@RestController
@RequestMapping("/api")
public class BusRouteController {
	private static Logger log = LoggerFactory.getLogger(BusRouteController.class);

	@Autowired
	private BusRouteService busRouteService;

	@RequestMapping(value = "direct", method = RequestMethod.GET)
	public BusRouteQueryResponse findDirectRoute(@RequestParam("dep_sid") int departureStopId,
            @RequestParam("arr_sid") int arrivalStopId)
	//TODO: Look into why the request params to pojo mapping not working automatically with 'jsonProperty'.
	
	{
		BusStopPair busStopPair = new BusStopPair(departureStopId, arrivalStopId);
		log.info("BusRouteController --> findDirectRoute --> busStopPair -->{}", busStopPair);
		boolean directRoute = busRouteService.isDirectRouteExists(busStopPair);
		return new BusRouteQueryResponse(busStopPair, directRoute);
	}
	
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Bus route data is not initialized.")
	@ExceptionHandler(IllegalStateException.class)
	 public void handleIllegalStateException(IllegalStateException exception) {
		log.info("BusRouteController --> handleIllegalStateException --> IllegalStateException --> {}", exception.getMessage());
	 }

}
