package me.Munchii.JasminBuilder.Types;

public enum LocalVariableType {

    // Return from subroutine
    RETURN("ret"),

    // Load reference from local variable
    LOAD_REFERENCE("aload"),

    // Store reference into local variable
    STORE_REFERENCE("astore"),

    // Load double from local variable
    LOAD_DOUBLE("dload"),

    // Store double into local variable
    STORE_DOUBLE("dstore"),

    // Load float from local variable
    LOAD_FLOAT("fload"),

    // Store float into local variable
    STORE_FLOAT("fstore"),

    // Load int from local variable
    LOAD_INTEGER("iload"),

    // Store int into local variable
    STORE_INTEGER("istore"),

    // Load long from local variable
    LOAD_LONG("lload"),

    // Store long into local variable
    STORE_LONG("lstore");

    private final String representation;

    LocalVariableType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
