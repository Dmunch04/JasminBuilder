package me.munchii.Jasmin.util;

import me.munchii.Jasmin.type.*;

public class DataTypeConversion {
    public static String getJavaTypeName(IDataType type) {
        if (type instanceof ValueType valueType) {
            return switch (valueType) {
                case BOOLEAN -> "boolean";
                case BYTE -> "byte";
                case CHAR -> "char";
                case DOUBLE -> "double";
                case FLOAT -> "float";
                case INTEGER -> "integer";
                case LONG -> "long";
                case SHORT -> "short";
            };
        } else if (type instanceof VoidType) {
            return "void";
        } else if (type instanceof ReferenceType referenceType) {
            String value = referenceType.getRepresentation();
            int index = value.lastIndexOf("/");
            return value.substring(index + 1);
        } else if (type instanceof ClassType classType) {
            String value = classType.getRepresentation().replace(";", "");
            int index = value.lastIndexOf("/");
            return value.substring(index + 1);
        } else if (type instanceof ArrayType arrayType) {
            String dimensions = "[]".repeat(arrayType.getDimensions());
            return getJavaTypeName(arrayType.getType()) + dimensions;
        } else {
            return "";
        }
    }
}
