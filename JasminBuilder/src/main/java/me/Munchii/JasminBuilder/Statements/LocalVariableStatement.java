package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.LocalVariableType;

public class LocalVariableStatement implements JasminStatement {

    private final LocalVariableType type;
    private final int variableIndex;

    public LocalVariableStatement(LocalVariableType type, int variableIndex) {
        this.type = type;
        this.variableIndex = variableIndex;
    }

    @Override
    public String toOutputString() {
        return type.getRepresentation() + " " + variableIndex;
    }

}
