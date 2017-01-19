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
	    
	    private BusStopPair busStopPair;
	    private boolean connected;

	    public BusRouteQueryResponse(BusStopPair busStopPair, boolean connected) {
	    	this.busStopPair=busStopPair;
	        this.connected = connected;
	    }
	    
	    //package private for integration testing.
		BusRouteQueryResponse() {
		}
		@Override
		public String toString() {
			return "BusRouteQueryResponse [busStopPair=" + busStopPair + ", connected=" + connected + "]";
		}
	    
}
