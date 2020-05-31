package me.Munchii.JasminBuilder.Statements;

public class IncrementStatement implements JasminStatement {

    private final int variableIndex;
    private final int amount;

    public IncrementStatement(int variableIndex, int amount) {
        this.variableIndex = variableIndex;
        this.amount = amount;
    }

    @Override
    public String toOutputString() {
        return "iinc " + variableIndex + " " + amount;
    }

}
