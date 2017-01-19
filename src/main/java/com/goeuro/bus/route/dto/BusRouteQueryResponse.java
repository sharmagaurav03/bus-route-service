package com.goeuro.bus.route.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goeuro.bus.route.model.BusStopPair;

public class BusRouteQueryResponse {
	
	    @JsonProperty("dep_sid")
		public int getDepartureId() {
			return busStopPair.getDepartureStopId();
		}
	    @JsonProperty("arr_sid")
		public int getArrivalId() {
			return busStopPair.getArrivalStopId();
		}

	    @JsonProperty("direct_bus_route")
		public boolean isConnected() {
			return connected;
		}
	    
	    private final BusStopPair busStopPair;
	    private final boolean connected;

	    public BusRouteQueryResponse(BusStopPair busStopPair, boolean connected) {
	    	this.busStopPair=busStopPair;
	        this.connected = connected;
	    }
		@Override
		public String toString() {
			return "BusRouteQueryResponse [busStopPair=" + busStopPair + ", connected=" + connected + "]";
		}
	    
}
