package org.wahlzeit.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SphericCoordinateTest {
    @Test
    public void testValueTypeProperties() {
        SphericCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(2, 4, 5);
        SphericCoordinate coordinate2 = SphericCoordinate.ensureSphericCoordinate(2, 4.0000009, 5);
        SphericCoordinate coordinate3 = SphericCoordinate.ensureSphericCoordinate(2, 4.9, 5);
        assertSame(coordinate1, coordinate2);
        assertNotSame(coordinate1, coordinate3);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * tests the precondition of the constructor
     */
    @Test
    public void invalidArguments() {
        exception.expect(java.lang.IllegalArgumentException.class);
        SphericCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(Double.POSITIVE_INFINITY, 1, 1);
    }

    @Test
    public void instantiateCoordinate() {
        SphericCoordinate coordinate = SphericCoordinate.ensureSphericCoordinate(2, 4, 5);
        assertEquals(2, coordinate.getPhi(), 0);
        assertEquals(-2.2831853071795862, coordinate.getTheta(), 0);
        assertEquals(5, coordinate.getRadius(), 0);
    }

    @Test
    public void sphericAsSpheric() {
        SphericCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(2, 5, 10);
        SphericCoordinate coordinate2 = coordinate1.asSphericCoordinate();
        assertEquals(2, coordinate2.getPhi(), 0);
        assertEquals(-1.2831853071795862, coordinate2.getTheta(), 0);
        assertEquals(10, coordinate2.getRadius(), 0);
    }

    @Test
    public void cartesianAsSpheric() {
        //This test does not indicate any errors as there is an infinite number of Spherical coordinates for the same Cartesian coordinate
        double[][][] testData = {{{-2, 3, -4}, {2.4080113299497, 2.1587989303425, 5.3851648071345}},
                {{2, 3, 4}, {0.73358132364008, 0.98279372324733, 5.3851648071345}},
                {{-4, -5, -6}, {2.3237040974243, -2.2455372690185, 8.7749643873921}},
                {{3, -9, 81}, {0.1165902252823, -1.2490457723983, 81.553663314409}},
                {{-7.123, -8.123, -9.123}, {2.2720488408661, -2.2906973347579, 14.14034607073}}};

        for (double[][] testArray : testData) {
            SphericCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(testArray[1][0], testArray[1][1], testArray[1][2]);
            CartesianCoordinate coordinate2 = coordinate1.asCartesianCoordinate();
            assertEquals(testArray[0][0], coordinate2.getX(), 0.0001);
            assertEquals(testArray[0][1], coordinate2.getY(), 0.0001);
            assertEquals(testArray[0][2], coordinate2.getZ(), 0.0001);
        }
    }

    @Test
    public void convertOriginToCartesian() {
        CartesianCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(0, 0, 0).asCartesianCoordinate();
        CartesianCoordinate coordinate2 = SphericCoordinate.ensureSphericCoordinate(1, 2, 0).asCartesianCoordinate();

        assertEquals(0, coordinate1.getX(), 0.0001);
        assertEquals(0, coordinate1.getY(), 0.0001);
        assertEquals(0, coordinate1.getZ(), 0.0001);
        assertEquals(0, coordinate2.getX(), 0.0001);
        assertEquals(0, coordinate2.getY(), 0.0001);
        assertEquals(0, coordinate2.getZ(), 0.0001);
    }

    @Test
    public void convertCoordinateOnAxis() {
        SphericCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(1.5707963267949, 0, 5);
        SphericCoordinate coordinate2 = SphericCoordinate.ensureSphericCoordinate(1.5707963267949, 0.78539816339745, 7.0710678118655);
        SphericCoordinate coordinate3 = SphericCoordinate.ensureSphericCoordinate(1.5707963267949, 1.5707963267949, 5);
        SphericCoordinate coordinate4 = SphericCoordinate.ensureSphericCoordinate(1.5707963267949, -1.5707963267949, 5);
        SphericCoordinate coordinate5 = SphericCoordinate.ensureSphericCoordinate(0, 0, 5);
        SphericCoordinate coordinate6 = SphericCoordinate.ensureSphericCoordinate(Math.PI, 0, 5);

        SphericCoordinate[] testData = {coordinate1, coordinate2, coordinate3, coordinate4, coordinate5, coordinate6};
        int[][] testDataCartesian = {{5, 0, 0}, {5, 5, 0}, {0, 5, 0}, {0, -5, 0}, {0, 0, 5}, {0, 0, -5}};

        for (int i = 0; i < testData.length; i++) {
            CartesianCoordinate testCoordinate = testData[i].asCartesianCoordinate();
            assertEquals(testDataCartesian[i][0], testCoordinate.getX(), 0.0001);
            assertEquals(testDataCartesian[i][1], testCoordinate.getY(), 0.0001);
            assertEquals(testDataCartesian[i][2], testCoordinate.getZ(), 0.0001);
        }
    }

}