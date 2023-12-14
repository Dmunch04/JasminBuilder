package me.munchii.Jasmin.instruction.type;

public enum MethodInvokationInstructionType {
    // ??
    INVOKE_NON_VIRTUAL("invokenonvirtual", -1),

    // Invoke a class (static) method
    INVOKE_STATIC("invokestatic", 0),

    // Invoke instance method; dispatch based on class
    INVOKE_VIRTUAL("invokevirtual", -1),

    // ??
    INVOKE_SPECIAL("invokespecial", -1);

    private final String representation;
    private final int stackChange;

    MethodInvokationInstructionType(String representation, int stackChange) {
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
