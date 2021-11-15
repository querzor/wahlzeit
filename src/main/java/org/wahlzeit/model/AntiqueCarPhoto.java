package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AntiqueCarPhoto extends Photo{
    /**
     * @methodtype constructor
     */
    public AntiqueCarPhoto() {
        super();
    }

    /**
     * @methodtype constructor
     */
    public AntiqueCarPhoto(PhotoId myId) {
        super(myId);
    }

    /**
     * @methodtype constructor
     */
    public AntiqueCarPhoto(ResultSet rset) throws SQLException {
        super(rset);
    }

}
