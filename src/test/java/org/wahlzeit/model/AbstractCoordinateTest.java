package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractCoordinateTest {

    final Coordinate CartesianOrigin = CartesianCoordinate.getOrCreateCoordinate(0, 0, 0);
    final Coordinate SphericOrigin = SphericCoordinate.ensureSphericCoordinate(0, 0, 0);
    final Coordinate cartesian1 = CartesianCoordinate.getOrCreateCoordinate(-1, -1, 0);
    final Coordinate cartesian2 = CartesianCoordinate.getOrCreateCoordinate(-3, -4, 1);
    final Coordinate spheric1 = SphericCoordinate.ensureSphericCoordinate(0, 0, 5);
    final Coordinate spheric2 = SphericCoordinate.ensureSphericCoordinate(Math.PI / 4, 0, 7.0710678118655);

    @Test
    public void testEquals() {
        Coordinate coordinate1 = CartesianCoordinate.getOrCreateCoordinate(1, 2, 3);
        Coordinate coordinate2 = CartesianCoordinate.getOrCreateCoordinate(1, 2, 3);
        Coordinate coordinate3 = CartesianCoordinate.getOrCreateCoordinate(1, 2, 3.1);
        //(1, 2, 3) as spherical coordinate
        Coordinate coordinate4 = SphericCoordinate.ensureSphericCoordinate(0.64052231267943, 1.1071487177941, 3.7416573867739);
        //(1, 2, 3.1) as spherical coordinate
        Coordinate coordinate5 = SphericCoordinate.ensureSphericCoordinate(0.62488674794732, 1.1071487177941, 3.8223029707233);

        assertEquals(coordinate1, coordinate2);
        assertTrue(coordinate1.isEqual(coordinate2));
        assertNotEquals(coordinate1, coordinate3);
        assertFalse(coordinate1.isEqual(coordinate3));
        assertFalse(coordinate1.isEqual(null));
        assertNotEquals(null, coordinate1);

        assertTrue(coordinate1.isEqual(coordinate4));
        assertFalse(coordinate1.isEqual(coordinate5));
    }


    @Test
    public void cartesianEqualsSphericAndItself() {
        double[][] testData = {{0, 0, 0}, {1, 2, 3}, {1, 2, -3}, {1, -2, 3}, {-1, 2, 3}, {1, -2, -3}, {-1, 2, -3}, {-1, -2, 3}, {-1, -2, -3}, {2, 0, 0}, {0, 2, 0}, {0, 0, 2}, {2, 2, 0}, {2, 0, 2}, {0, 2, 2}, {9999, 1, 1}, {1, 9999, 1}, {1, 1, 9999}};
        for (double[] toTest : testData) {
            CartesianCoordinate coordinate1 = CartesianCoordinate.getOrCreateCoordinate(toTest[0], toTest[1], toTest[2]);
            SphericCoordinate coordinate2 = coordinate1.asSphericCoordinate();
            CartesianCoordinate coordinate3 = coordinate2.asCartesianCoordinate();

            assertTrue(coordinate1.isEqual(coordinate2));
            assertTrue(coordinate1.isEqual(coordinate3));
        }
    }

    @Test
    public void sphericEqualsCartesianAndItself() {
        double[][] testData = {{0, 0, 0}, {1, 2, 3}, {1, 2, -3}, {1, -2, 3}, {-1, 2, 3}, {1, -2, -3}, {-1, 2, -3}, {-1, -2, 3}, {-1, -2, -3}, {2, 0, 0}, {0, 2, 0}, {0, 0, 2}, {2, 2, 0}, {2, 0, 2}, {0, 2, 2}, {9999, 1, 1}, {1, 9999, 1}, {1, 1, 9999}};
        for (double[] toTest : testData) {
            SphericCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(toTest[0], toTest[1], toTest[2]);
            CartesianCoordinate coordinate2 = coordinate1.asCartesianCoordinate();
            SphericCoordinate coordinate3 = coordinate2.asSphericCoordinate();

            assertTrue(coordinate1.isClose(coordinate2));
            assertTrue(coordinate1.isClose(coordinate3));
        }
    }

    @Test
    public void differentSphericCoordinatesOfTheSamePoint() {
        double[][] testData = {{1, 1, 3}, {1 + 2 * Math.PI, 1, 3}, {1 - 2 * Math.PI, 1, 3}, {1 + 2 * Math.PI, 1 + 2 * Math.PI, 3}, {-1, 1 + Math.PI, 3}};
        for (int i = 0; i < testData.length - 1; i++) {
            SphericCoordinate coordinate1 = SphericCoordinate.ensureSphericCoordinate(testData[i][0], testData[i][1], testData[i][2]);
            SphericCoordinate coordinate2 = SphericCoordinate.ensureSphericCoordinate(testData[i + 1][0], testData[i + 1][1], testData[i + 1][2]);
            assertTrue(coordinate1.isClose(coordinate2));
        }
    }

    @Test
    public void cartessianDistanceTest() {
        assertEquals(1.414213562, CartesianOrigin.getCartesianDistance(cartesian1), 0.0001);
        assertEquals(5.099019514, CartesianOrigin.getCartesianDistance(cartesian2), 0.0001);
        assertEquals(3.741657387, cartesian1.getCartesianDistance(cartesian2), 0.0001);
        assertEquals(CartesianOrigin.getCartesianDistance(CartesianOrigin), 0.0, 0.0001);
    }

    @Test
    public void cartesianDistanceOfSphericCoordTest() {
        assertEquals(0.0, SphericOrigin.getCartesianDistance(SphericOrigin), 0.000001);
        assertEquals(5, SphericOrigin.getCartesianDistance(spheric1), 0.0001);
        assertEquals(7.0710678118655, SphericOrigin.getCartesianDistance(spheric2), 0.0001);
        assertEquals(5, spheric1.getCartesianDistance(spheric2), 0.0001);
    }

    @Test
    public void mixedCartesianDistance() {
        assertEquals(0.0, SphericOrigin.getCartesianDistance(CartesianOrigin), 0.0001);
        assertEquals(7.874007874, spheric2.getCartesianDistance(cartesian1), 0.0001);
    }

    @Test
    public void centralAngle() {
        SphericCoordinate Berlin = SphericCoordinate.ensureSphericCoordinate(Math.toRadians(52.517) + Math.PI / 2, Math.toRadians(13.40), 10);
        SphericCoordinate Tokio = SphericCoordinate.ensureSphericCoordinate(Math.toRadians(35.70) + Math.PI / 2, Math.toRadians(139.767), 10);
        assertEquals(1.400, Berlin.getCentralAngle(Tokio), 0.001);
        assertEquals(1.400, Tokio.getCentralAngle(Berlin), 0.001);

        Coordinate coordinate1 = CartesianCoordinate.getOrCreateCoordinate(1000, 0, 0).asSphericCoordinate();
        Coordinate coordinate2 = CartesianCoordinate.getOrCreateCoordinate(0, 1000, 0).asSphericCoordinate();
        Coordinate coordinate3 = CartesianCoordinate.getOrCreateCoordinate(0, 0, 1000).asSphericCoordinate();
        assertEquals(Math.PI / 2, coordinate1.getCentralAngle(coordinate2), 0.0001);
        assertEquals(Math.PI / 2, coordinate1.getCentralAngle(coordinate3), 0.0001);
        assertEquals(Math.PI / 2, coordinate2.getCentralAngle(coordinate3), 0.0001);

        Coordinate coordinate4 = CartesianCoordinate.getOrCreateCoordinate(-5, 0, 0).asSphericCoordinate();
        Coordinate coordinate5 = CartesianCoordinate.getOrCreateCoordinate(0, -5, 0).asSphericCoordinate();
        Coordinate coordinate6 = CartesianCoordinate.getOrCreateCoordinate(0, 0, -5).asSphericCoordinate();
        assertEquals(Math.PI, coordinate1.getCentralAngle(coordinate4), 0.0001);
        assertEquals(Math.PI, coordinate2.getCentralAngle(coordinate5), 0.0001);
        assertEquals(Math.PI, coordinate3.getCentralAngle(coordinate6), 0.0001);

        Coordinate coordinate7 = CartesianCoordinate.getOrCreateCoordinate(1, 1, 1).asSphericCoordinate();
        Coordinate coordinate8 = CartesianCoordinate.getOrCreateCoordinate(-1, -1, -1).asSphericCoordinate();
        assertEquals(Math.PI, coordinate7.getCentralAngle(coordinate8), 0.0001);

        Coordinate coordinate9 = SphericCoordinate.ensureSphericCoordinate(Math.PI / 6, 0, 10);
        Coordinate coordinate10 = SphericCoordinate.ensureSphericCoordinate(Math.PI / 2 + Math.PI / 6, 0, 10);
        assertEquals(Math.PI / 2, coordinate9.getCentralAngle(coordinate10), 0.0001);
    }
}