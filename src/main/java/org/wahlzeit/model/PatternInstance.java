package org.wahlzeit.model;

import java.lang.annotation.Repeatable;

@Repeatable(PatternInstances.class)
public @interface PatternInstance {
    String patternName();

    String[] participants();
}

@interface PatternInstances {
    PatternInstance[] value();
}