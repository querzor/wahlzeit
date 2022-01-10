package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

    @Test
    public void testInstantiation() {
        CartesianCoordinate coordinate1 = CartesianCoordinate.getOrCreateCoordinate(1, 1, 1);
        SphericCoordinate coordinate2 = SphericCoordinate.ensureSphericCoordinate(Math.PI, Math.PI / 2, 40000);
        Location location1 = new Location(coordinate1);
        Location location2 = new Location(coordinate2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiationWithNull() {
        Location location = new Location(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testUpdateCoordinatesToNull() {
        Location location = new Location(CartesianCoordinate.getOrCreateCoordinate(1, 1, 1));
        location.setCoordinate(null);
    }

    @Test
    public void testEqualsAndHashcode() {
        Coordinate coordinate1 = CartesianCoordinate.getOrCreateCoordinate(1, 1, 1);
        Location loc1 = new Location(coordinate1);
        Location loc2 = new Location(coordinate1.asSphericCoordinate());
        Location loc3 = new Location(CartesianCoordinate.getOrCreateCoordinate(1, 1, 1.2));

        Assert.assertEquals(loc1, loc2);
        Assert.assertEquals(loc2, loc1);
        Assert.assertNotEquals(loc1, loc3);

        Assert.assertEquals(loc1.hashCode(), loc2.hashCode());
    }
}