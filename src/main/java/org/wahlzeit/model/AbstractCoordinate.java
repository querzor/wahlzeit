package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{

    //coordinates are assumed to be equal, if their distance Cartesian distance is smaller than 0.001
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) return false;
        return getCartesianDistance(coordinate) < 0.001;
    }

    public boolean equals(Coordinate coordinate) {
        return isEqual(coordinate);
    }

    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate coordinate1 = this.asCartesianCoordinate();
        CartesianCoordinate coordinate2 = coordinate.asCartesianCoordinate();
        return Math.sqrt(Math.pow(coordinate1.getX() - coordinate2.getX(), 2) + Math.pow(coordinate1.getY() - coordinate2.getY(), 2) + Math.pow(coordinate1.getZ() - coordinate2.getZ(), 2));
    }

    /**
     * @param coordinate any coordinate
     * @return the central angle
     */

    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate coordinate1 = this.asSphericCoordinate();
        SphericCoordinate coordinate2 = coordinate.asSphericCoordinate();
        //phi1 and phi2 converted spherical latitude
        double phi1 = coordinate1.getPhi() - Math.PI/2;
        double phi2 = coordinate2.getPhi() - Math.PI/2;
        double deltaTheta = Math.abs(coordinate1.getTheta() - coordinate2.getTheta());

        return Math.acos(Math.sin(phi1) * Math.sin(phi2) + Math.cos(phi1) * Math.cos(phi2) * Math.cos(deltaTheta));
    }

}