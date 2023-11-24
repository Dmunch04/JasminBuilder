package me.munchii.Jasmin.util;

import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.type.IDataType;
import me.munchii.Jasmin.type.ReferenceType;

import java.util.Arrays;

public class MethodSpec {
    public static String makeMethodSpec(ReferenceType classReference, JasminMethod method) {
        return makeMethodSpec(classReference, method.methodName, method.returnType, method.paramTypes.toArray(new IDataType[0]));
    }

    public static String makeMethodSpec(ReferenceType classReference, String methodName, IDataType returnType, IDataType... paramTypes) {
        StringBuilder builder = new StringBuilder();
        builder.append(classReference.getRepresentation()).append("/")
                .append(methodName);

        builder.append("(");
        Arrays.stream(paramTypes).forEach(param -> builder.append(param.getRepresentation()));
        builder.append(")")
                .append(returnType.getRepresentation());

        return builder.toString();
    }
}
