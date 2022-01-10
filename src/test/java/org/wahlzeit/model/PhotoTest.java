package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PhotoTest {

    @Test
    public void testInstantiation() {
        Coordinate coordinate1 = CartesianCoordinate.getOrCreateCoordinate(1, 2, 3);
        Coordinate coordinate2 = SphericCoordinate.ensureSphericCoordinate(1, 1, 200);

        Location loc1 = new Location(coordinate1);

        Photo photo1 = new Photo();
        photo1.setLocation(loc1);
    }

    @Test
    public void testSetLocation(){
        Photo photo = new Photo();
        Location loc = new Location(CartesianCoordinate.getOrCreateCoordinate(1, 2, 3));

        Assert.assertNull(photo.getLocation());
        photo.setLocation(loc);
        Assert.assertEquals(loc, photo.getLocation());
        photo.setLocation(null);
        Assert.assertNull(photo.getLocation());
    }

    @Test
    public void testDB() throws SQLException {
        Photo photo = new Photo();
        Coordinate coordinate = CartesianCoordinate.getOrCreateCoordinate(1, -1, 3);
        ResultSet rset = Mockito.mock(ResultSet.class);
        Location location = new Location(coordinate);
        photo.setLocation(location);
        try {
            photo.setOwnerHomePage(new URL("http://www.example.com"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        photo.writeOn(rset);

        Mockito.verify(rset, Mockito.times(1)).updateDouble("x_coordinate", coordinate.asSphericCoordinate().getPhi());
        Mockito.verify(rset, Mockito.times(1)).updateDouble("y_coordinate", coordinate.asSphericCoordinate().getTheta());
        Mockito.verify(rset, Mockito.times(1)).updateDouble("z_coordinate", coordinate.asSphericCoordinate().getRadius());
    }
}
