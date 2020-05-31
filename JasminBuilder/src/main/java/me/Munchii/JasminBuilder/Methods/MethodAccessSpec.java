package me.Munchii.JasminBuilder.Methods;

public enum MethodAccessSpec {

    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    STATIC("static"),
    FINAL("final"),
    SYNCHRONIZED("synchronized"),
    NATIVE("native"),
    ABSTRACT("abstract");

    private final String representation;

    MethodAccessSpec(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
