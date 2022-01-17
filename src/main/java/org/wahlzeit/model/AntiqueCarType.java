package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AntiqueCarType {
    private final String name;
    protected AntiqueCarType superType = null;
    protected Set<AntiqueCarType> subTypes = new HashSet<>();

    public AntiqueCarType(String name) {
        if (name == null) throw new IllegalStateException("AntiqueCarType name was Null");
        this.name = name;
    }

    public boolean hasInstance(AntiqueCar car) {
        assert (car != null) : "asked about null object";
        if (car.getType() == this) return true;
        for (AntiqueCarType type : subTypes) {
            if (type.hasInstance(car)) return true;
        }
        return false;
    }

    public AntiqueCar createNewInstance() {
        return new AntiqueCar(this);
    }

    public void addSubType(AntiqueCarType at) {
        assert (at != null) : "tried to set null sub-type";
        at.superType = this;
        subTypes.add(at);
    }

    public Iterator<AntiqueCarType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public AntiqueCarType getSuperType() {
        return superType;
    }

    public Set<AntiqueCarType> getSubTypes() {
        return subTypes;
    }
}