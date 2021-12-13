package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) return false;
        SphericCoordinate coordinate1 = this.asSphericCoordinate();
        SphericCoordinate coordinate2 = coordinate.asSphericCoordinate();

        if (Double.compare(Math.round(coordinate1.getPhi() * 100) / 100.0, Math.round(coordinate2.getPhi() * 100) / 100.0) != 0)
            return false;
        if (Double.compare(Math.round(coordinate1.getTheta() * 100) / 100.0, Math.round(coordinate2.getTheta() * 100) / 100.0) != 0)
            return false;
        return Double.compare(Math.round(coordinate1.getRadius() * 100) / 100.0, Math.round(coordinate2.getRadius() * 100) / 100.0) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        return isEqual(((Coordinate) o).asSphericCoordinate());
    }

    @Override
    public int hashCode() {
        return this.asSphericCoordinate().hashCode();
    }

    @Override
    public boolean isClose(Coordinate coordinate) {
        if (coordinate == null) return false;
        CartesianCoordinate coordinate1 = this.asCartesianCoordinate();
        CartesianCoordinate coordinate2 = this.asCartesianCoordinate();
        return coordinate1.getCartesianDistance(coordinate2) < 0.001;
    }

    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate coordinate1 = this.asCartesianCoordinate();
        CartesianCoordinate coordinate2 = coordinate.asCartesianCoordinate();
        double distance = Math.sqrt(Math.pow(coordinate1.getX() - coordinate2.getX(), 2) + Math.pow(coordinate1.getY() - coordinate2.getY(), 2) + Math.pow(coordinate1.getZ() - coordinate2.getZ(), 2));

        /*
         * postconditions
         */
        assertValidDouble(distance);

        return distance;
    }

    /**
     * @param coordinate any coordinate
     * @return the central angle
     */

    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate coordinate1 = this.asSphericCoordinate();
        SphericCoordinate coordinate2 = coordinate.asSphericCoordinate();
        double phi1 = coordinate1.getPhi();
        double phi2 = coordinate2.getPhi();
        double deltaTheta = Math.abs(coordinate1.getTheta() - coordinate2.getTheta());

        double centralAngle = Math.acos(Math.cos(phi1) * Math.cos(phi2) + -Math.sin(phi1) * -Math.sin(phi2) * Math.cos(deltaTheta));

        assertValidDouble(centralAngle);

        return centralAngle;
    }

    protected void assertValidDouble(double d) {
        if (!Double.isFinite(d)) throw new IllegalArgumentException("Argument is NaN or Infinity");
    }

}