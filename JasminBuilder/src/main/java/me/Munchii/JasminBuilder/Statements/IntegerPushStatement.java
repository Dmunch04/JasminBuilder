package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.IntegerPushType;

public class IntegerPushStatement implements JasminStatement {

    private final IntegerPushType type;
    private final int value;

    public IntegerPushStatement(IntegerPushType type, int value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toOutputString() {
        return type.getRepresentation() + " " + value;
    }

}
