package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Helper {

    public static String makeMethodSpec(String methodName, DataType methodReturnType, List<DataType> paramTypes) {
        StringBuilder builder = new StringBuilder();
        builder.append(methodName).append("(");

        for (DataType type : paramTypes) {
            builder.append(type.getRepresentation());
        }

        builder.append(")").append(methodReturnType.getRepresentation());

        return builder.toString();
    }

    public static String makeFieldSpec(String className, String fieldName) {
        return className + "/" + fieldName;
    }

    public static List<DataType> dataTypeArrayToList(DataType[] types) {
        if (types != null)
            return asList(types);
        else
            return new ArrayList<>();
    }

    public static String getDataTypeName(DataType type) {
        switch (type.getType()) {
            case BOOLEAN:
                return "bool";
            case BYTE:
                return "byte";
            case CHAR:
                return "char";
            case DOUBLE:
                return "double";
            case FLOAT:
                return "float";
            case INTEGER:
                return "int";
            case LONG:
                return "long";
            case VOID:
                return "void";
            case SHORT:
                return "short";
            case ARRAY:
                return getDataTypeArrayName(type);
            case REFERENCE:
                return getCustomDataTypeName(type);
        }

        throw new IllegalArgumentException("Could not match value");
    }

    public static String getCustomDataTypeName(DataType type) {
        String representation = type.getRepresentation();
        representation = representation.replace("[", "");

        if (representation.startsWith("L") && representation.endsWith(";"))
            representation = representation.substring(1, representation.length() - 1);

        String[] classPaths = representation.split("/");

        return classPaths[classPaths.length - 1];
    }

    public static String getDataTypeArrayName(DataType type) {
        String representation = type.getRepresentation();
        int Dimensions = representation.length() - representation.replace("[", "").length();
        representation = representation.replace("[", "");

        if (representation.startsWith("L") && representation.endsWith(";"))
            representation = representation.substring(1, representation.length() - 1);

        String[] classPaths = representation.split("/");
        String className = classPaths[classPaths.length - 1];

        if (className.equals("String") || className.equals("Object")) {
            return className.toLowerCase() + "[]".repeat(Dimensions);
        }

        // TODO: Fix that primitive types just gets displayed as their representation

        else {
            return representation + "[]".repeat(Dimensions);
        }
    }

    public static JasminStatement pushValueToStack(JasminValue value) {
        switch (value.getType().getType()) {
            case BYTE: {
                byte newValue = (byte) value.getValue();

                return new IntegerPushStatement(IntegerPushType.BI_PUSH, newValue);
            }

            case CHAR: {
                return new IntegerPushStatement(IntegerPushType.BI_PUSH, charToInt((char) value.getValue()));
            }

            case DOUBLE: {
                double newValue = (double) value.getValue();

                if (newValue == 0.0d) return new NoParameterStatement(NoParameterType.PUSH_DOUBLE_0);
                if (newValue == 1.0d) return new NoParameterStatement(NoParameterType.PUSH_DOUBLE_1);
                else
                    return new LoadConstantStatement(LoadConstantType.LOAD_CONSTANT_2_WIDE, new JasminValue(newValue, DataType.DOUBLE));
            }

            case FLOAT: {
                float newValue = (float) value.getValue();

                if (newValue == 0.0f) return new NoParameterStatement(NoParameterType.FLOAT_CONSTANT_0);
                if (newValue == 1.0f) return new NoParameterStatement(NoParameterType.FLOAT_CONSTANT_1);
                if (newValue == 2.0f) return new NoParameterStatement(NoParameterType.FLOAT_CONSTANT_2);
                else
                    return new LoadConstantStatement(LoadConstantType.LOAD_CONSTANT, new JasminValue(newValue, DataType.FLOAT));
            }

            case INTEGER: {
                int newValue = (int) value.getValue();

                if (newValue == -1) return new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_NEGATIVE_1);
                if (newValue == 0) return new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_0);
                if (newValue == 1) return new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_1);
                if (newValue == 2) return new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_2);
                if (newValue == 3) return new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_3);
                if (newValue == 4) return new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_4);
                if (newValue == 5) return new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_5);
                else return new IntegerPushStatement(IntegerPushType.BI_PUSH, newValue);
            }

            case LONG: {
                long newValue = Long.parseLong(String.valueOf(value.getValue()));

                if (newValue == 0) return new NoParameterStatement(NoParameterType.LONG_CONSTANT_0);
                if (newValue == 1) return new NoParameterStatement(NoParameterType.LONG_CONSTANT_1);
                else
                    return new LoadConstantStatement(LoadConstantType.LOAD_CONSTANT_2_WIDE, new JasminValue(newValue, DataType.LONG));
            }

            // AKA null
            // TODO: Should we introduce a `Null` type? If so, Couldn't it get confused with `Void` in some cases?
            case VOID: {
                return new NoParameterStatement(NoParameterType.PUSH_NULL);
            }

            case SHORT: {
                short newValue = (short) value.getValue();

                return new IntegerPushStatement(IntegerPushType.SI_PUSH, newValue);
            }

            // TODO: Is this the correct way?
            case REFERENCE: {
                return new LoadConstantStatement(LoadConstantType.LOAD_CONSTANT, new JasminValue(String.valueOf(value.getValue()), value.getType()));
            }

            case ARRAY: {
                //? TODO: Should this be allowed?
                return new ObjectStatement(ObjectType.A_NEW_ARRAY, value.getType().getRepresentation());
            }

            case BOOLEAN: {
                boolean newValue = (boolean) value.getValue();

                // true = 1
                // false = 0
                return newValue ? new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_1) : new NoParameterStatement(NoParameterType.INTEGER_CONSTANT_0);
            }
        }

        throw new IllegalArgumentException("Could not match value");
    }

    public static String conditionTypeToRepresentation(ConditionType type) {
        switch (type) {
            case EQUALS:
                return "==";
            case NOT_EQUALS:
                return "!=";
            case LESS_THAN:
                return "<";
            case LESS_THAN_EQUALS:
                return "<=";
            case GREATER_THAN:
                return ">";
            case GREATER_THAN_EQUALS:
                return ">=";
            default:
                return "?";
        }
    }

    public static int charToInt(char c) {
        return c;
    }

}
