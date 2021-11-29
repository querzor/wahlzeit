package org.wahlzeit.model;

import java.util.zip.CRC32C;

public class SphericCoordinate extends AbstractCoordinate {

    /*
    phi represents latitude; phi=0 points toward the north pole
    double represents longitude
    radius represents radial distance
     */

    private final double phi, theta, radius;

    /**
     * @param phi    latitude
     * @param theta  longitude
     * @param radius radial distance
     * @methodtype constructor
     */

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    /**
     * @methodtype get
     */

    public double getPhi() {
        return phi;
    }

    /**
     * @methodtype get
     */

    public double getTheta() {
        return theta;
    }

    /**
     * @methodtype get
     */

    public double getRadius() {
        return radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(phi) * Math.cos(theta);
        double y = radius * Math.sin(phi) * Math.sin(theta);
        double z = radius * Math.cos(phi);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public int hashCode() {
        CRC32C hash = new CRC32C();
        hash.update(Double.hashCode(phi));
        hash.update(Double.hashCode(theta));
        hash.update(Double.hashCode(radius));
        return (int) hash.getValue();
    }
}