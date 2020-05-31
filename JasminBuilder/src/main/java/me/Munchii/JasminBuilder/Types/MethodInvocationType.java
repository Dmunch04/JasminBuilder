package me.Munchii.JasminBuilder.Types;

public enum MethodInvocationType {

    // ??
    INVOKE_NON_VIRTUAL("invokenonvirtual"),

    // Invoke a class (static) method
    INVOKE_STATIC("invokestatic"),

    // Invoke instance method; dispatch based on class
    INVOKE_VIRTUAL("invokevirtual"),

    // ??
    INVOKE_SPECIAL("invokespecial");

    private final String representation;

    MethodInvocationType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
