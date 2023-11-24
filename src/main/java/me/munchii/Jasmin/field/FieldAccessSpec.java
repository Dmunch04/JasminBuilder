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
    TRANSIENT("transient"),
    _COMBINED("");

    private String value;

    FieldAccessSpec(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FieldAccessSpec COMBINED(FieldAccessSpec... accessSpecs) {
        FieldAccessSpec combined = FieldAccessSpec._COMBINED;
        combined.value = Arrays.stream(accessSpecs)
                .sorted()
                .map(FieldAccessSpec::getValue)
                .collect(Collectors.joining(" "));
        return combined;
    }
}
