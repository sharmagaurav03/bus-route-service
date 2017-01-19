package com.goeuro.bus.route.model;

public class BusStopPair {
	private int arrivalStopId;

	private int departureStopId;

	public BusStopPair(int departureStopId, int arrivalStopId) {
		this.departureStopId = departureStopId;
		this.arrivalStopId = arrivalStopId;
	}

	public int getArrivalStopId() {
		return arrivalStopId;
	}

	public int getDepartureStopId() {
		return departureStopId;
	}

	public void setArrivalStopId(int arrivalStopId) {
		this.arrivalStopId = arrivalStopId;
	}

	public void setDepartureStopId(int departureStopId) {
		this.departureStopId = departureStopId;
	}

	@Override
	public String toString() {
		return "BusStopPair [arrivalStopId=" + arrivalStopId + ", departureStopId=" + departureStopId + "]";
	}

	
}
