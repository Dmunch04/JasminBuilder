package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.LimitType;

public class LimitStatement implements JasminStatement {

    private final LimitType type;
    private final int amount;

    public LimitStatement(LimitType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toOutputString() {
        return ".limit " + type.getRepresentation() + " " + amount;
    }

    public LimitType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

}
