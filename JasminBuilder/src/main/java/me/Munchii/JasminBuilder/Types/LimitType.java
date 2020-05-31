package me.Munchii.JasminBuilder.Types;

public enum LimitType {

    STACK("stack"),
    LOCALS("locals");

    private final String representation;

    LimitType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
