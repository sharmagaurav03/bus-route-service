package com.goeuro.bus.route.utility.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.goeuro.bus.route.model.Route;
import com.goeuro.bus.route.utility.validator.RouteRowValidator;
import com.goeuro.bus.route.utility.validator.StringValidator;

@Component
public class FileParser implements RouteParser {
	private static final Logger log = LoggerFactory.getLogger(FileParser.class);
	private StringValidator stringValidator = new StringValidator();
	private RouteRowValidator routeRowValidator = new RouteRowValidator();
	
	public FileParser() {
		super();
	}

	//TODO: Add validators in Map using DI.
	public FileParser(StringValidator stringValidator, RouteRowValidator routeRowValidator) {
		super();
		this.stringValidator = stringValidator;
		this.routeRowValidator = routeRowValidator;
	}

	public StringValidator getStringValidator() {
		return stringValidator;
	}

	public void setStringValidator(StringValidator stringValidator) {
		this.stringValidator = stringValidator;
	}

	public RouteRowValidator getRouteRowValidator() {
		return routeRowValidator;
	}

	public void setRouteRowValidator(RouteRowValidator routeRowValidator) {
		this.routeRowValidator = routeRowValidator;
	}

	public  Set<Route> parseRoutes(String filePath) throws FileNotFoundException {
		log.info("FlatFileParser --> parseRoute -->filePath -->{}", filePath);
		String content = extractFileContentToString(filePath);
		String[] routeRows = extractRouteRows(content);
		Set<Route> routes = extractRoutesFromRows(routeRows);
		log.info("FlatFileParser --> parseRoute -->List<Route> extracted -->{}", routes);
		return routes;

	}

	private String[] extractRouteRows(String content) {
		stringValidator.validate("Bus route file data",content);
		return content.split("\n");
	}

	private Set<Route> extractRoutesFromRows(String[] routeRows) {
		int totalRoutes = Integer.parseInt(routeRows[0].trim());
		int routesRowCounter = 1;
		int tempRouteId = -1;
		HashSet<Integer> stopIds;
		HashSet<Route> routes = new HashSet<>();

		while (routesRowCounter < totalRoutes) {
			 stopIds = new HashSet<>();
			
			String[] routeTokens = routeRows[routesRowCounter].split(" ");
			
			routeRowValidator.validate(routeRows[routesRowCounter], routeTokens);
			
			tempRouteId = Integer.parseInt(routeTokens[0]);
			int routesTokenCounter = 1;
			while (routesTokenCounter < routeTokens.length) {
				stopIds.add(Integer.parseInt(routeTokens[routesTokenCounter].trim()));
				++routesTokenCounter;
			}
			routes.add(new Route(tempRouteId, stopIds));
			++routesRowCounter;
		}
		return Collections.unmodifiableSet(routes);
	}

	private String extractFileContentToString(String filePath) throws FileNotFoundException {
		stringValidator.validate("Bus route file path", filePath.trim());
		String content;
		Scanner scanner;
		try {
			File routeDataFile = new File(filePath.trim());
			if(!routeDataFile.exists())
				throw new FileNotFoundException("Route data file does not exist. File path is "+filePath);
				
			scanner = new Scanner(routeDataFile);
		} catch (FileNotFoundException e) {
			log.error("FlatFileParser --> extractFileContentToString -->filePath -->{}", e.getMessage(),e);
			throw e;
		}
		content = scanner.useDelimiter("\\Z").next();
		scanner.close();
		return content;
	}

}
