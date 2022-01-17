package org.wahlzeit.model;

public class AntiqueCar {
    private final AntiqueCarType antiqueCarType;
    private final int id;
    private int year;
    private int nextId = 0;

    public AntiqueCar(AntiqueCarType antiqueCarType) {
        if (antiqueCarType == null) throw new IllegalStateException("antiqueCarType was null");
        this.antiqueCarType = antiqueCarType;
        id = ++nextId;
    }

    public AntiqueCarType getType() {
        return antiqueCarType;
    }

    public int getId() {
        return id;
    }
}
