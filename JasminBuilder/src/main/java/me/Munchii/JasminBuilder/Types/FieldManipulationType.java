package me.Munchii.JasminBuilder.Types;

public enum FieldManipulationType {

    // Fetch field from object
    GET_FIELD("getfield"),

    // Get static field from class
    GET_STATIC("getstatic"),

    // Set field in object
    PUT_FIELD("putfield"),

    // Set static field in class
    PUT_STATIC("putstatic");

    private final String representation;

    FieldManipulationType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
