package me.munchii.Jasmin.classes;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ClassAccessSpec {
    PUBLIC("public"),
    FINAL("final"),
    SUPER("super"),
    INTERFACE("interface"),
    ABSTRACT("abstract");

    private String value;

    ClassAccessSpec(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
