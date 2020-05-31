package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Types.VariableType;
import me.Munchii.JasminBuilder.Variable;

public class VariableStatement implements JasminStatement {

    private final VariableType type;
    private final Variable variable;
    private final JasminPassable value;
    private final String name;

    public VariableStatement(VariableType type, Variable variable) {
        this(type, variable, variable.getValue());
    }

    public VariableStatement(VariableType type, Variable variable, JasminPassable value) {
        this.type = type;
        this.variable = variable;
        this.value = value;
        this.name = variable.getName();
    }

    //* Only for loading variables
    public VariableStatement(VariableType type, String name) {
        this.type = type;
        this.variable = null;
        this.value = null;
        this.name = name;
    }

    @Override
    public String toOutputString() {
        if (type != VariableType.LOAD) {
            StringBuilder builder = new StringBuilder();

            for (JasminStatement statement : value.pushToStack())
                builder.append(statement.toOutputString()).append("\n");

            builder.append(variable.store().toOutputString());

            return builder.toString();
        }

        //* Don't worry about this, as it won't be written to the code
        return "";
    }

    public VariableType getType() {
        return type;
    }

    public Variable getVariable() {
        return variable;
    }

    public JasminPassable getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public VariableReference getReference() {
        return new VariableReference(name);
    }
}
