package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.DataTypes.DataType;

public class MultiANewArrayStatement implements JasminStatement {

    private final DataType type;
    private final int dimensions;

    public MultiANewArrayStatement(DataType type, int dimensions) {
        this.type = type;
        this.dimensions = dimensions;
    }

    @Override
    public String toOutputString() {
        return "multianewarray " + type.getRepresentation() + " " + dimensions;
    }

}
