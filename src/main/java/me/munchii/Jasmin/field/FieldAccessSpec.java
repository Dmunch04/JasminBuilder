package me.munchii.Jasmin.field;

import me.munchii.Jasmin.method.MethodAccessSpec;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum FieldAccessSpec {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    STATIC("static"),
    FINAL("final"),
    VOLATILE("volatile"),
    TRANSIENT("transient");

    private String value;

    FieldAccessSpec(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
