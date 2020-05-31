package me.Munchii.JasminBuilder.Classes;

public enum ClassAccessSpec {

    PUBLIC("public"),
    FINAL("final"),
    SUPER("super"),
    INTERFACE("interface"),
    ABSTRACT("abstract");

    private final String representation;

    ClassAccessSpec(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
