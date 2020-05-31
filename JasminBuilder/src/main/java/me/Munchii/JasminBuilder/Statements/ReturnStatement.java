package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.JasminPassable;

public class ReturnStatement implements JasminStatement {

    private final JasminPassable value;

    public ReturnStatement() {
        this(null);
    }

    public ReturnStatement(JasminPassable value) {
        this.value = value;
    }

    @Override
    public String toOutputString() {
        if (value == null)
            return "return";

        StringBuilder builder = new StringBuilder();
        for (JasminStatement statement : value.pushToStack()) {
            builder.append(statement.toOutputString()).append("\n\t");
        }
        builder.append("\n").append("\t");

        switch (value.getType().getType()) {
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case SHORT:
            case INTEGER:
                builder.append("ireturn");
                break;

            case FLOAT:
                builder.append("freturn");
                break;

            case DOUBLE:
                builder.append("dreturn");
                break;

            case LONG:
                builder.append("lreturn");
                break;

            case ARRAY:
            case REFERENCE:
                builder.append("areturn");
                break;

            case VOID:
                builder.append("return");
                break;
        }

        return builder.toString();
    }

}
