package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.SwitchType;

public class SwitchStatement implements JasminStatement {

    private final SwitchType type;

    public SwitchStatement(SwitchType type) {
        this.type = type;
    }

    @Override
    public String toOutputString() {
        return type.getRepresentation();
    }

}
