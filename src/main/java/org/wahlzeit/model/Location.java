package org.wahlzeit.model;

public class Location {
    CartesianCoordinate coordinate;

    public Location(CartesianCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean equals(Location location) {
        if (location == null) return false;
        return this.coordinate.equals(location.coordinate);
    }

    public CartesianCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CartesianCoordinate coordinate) {
        this.coordinate = coordinate;
    }
}
