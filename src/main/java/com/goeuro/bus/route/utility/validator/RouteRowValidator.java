package com.goeuro.bus.route.utility.validator;

public class RouteRowValidator implements EntityValidator<String, String[]>{

	@Override
	public void validate(String routeRow, String[] routeTokens) {
		if (!routeContainsAtleastThreeIntegers(routeTokens))
			throw new IllegalArgumentException("Invalid bus route. Expected: 'routeId srcStation destStation...'. Actual: " + routeRow);
		if(routeContainsMaximumThanOneMillionStations(routeTokens))
			throw new IllegalArgumentException("Invalid bus route. Expected: 1 RouteId and Maximum number of stations = 1,000,000");
	}
	
	private boolean routeContainsMaximumThanOneMillionStations(String[] routeTokens) {
		return routeTokens.length > 1000001;
	}

	private boolean routeContainsAtleastThreeIntegers(String[] routeTokens) {
		return routeTokens.length >= 3;
	}

}
