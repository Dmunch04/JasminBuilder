package me.Munchii.JasminBuilder.Fields;

public enum FieldAccessSpec {

    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    STATIC("static"),
    FINAL("final"),
    VOLATILE("volatile"),
    TRANSIENT("transient");

    private final String representation;

    FieldAccessSpec(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
