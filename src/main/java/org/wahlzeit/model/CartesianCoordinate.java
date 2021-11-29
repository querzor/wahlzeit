package org.wahlzeit.model;

/*
just a cartesian coordinate system
 */

import java.util.zip.CRC32C;

public class CartesianCoordinate extends AbstractCoordinate {

    private final double x, y, z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @methodtype get
     */

    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     */

    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     */

    public double getZ() {
        return z;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double theta = Math.atan2(y, x);
        double radius = Math.sqrt(x * x + y * y + z * z);
        double phi = (radius == 0) ? 0 : Math.acos(z / radius);
        return new SphericCoordinate(phi, theta, radius);
    }

    @Override
    public int hashCode() {
        CRC32C hash = new CRC32C();
        hash.update(Double.hashCode(x));
        hash.update(Double.hashCode(y));
        hash.update(Double.hashCode(z));
        return (int) hash.getValue();
    }
}