package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.NoParameterType;

public class NoParameterStatement implements JasminStatement {

    private final NoParameterType type;

    public NoParameterStatement(NoParameterType type) {
        this.type = type;
    }

    @Override
    public String toOutputString() {
        return type.getRepresentation();
    }

}
