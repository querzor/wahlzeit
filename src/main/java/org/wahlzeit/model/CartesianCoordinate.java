package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {
    private double x, y, z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //returns the Euclidean distance to the given coordinate
    public double getDistance(CartesianCoordinate coordinate) {
        return Math.sqrt(Math.pow(this.x - coordinate.getX(), 2) + Math.pow(this.y - coordinate.getY(), 2) + Math.pow(this.z - coordinate.getZ(), 2));
    }

    public boolean equals(CartesianCoordinate coordinate) {
        return isEqual(coordinate);
    }

    //true if Euclidean distance is below 0.001
    public boolean isEqual(CartesianCoordinate coordinate) {
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

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate otherCoord = coordinate.asCartesianCoordinate();
        double distance = Math.sqrt(Math.pow((otherCoord.getX() - this.getX()), 2) +
                Math.pow((otherCoord.getY() - this.getY()), 2) +
                Math.pow((otherCoord.getZ() - this.getZ()), 2) );
        return distance;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(x*x+y*y+z*z);
        double phi = Math.atan(x*x+y*y/z);
        double theta;
        if (x > 0) {
            theta = Math.atan(y/x);
        } else if (x < 0) {
            theta = Math.atan(y/x) + Math.PI;
        } else {
            theta = Math.PI/2;
        }
        return new SphericCoordinate(phi, theta, radius);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return this.asSphericCoordinate().getCentralAngle(this);
    }
}
