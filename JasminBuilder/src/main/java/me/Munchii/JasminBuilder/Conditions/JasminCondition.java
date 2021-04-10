package me.Munchii.JasminBuilder.Conditions;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Types.ConditionType;
import me.Munchii.JasminBuilder.Utils.Helper;

public class JasminCondition {

    private final JasminPassable firstValue;
    private final JasminPassable lastValue;
    private final ConditionType type;
    private final DataType valueType;

    public JasminCondition(JasminPassable firstValue, JasminPassable lastValue, ConditionType type) {
        if (!(firstValue.getType().compare(lastValue.getType())) && !(firstValue.getType().compare(DataType.VOID) || lastValue.getType().compare(DataType.VOID))) {
            Logger.error("Values must be of same type!");
            throw new AbortException();
        }

        if (
                (firstValue.getType().compare(DataType.VOID) && !(lastValue.getType() instanceof ReferenceType || lastValue.getType() instanceof ArrayType)) ||
                (lastValue.getType().compare(DataType.VOID) && !(firstValue.getType() instanceof ReferenceType || firstValue.getType() instanceof ArrayType))
        ) {
            Logger.error("Cannot compare non-reference to null!");
            throw new AbortException();
        }

        this.firstValue = firstValue;
        this.lastValue = lastValue;
        this.type = type;

        if (firstValue.getType().compare(DataType.VOID)) this.valueType = lastValue.getType();
        else if (lastValue.getType().compare(DataType.VOID))
            this.valueType = lastValue.getType(); //? TODO: Hmm. Same as above but well works
        else this.valueType = firstValue.getType();
    }

    public static JasminCondition equals(JasminPassable first, JasminPassable last) {
        return new JasminCondition(first, last, ConditionType.EQUALS);
    }

    public static JasminCondition notEquals(JasminPassable first, JasminPassable last) {
        return new JasminCondition(first, last, ConditionType.NOT_EQUALS);
    }

    public static JasminCondition lessThan(JasminPassable first, JasminPassable last) {
        return new JasminCondition(first, last, ConditionType.LESS_THAN);
    }

    public static JasminCondition lessThanEquals(JasminPassable first, JasminPassable last) {
        return new JasminCondition(first, last, ConditionType.LESS_THAN_EQUALS);
    }

    public static JasminCondition greaterThan(JasminPassable first, JasminPassable last) {
        return new JasminCondition(first, last, ConditionType.GREATER_THAN);
    }

    public static JasminCondition greaterThanEquals(JasminPassable first, JasminPassable last) {
        return new JasminCondition(first, last, ConditionType.GREATER_THAN_EQUALS);
    }

    public JasminPassable getFirstValue() {
        return firstValue;
    }

    public JasminPassable getLastValue() {
        return lastValue;
    }

    public ConditionType getType() {
        return type;
    }

    public DataType getValueType() {
        return valueType;
    }

}
