package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Types.LoadConstantType;

public class LoadConstantStatement implements JasminStatement {

    private final LoadConstantType type;
    private final JasminValue constant;

    public LoadConstantStatement(LoadConstantType type, JasminValue constant) {
        this.type = type;
        this.constant = constant;
    }

    @Override
    public String toOutputString() {
        return type.getRepresentation() + " " + constant.toOutputString();
    }

}
