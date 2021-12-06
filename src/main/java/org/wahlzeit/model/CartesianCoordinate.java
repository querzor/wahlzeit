package org.wahlzeit.model;

/*
just a cartesian coordinate system
 */

public class CartesianCoordinate extends AbstractCoordinate {

    private final double x, y, z;

    public CartesianCoordinate(double x, double y, double z) {
        /*
         * invariant
         */
        assert !Double.isNaN(x) && Double.isFinite(x);
        assert !Double.isNaN(y) && Double.isFinite(y);
        assert !Double.isNaN(z) && Double.isFinite(z);

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
}