package org.wahlzeit.model;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AntiqueCarPhotoManager extends PhotoManager{

    /**
     *
     */
    protected static final AntiqueCarPhotoManager instance = new AntiqueCarPhotoManager();


    public AntiqueCarPhotoManager() {
        super();
    }

    /**
     *
     */
    @Override
    protected Photo createObject(ResultSet rset) throws SQLException {
        return AntiqueCarPhotoFactory.getInstance().createPhoto(rset);
    }

    /**
     *
     */
    @Override
    public Photo createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        AntiqueCarPhoto result = (AntiqueCarPhoto) PhotoUtil.createPhoto(file, id);
        addPhoto(result);
        return result;
    }
}
