package me.munchii.Jasmin.method;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum MethodAccessSpec {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    STATIC("static"),
    FINAL("final"),
    SYNCHRONIZED("synchronized"),
    NATIVE("native"),
    ABSTRACT("abstract");

    private String value;

    MethodAccessSpec(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
