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
    ABSTRACT("abstract"),
    _COMBINED("");

    private String value;

    MethodAccessSpec(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MethodAccessSpec COMBINED(MethodAccessSpec... accessSpecs) {
        MethodAccessSpec combined = MethodAccessSpec._COMBINED;
        combined.value = Arrays.stream(accessSpecs)
                .sorted()
                .map(MethodAccessSpec::getValue)
                .collect(Collectors.joining(" "));
        return combined;
    }
}
