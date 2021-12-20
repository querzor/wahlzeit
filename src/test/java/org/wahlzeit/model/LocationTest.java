package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

    @Test
    public void instantiate() {
        CartesianCoordinate coordinate1 = new CartesianCoordinate(1, 1, 1);
        SphericCoordinate coordinate2 = SphericCoordinate.getOrCreateCoordinate(Math.PI, Math.PI/2, 40000);
        Location location1 = new Location(coordinate1);
        Location location2 = new Location(coordinate2);
    }
}
