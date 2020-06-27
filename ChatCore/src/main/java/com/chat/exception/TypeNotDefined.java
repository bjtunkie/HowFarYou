package com.chat.exception;

public class TypeNotDefined extends RuntimeException{
    public TypeNotDefined() {
        super("Type is not among the basic dataypes please check again.....");
    }
}
