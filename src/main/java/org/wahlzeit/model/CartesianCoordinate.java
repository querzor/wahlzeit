package org.wahlzeit.model;

/*
just a cartesian coordinate system
 */

public class CartesianCoordinate extends AbstractCoordinate {

    private final double x, y, z;

    public CartesianCoordinate(double x, double y, double z) {
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);

        this.x = x;
        this.y = y;
        this.z = z;

        doAssertClassInvariants();
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
        doAssertClassInvariants();
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        doAssertClassInvariants();
        double theta = Math.atan2(y, x);
        double radius = Math.sqrt(x * x + y * y + z * z);
        double phi = (radius == 0) ? 0 : Math.acos(z / radius);
        return new SphericCoordinate(phi, theta, radius);
    }

    void doAssertClassInvariants() throws IllegalStateException {
        if (Double.isNaN(x)) throw new IllegalStateException();
        if (Double.isNaN(y)) throw new IllegalStateException();
        if (Double.isNaN(z)) throw new IllegalStateException();
    }
}