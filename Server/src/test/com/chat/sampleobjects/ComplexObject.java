package com.chat.sampleobjects;

import java.util.LinkedList;
import java.util.List;

public class ComplexObject {

    private final List<String> names;

    public ComplexObject() {
        names = new LinkedList<>();
    }

    public void add(String string) {
        names.add(string);
    }

    public List<String> get() {
        return this.names;
    }
}
