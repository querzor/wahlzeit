package org.wahlzeit.model;

public class Location {
    SphericCoordinate coordinate;

    public Location(Coordinate coordinate) {
        this.coordinate = coordinate.asSphericCoordinate();
    }

    public boolean equals(Location location) {
        if (location == null) return false;
        return this.coordinate.equals(location.coordinate);
    }

    public SphericCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(SphericCoordinate coordinate) {
        this.coordinate = coordinate;
    }
}
