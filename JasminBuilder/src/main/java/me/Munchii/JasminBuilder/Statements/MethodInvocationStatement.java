package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.List;

public class MethodInvocationStatement implements JasminStatement {

    private final MethodInvocationType type;
    private final String methodName;
    private final DataType methodReturnType;
    private final List<DataType> paramTypes;

    public MethodInvocationStatement(MethodInvocationType type, JasminMethod method) {
        this(type, method.getMethodName(), method.getMethodReturnType(), method.getParamTypes());
    }

    public MethodInvocationStatement(MethodInvocationType type, String methodName, DataType methodReturnType, List<DataType> paramTypes) {
        this.type = type;
        this.methodName = methodName;
        this.methodReturnType = methodReturnType;
        this.paramTypes = paramTypes;
    }

    @Override
    public String toOutputString() {
        return type.getRepresentation() + " " + Helper.makeMethodSpec(methodName, methodReturnType, paramTypes);
    }

}
