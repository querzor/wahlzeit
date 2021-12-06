package org.wahlzeit.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        /*
         * preconditions
         */
        assert !Double.isNaN(phi) && Double.isFinite(theta);
        assert !Double.isNaN(theta) && Double.isFinite(theta);
        assert !Double.isNaN(radius) && Double.isFinite(radius);

        /*
        ensures that there is just one coordinate representation for each point
         */
        if (radius < 0) {
            radius = -radius;
            theta += Math.PI / 2;
            phi += Math.PI / 2;
        }
        if (phi < 0 || phi > 2 * Math.PI) phi = ((phi % (2 * Math.PI)) + 2 * Math.PI) % (2 * Math.PI);
        if (theta < 0 || theta > 2 * Math.PI) theta = ((theta % (2 * Math.PI)) + 2 * Math.PI) % (2 * Math.PI);
        if (phi > Math.PI) {
            phi = 2 * Math.PI - phi;
            theta = theta + Math.PI;
            theta = theta % 2 * Math.PI;
        }
        if (theta > Math.PI) theta -= 2 * Math.PI;

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        /*
         * postconditions
         */
        assert !Double.isNaN(this.phi) && Double.isFinite(this.theta);
        assert !Double.isNaN(this.theta) && Double.isFinite(this.theta);
        assert !Double.isNaN(this.radius) && Double.isFinite(this.radius);
    }

    /**
     * @methodtype get
     */

    double getPhi() {
        return phi;
    }

    /**
     * @methodtype get
     */

    double getTheta() {
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
        final int ACCURACY = 4;
        double phi = new BigDecimal(this.phi).setScale(ACCURACY, RoundingMode.HALF_UP).doubleValue();
        double theta = new BigDecimal(this.theta).setScale(ACCURACY, RoundingMode.HALF_UP).doubleValue();
        double radius = new BigDecimal(this.radius).setScale(ACCURACY, RoundingMode.HALF_UP).doubleValue();

        int result;
        long temp;
        temp = Double.doubleToLongBits(phi);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(theta);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}