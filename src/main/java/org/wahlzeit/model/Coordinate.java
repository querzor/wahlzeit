package org.wahlzeit.model;

public class Coordinate {
    private double x, y, z;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //returns the Euclidean distance to the given coordinate
    public double getDistance(Coordinate coordinate) {
        return Math.sqrt(Math.pow(this.x - coordinate.getX(), 2) + Math.pow(this.y - coordinate.getY(), 2) + Math.pow(this.z - coordinate.getZ(), 2));
    }

    public boolean equals(Coordinate coordinate) {
        return isEqual(coordinate);
    }

    //true if Euclidean distance is below 0.001
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) return false;
        return this.getDistance(coordinate) < 0.001;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
