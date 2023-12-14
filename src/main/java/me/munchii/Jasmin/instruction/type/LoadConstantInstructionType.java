package me.munchii.Jasmin.instruction.type;

public enum LoadConstantInstructionType {
    // Push item from run-time constant pool
    LOAD_CONSTANT("ldc", +1),

    // Push item from run-time constant pool (wide index)
    LOAD_CONSTANT_WIDE("ldc_w", +1), // For `long` and `double`

    LOAD_CONSTANT_2("ldc2", +1),

    LOAD_CONSTANT_2_WIDE("ldc2_w", +1);

    private final String representation;
    private final int stackChange;

    LoadConstantInstructionType(String representation, int stackChange) {
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
