package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.FieldManipulationType;

public class FieldManipulationStatement implements JasminStatement {

    private final FieldManipulationType type;
    private final String fieldSpec;
    private final String descriptor;

    public FieldManipulationStatement(FieldManipulationType type, String fieldSpec, String descriptor) {
        this.type = type;
        this.fieldSpec = fieldSpec;
        this.descriptor = descriptor;
    }

    @Override
    public String toOutputString() {
        StringBuilder builder = new StringBuilder();
        builder.append(type.getRepresentation()).append(" ");
        builder.append(fieldSpec).append(" ");
        builder.append(descriptor);

        return builder.toString();
    }

}
