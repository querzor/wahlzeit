package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{

    public boolean isEqual(Coordinate coordinate) {
        return this.getCartesianDistance(coordinate.asCartesianCoordinate()) < 0.001;
    }

}
