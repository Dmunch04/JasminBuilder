package me.munchii.Jasmin.instruction.type;

public enum LocalVariableInstructionType {
    // Return from subroutine
    RETURN("ret", 0),

    // Load reference from local variable
    LOAD_REFERENCE("aload", +1),

    // Store reference into local variable
    STORE_REFERENCE("astore", -1),

    // Load double from local variable
    LOAD_DOUBLE("dload", +1),

    // Store double into local variable
    STORE_DOUBLE("dstore", -1),

    // Load float from local variable
    LOAD_FLOAT("fload", +1),

    // Store float into local variable
    STORE_FLOAT("fstore", -1),

    // Load int from local variable
    LOAD_INTEGER("iload", +1),

    // Store int into local variable
    STORE_INTEGER("istore", -1),

    // Load long from local variable
    LOAD_LONG("lload", +1),

    // Store long into local variable
    STORE_LONG("lstore", -1);


    private final String representation;
    private final int stackChange;

    LocalVariableInstructionType(String representation, int stackChange) {
        this.representation = representation;
        this.stackChange = stackChange;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getStackChange() {
        return stackChange;
    }
}
