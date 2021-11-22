package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class CartesianCoordinateTest {

    @Test
    public void instantiateCoordinate() {
        CartesianCoordinate coordinate = new CartesianCoordinate(1, -1, 1);
        System.out.println(coordinate.getX());
        Assert.assertEquals(1, coordinate.getX(), 0.00001);
        Assert.assertEquals(coordinate.getY(), -1.0, 0.00001);
        Assert.assertEquals(coordinate.getZ(), 1.0, 0.00001);
    }

}
