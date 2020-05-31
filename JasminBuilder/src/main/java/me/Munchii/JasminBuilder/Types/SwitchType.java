package me.Munchii.JasminBuilder.Types;

public enum SwitchType {

    // Access jump table by key match and jump
    LOOKUP_SWITCH("lookupswitch"),

    // Access jump table by index and jump
    TABLE_SWITCH("tableswitch");

    private final String representation;

    SwitchType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
