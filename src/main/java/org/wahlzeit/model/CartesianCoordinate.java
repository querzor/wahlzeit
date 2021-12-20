package org.wahlzeit.model;

/*
just a cartesian coordinate system
 */

import java.util.HashMap;
import java.util.Map;

public class CartesianCoordinate extends AbstractCoordinate {

    private final double x, y, z;
    private static final Map<CartesianCoordinate, CartesianCoordinate> existingCoordinatesMap = new HashMap<>();


    private CartesianCoordinate(double x, double y, double z) {
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);

        this.x = x;
        this.y = y;
        this.z = z;

        doAssertClassInvariants();
    }

    public static CartesianCoordinate getOrCreateCoordinate(double x, double y, double z) {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);
        CartesianCoordinate exCoord = existingCoordinatesMap.get(cartesianCoordinate);
        if (exCoord != null) {
            return exCoord;
        } else {
            existingCoordinatesMap.put(cartesianCoordinate, cartesianCoordinate);
            return cartesianCoordinate;
        }
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
        return SphericCoordinate.getOrCreateCoordinate(phi, theta, radius);
    }

    void doAssertClassInvariants() throws IllegalStateException {
        if (Double.isNaN(x)) throw new IllegalStateException();
        if (Double.isNaN(y)) throw new IllegalStateException();
        if (Double.isNaN(z)) throw new IllegalStateException();
    }
}