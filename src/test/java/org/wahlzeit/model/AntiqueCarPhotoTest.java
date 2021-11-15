package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class AntiqueCarPhotoTest {
    @Test
    public void AntiqueCarPhotoInstantiateTest() {
        AntiqueCarPhoto test = new AntiqueCarPhoto();
        Assert.assertNotNull(test);
    }
}
