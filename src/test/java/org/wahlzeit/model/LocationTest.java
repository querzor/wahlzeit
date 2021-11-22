package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

    @Test
    public void instantiateLocation() {
        CartesianCoordinate coordinate = new CartesianCoordinate(1.0, -1.0, 1.0);
        Location location = new Location(coordinate);
        Assert.assertEquals(location.coordinate, coordinate);
    }
}
