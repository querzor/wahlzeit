package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateTest {

    @Test
    public void instantiateCoordinate() {
        CartesianCoordinate coordinate = new CartesianCoordinate(2, -3, 4);
        assertEquals(2, coordinate.getX(), 0);
        assertEquals(-3, coordinate.getY(), 0);
        assertEquals(4, coordinate.getZ(), 0);
    }

    @Test
    public void cartesianAsCartesian() {
        CartesianCoordinate coordinate1 = new CartesianCoordinate(-2, 3, -4);
        CartesianCoordinate coordinate2 = coordinate1.asCartesianCoordinate();
        assertEquals(-2, coordinate2.getX(), 0);
        assertEquals(3, coordinate2.getY(), 0);
        assertEquals(-4, coordinate2.getZ(), 0);
    }

    @Test
    public void cartesianAsSpheric() {
        double[][][] testData = {{{-2, 3, -4}, {2.4080113299497, 2.1587989303425, 5.3851648071345}},
                {{2, 3, 4}, {0.73358132364008, 0.98279372324733, 5.3851648071345}},
                {{-4, -5, -6}, {2.3237040974243, -2.2455372690185, 8.7749643873921}},
                {{3, -9, 81}, {0.1165902252823, -1.2490457723983, 81.553663314409}},
                {{-7.123, -8.123, -9.123}, {2.2720488408661, -2.2906973347579, 14.14034607073}}};

        for (double[][] testArray : testData) {
            CartesianCoordinate coordinate1 = new CartesianCoordinate(testArray[0][0], testArray[0][1], testArray[0][2]);
            SphericCoordinate coordinate2 = coordinate1.asSphericCoordinate();
            assertEquals(testArray[1][0], coordinate2.getPhi(), 0.0001);
            assertEquals(testArray[1][1], coordinate2.getTheta(), 0.0001);
            assertEquals(testArray[1][2], coordinate2.getRadius(), 0.0001);
        }
    }

    @Test
    public void cartesianAsSphericOrigin() {
        CartesianCoordinate coordinate1 = new CartesianCoordinate(0, 0, 0);
        SphericCoordinate coordinate2 = coordinate1.asSphericCoordinate();
        assertEquals(0, coordinate2.getRadius(), 0.0001);
    }
}