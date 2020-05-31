package me.Munchii.JasminBuilder.Types;

public enum ObjectType {

    // Create new array of reference
    A_NEW_ARRAY("anewarray"),

    // Check whether object is of given type
    CHECK_CAST("checkcast"),

    // Determine if object is of given type
    INSTANCE_OF("instanceof"),

    // Create new object
    NEW("new");

    private final String representation;

    ObjectType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
