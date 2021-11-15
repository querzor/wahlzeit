package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AntiqueCarPhotoFactory extends PhotoFactory{

    /**
     * @methodtype constructor
     */
    protected AntiqueCarPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static AntiqueCarPhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic PhotoFactory");
            setInstance(new AntiqueCarPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(AntiqueCarPhotoFactory AntiqueCarPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize PhotoFactory twice");
        }

        instance = AntiqueCarPhotoFactory;
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }


    @Override
    public Photo createPhoto() {
        return new AntiqueCarPhoto();
    }

    @Override
    public Photo createPhoto(PhotoId id) {
        return new AntiqueCarPhoto(id);
    }

    @Override
    public Photo createPhoto(ResultSet rs) throws SQLException {
        return new AntiqueCarPhoto(rs);
    }
}
