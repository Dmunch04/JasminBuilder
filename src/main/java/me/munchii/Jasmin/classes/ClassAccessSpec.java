package me.munchii.Jasmin.classes;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ClassAccessSpec {
    PUBLIC("public"),
    FINAL("final"),
    SUPER("super"),
    INTERFACE("interface"),
    ABSTRACT("abstract"),
    _COMBINED("");

    private String value;

    ClassAccessSpec(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClassAccessSpec COMBINED(ClassAccessSpec... accessSpecs) {
        ClassAccessSpec combined = ClassAccessSpec._COMBINED;
        combined.value = Arrays.stream(accessSpecs)
                .sorted()
                .map(ClassAccessSpec::getValue)
                .collect(Collectors.joining(" "));
        return combined;
    }
}
