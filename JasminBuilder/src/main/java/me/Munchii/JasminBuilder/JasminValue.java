package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * The `JasminValue` class represents a single anonymous value in Jasmin
 */
public class JasminValue implements Builder, JasminPassable {

    private final Object value;
    private final DataType type;

    /**
     * @param value The value
     * @param type  The data type of the value
     */
    public JasminValue(Object value, DataType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toOutputString() {
        if (value instanceof String) {
            return String.format("\"%s\"", value.toString());
        } else if (value == null) {
            return "";
        } else if (value == Boolean.TRUE) {
            return "1";
        } else if (value == Boolean.FALSE) {
            return "0";
        }

        return value.toString();
    }

    @Override
    public List<JasminStatement> pushToStack() {
        return asList(Helper.pushValueToStack(this));
    }

    /**
     * @return The value
     */
    public Object getValue() {
        return value;
    }

    @Override
    public DataType getType() {
        return type;
    }

}
