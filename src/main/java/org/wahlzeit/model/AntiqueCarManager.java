package org.wahlzeit.model;

import java.util.HashMap;

public class AntiqueCarManager {
    private static AntiqueCarManager instance;
    private final HashMap<Integer, AntiqueCar> AntiqueCars = new HashMap<>();
    private final HashMap<String, AntiqueCarType> AntiqueCarTypes = new HashMap<>();

    public AntiqueCar createAntiqueCar(String antiqueCarType) {
        if (antiqueCarType == null) throw new IllegalStateException("name was null");

        AntiqueCarType antiqueCarType1 = getOrCreateAntiqueCarType(antiqueCarType);
        AntiqueCar toReturn = antiqueCarType1.createNewInstance();
        AntiqueCars.put(toReturn.getId(), toReturn);
        return toReturn;
    }

    public static AntiqueCarManager getInstance() {
        if (instance == null) instance = new AntiqueCarManager();
        return instance;
    }

    public AntiqueCarType getOrCreateAntiqueCarType(String name) {
        if (!AntiqueCarTypes.containsKey(name)) {
            AntiqueCarTypes.put(name, new AntiqueCarType(name));
        }
        return AntiqueCarTypes.get(name);
    }

    private AntiqueCarManager() {};
}
