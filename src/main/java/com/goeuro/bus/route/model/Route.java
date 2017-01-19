package com.goeuro.bus.route.model;

import java.util.Collections;
import java.util.Set;

public class Route {
    private final int id;
    private final Set<Integer> stopIds;

    public Route(int id, Set<Integer> stations) {
        this.id = id;
        this.stopIds = stations;
    }

	public int getId() {
		return id;
	}

	public Set<Integer> getStations() {
		return Collections.unmodifiableSet(stopIds);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((stopIds == null) ? 0 : stopIds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (id != other.id)
			return false;
		if (stopIds == null) {
			if (other.stopIds != null)
				return false;
		} else if (!stopIds.equals(other.stopIds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", stopIds=" + stopIds + "]";
	}
	
}