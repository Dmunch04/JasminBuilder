package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import java.util.ArrayList;
import java.util.List;

public class ExpressionBuilder implements Builder, JasminPassable {

    private final JasminPassable baseValue;
    private final List<JasminStatement> statements;
    private final DataType type;

    public ExpressionBuilder(JasminPassable baseValue) {
        this.baseValue = baseValue;
        this.statements = new ArrayList<>();
        statements.addAll(baseValue.pushToStack());
        this.type = baseValue.getType();
    }

    public ExpressionBuilder negate() {
        switch (type.getType()) {
            // TODO: Implement more types?
            case DOUBLE:
                statements.add(new NoParameterStatement(NoParameterType.NEGATE_DOUBLE));
                break;
            case FLOAT:
                statements.add(new NoParameterStatement(NoParameterType.NEGATE_FLOAT));
                break;
            case INTEGER:
                statements.add(new NoParameterStatement(NoParameterType.NEGATE_INTEGER));
                break;
            case LONG:
                statements.add(new NoParameterStatement(NoParameterType.NEGATE_LONG));
                break;
        }

        return this;
    }

    public ExpressionBuilder add(JasminPassable other) {
        if (other.getType() != type)
            throw new IllegalArgumentException("Second value not of same type as base value!");

        statements.addAll(other.pushToStack());

        switch (type.getType()) {
            // TODO: Implement more types?
            case DOUBLE:
                statements.add(new NoParameterStatement(NoParameterType.ADD_DOUBLE));
                break;
            case FLOAT:
                statements.add(new NoParameterStatement(NoParameterType.ADD_FLOAT));
                break;
            case INTEGER:
                statements.add(new NoParameterStatement(NoParameterType.ADD_INTEGER));
                break;
            case LONG:
                statements.add(new NoParameterStatement(NoParameterType.ADD_LONG));
                break;
        }

        return this;
    }

    public ExpressionBuilder subtract(JasminPassable other) {
        if (other.getType() != type)
            throw new IllegalArgumentException("Second value not of same type as base value!");

        statements.addAll(other.pushToStack());

        switch (type.getType()) {
            // TODO: Implement more types
            case DOUBLE:
                statements.add(new NoParameterStatement(NoParameterType.SUBTRACT_DOUBLE));
                break;
            case FLOAT:
                statements.add(new NoParameterStatement(NoParameterType.SUBTRACT_FLOAT));
                break;
            case INTEGER:
                statements.add(new NoParameterStatement(NoParameterType.SUBTRACT_INTEGER));
                break;
            case LONG:
                statements.add(new NoParameterStatement(NoParameterType.SUBTRACT_LONG));
                break;
        }

        return this;
    }

    public ExpressionBuilder multiply(JasminPassable other) {
        if (other.getType() != type)
            throw new IllegalArgumentException("Second value not of same type as base value!");

        statements.addAll(other.pushToStack());

        switch (type.getType()) {
            case DOUBLE:
                statements.add(new NoParameterStatement(NoParameterType.MULTIPLY_DOUBLE));
                break;
            case FLOAT:
                statements.add(new NoParameterStatement(NoParameterType.MULTIPLY_FLOAT));
                break;
            case INTEGER:
                statements.add(new NoParameterStatement(NoParameterType.MULTIPLY_INTEGER));
                break;
            case LONG:
                statements.add(new NoParameterStatement(NoParameterType.MULTIPLY_LONG));
                break;
        }

        return this;
    }

    public ExpressionBuilder divide(JasminPassable other) {
        if (other.getType() != type)
            throw new IllegalArgumentException("Second value not of same type as base value!");

        statements.addAll(other.pushToStack());

        switch (type.getType()) {
            case DOUBLE:
                statements.add(new NoParameterStatement(NoParameterType.DIVIDE_DOUBLE));
                break;
            case FLOAT:
                statements.add(new NoParameterStatement(NoParameterType.DIVIDE_FLOAT));
                break;
            case INTEGER:
                statements.add(new NoParameterStatement(NoParameterType.DIVIDE_INTEGER));
                break;
            case LONG:
                statements.add(new NoParameterStatement(NoParameterType.DIVIDE_LONG));
                break;
        }

        return this;
    }

    public static ExpressionBuilder negate(JasminPassable Value1) {
        return new ExpressionBuilder(Value1).negate();
    }

    public static ExpressionBuilder add(JasminPassable Value1, JasminPassable Value2) {
        return new ExpressionBuilder(Value1).add(Value2);
    }

    public static ExpressionBuilder subtract(JasminPassable Value1, JasminPassable Value2) {
        return new ExpressionBuilder(Value1).subtract(Value2);
    }

    public static ExpressionBuilder multiply(JasminPassable Value1, JasminPassable Value2) {
        return new ExpressionBuilder(Value1).multiply(Value2);
    }

    public static ExpressionBuilder divide(JasminPassable Value1, JasminPassable Value2) {
        return new ExpressionBuilder(Value1).divide(Value2);
    }

    @Override
    public String toOutputString() {
        return "";
    }

    @Override
    public List<JasminStatement> pushToStack() {
        return statements;
    }

    @Override
    public DataType getType() {
        return type;
    }

}
