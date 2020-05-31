package me.Munchii.JasminBuilder.Types;

// TODO: Maybe rename this + it's statement (`IntegerPushStatement`) + everything related to this
public enum IntegerPushType {

    // Push byte
    BI_PUSH("bipush"),

    // Push short
    SI_PUSH("sipush");

    private final String representation;

    IntegerPushType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
