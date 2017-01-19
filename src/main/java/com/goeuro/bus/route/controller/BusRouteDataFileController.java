package com.goeuro.bus.route.controller;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.goeuro.bus.route.service.BusRouteDataFileService;

@RestController
@RequestMapping("/api/route/data")
public class BusRouteDataFileController {
	private static Logger log = LoggerFactory.getLogger(BusRouteDataFileController.class);

	@Autowired
	private BusRouteDataFileService busRouteDataFileService;

	@RequestMapping(value = "load", method = RequestMethod.PUT)
	public void loadData(@RequestBody String filePath) throws FileNotFoundException
	{
		log.info("BusRouteDataFileController --> refreshData --> path --> {}", filePath);
		busRouteDataFileService.loadBusRouteData(filePath);
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="File not found.")
	@ExceptionHandler(FileNotFoundException.class)
	 public void handleFileNotFoundException(FileNotFoundException exception) {
		log.info("BusRouteDataFileController --> handleFileNotFoundException --> FileNotFoundException --> {}", exception.getMessage());
	 }
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid Bus route data file")
	@ExceptionHandler(IllegalArgumentException.class)
	 public void handleIllegalArgumentException(IllegalArgumentException exception) {
		log.info("BusRouteDataFileController --> handleIllegalArgumentException --> IllegalArgumentException --> {}", exception.getMessage());
	 }
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bus route data file contains non integer route id and stops id")
	@ExceptionHandler(NumberFormatException.class)
	 public void handleNumberFormatException(NumberFormatException exception) {
		log.info("BusRouteDataFileController --> handleNumberFormatException --> NumberFormatException --> {}", exception.getMessage());
	 }


}
