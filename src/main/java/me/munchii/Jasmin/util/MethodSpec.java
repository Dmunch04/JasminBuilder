package me.munchii.Jasmin.util;

import me.munchii.Jasmin.method.ConstructorMethod;
import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.type.JasminType;
import me.munchii.Jasmin.type.ReferenceType;
import me.munchii.Jasmin.type.ReturnableType;
import me.munchii.Jasmin.type.ValueType;

import java.util.Arrays;
import java.util.List;

public class MethodSpec {
    public static String makeMethodSpec(ReferenceType classReference, JasminMethod method) {
        return makeMethodSpec(classReference, method.methodName, method.returnType, method.paramTypes);
    }

    public static String makeMethodSpec(ReferenceType classReference, ConstructorMethod method) {
        return makeMethodSpec(classReference, method.methodName, method.returnType, method.paramTypes);
    }

    public static String makeMethodSpec(ReferenceType classReference, String methodName, ReturnableType returnType, List<ValueType> paramTypes) {
        StringBuilder builder = new StringBuilder();
        builder.append(classReference.getRepresentation()).append("/")
                .append(methodName);

        builder.append("(");
        paramTypes.forEach(param -> builder.append(param.getRepresentation()));
        builder.append(")")
                .append(returnType.getRepresentation());

        return builder.toString();
    }
}
