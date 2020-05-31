package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.ObjectType;

public class ObjectStatement implements JasminStatement {

    private final ObjectType type;
    private final String className;

    public ObjectStatement(ObjectType type, String className) {
        this.type = type;
        this.className = className;
    }

    @Override
    public String toOutputString() {
        return type.getRepresentation() + " " + className;
    }

}
