package com.goeuro.bus.route.utility.parser;

import java.io.IOException;
import java.util.Set;

import com.goeuro.bus.route.model.Route;

public interface RouteParser {
	public Set<Route> parseRoutes(String filePath) throws IOException;
}
