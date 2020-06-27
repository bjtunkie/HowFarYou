package com.chat.sampleobjects;

import java.util.List;

public abstract class ModifierObject {

    private static final int privateStaticFinalIntObject = 0;
    private static transient int privateStaticTransientObject = 0;
    private transient int privateTransientObject = 0;
    private final int privateFinalObject = 0;

    private final List<Object> finalList = null;
    private List<Object> nonFinalList = null;
}
