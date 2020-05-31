package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.DataTypes.DataType;

public class NewArrayStatement implements JasminStatement {

    private final DataType type;

    public NewArrayStatement(DataType type) {
        this.type = type;
    }

    @Override
    public String toOutputString() {
        return "newarray " + type.getRepresentation();
    }

}
