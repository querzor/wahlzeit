package org.wahlzeit.model;

public class Location {
    Coordinate coordinate;

    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean equals(Location location) {
        if (location == null) return false;
        return this.coordinate.equals(location.coordinate);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
