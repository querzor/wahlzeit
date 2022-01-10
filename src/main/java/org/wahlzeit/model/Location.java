package org.wahlzeit.model;

public class Location {
    private Coordinate coordinate;


    /**
     * @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("coordinate was null");
        }
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        return getCoordinate().equals(location.getCoordinate());
    }

    @Override
    public int hashCode() {
        return getCoordinate().hashCode();
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        doAssertClassInvariants();
    }

    private void doAssertClassInvariants() throws IllegalStateException {
        if (coordinate == null) throw new IllegalStateException("coordinate is null");
    }
}