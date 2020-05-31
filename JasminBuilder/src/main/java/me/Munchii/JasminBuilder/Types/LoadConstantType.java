package me.Munchii.JasminBuilder.Types;

public enum LoadConstantType {

    // Push item from run-time constant pool
    LOAD_CONSTANT("ldc"),

    // Push item from run-time constant pool (wide index)
    LOAD_CONSTANT_WIDE("ldc_w"), // For `long` and `double`

    LOAD_CONSTANT_2("ldc2"),

    LOAD_CONSTANT_2_WIDE("ldc2_w");

    private final String representation;

    LoadConstantType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
