package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;
import me.Munchii.JasminBuilder.Types.VariableType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MethodInvocation {

    public static JasminPassable callMethod(JasminClass jasminClass, JasminMethod method, JasminPassable... arguments) {
        return callMethod(jasminClass, method, asList(arguments));
    }

    public static JasminPassable callMethod(JasminClass jasminClass, JasminMethod method, List<JasminPassable> arguments) {
        return callMethod(jasminClass.getClassName(), method.getMethodName(), arguments, method.getMethodReturnType(), method.getParamTypes());
    }

    public static JasminPassable callMethod(String className, String methodName, List<JasminPassable> arguments, DataType returnType, DataType... paramTypes) {
        return callMethod(className, methodName, arguments, returnType, asList(paramTypes));
    }

    public static JasminPassable callMethod(String className, String methodName, List<JasminPassable> arguments, DataType returnType, List<DataType> paramTypes) {
        return new JasminPassable() {
            @Override
            public List<JasminStatement> pushToStack() {
                List<JasminStatement> statements = new ArrayList<>();

                statements.add(new VariableStatement(VariableType.LOAD, "this"));

                for (JasminPassable arg : arguments) {
                    statements.addAll(arg.pushToStack());
                }

                statements.add(new MethodInvocationStatement(MethodInvocationType.INVOKE_VIRTUAL, className + "/" + methodName, returnType, paramTypes));

                return statements;
            }

            @Override
            public DataType getType() {
                return returnType;
            }
        };
    }

    public static JasminPassable callSpecialMethod(JasminClass jasminClass, JasminMethod method, JasminPassable... arguments) {
        return callSpecialMethod(jasminClass, method, asList(arguments));
    }

    public static JasminPassable callSpecialMethod(JasminClass jasminClass, JasminMethod method, List<JasminPassable> arguments) {
        return callSpecialMethod(jasminClass.getClassName(), method.getMethodName(), arguments, method.getMethodReturnType(), method.getParamTypes());
    }

    public static JasminPassable callSpecialMethod(String className, String methodName, List<JasminPassable> arguments, DataType returnType, DataType... paramTypes) {
        return callSpecialMethod(className, methodName, arguments, returnType, asList(paramTypes));
    }

    public static JasminPassable callSpecialMethod(String className, String methodName, List<JasminPassable> arguments, DataType returnType, List<DataType> paramTypes) {
        return new JasminPassable() {
            @Override
            public List<JasminStatement> pushToStack() {
                List<JasminStatement> statements = new ArrayList<>();

                for (JasminPassable arg : arguments) {
                    statements.addAll(arg.pushToStack());
                }

                statements.add(new MethodInvocationStatement(MethodInvocationType.INVOKE_SPECIAL, className + "/" + methodName, returnType, paramTypes));

                return statements;
            }

            @Override
            public DataType getType() {
                return returnType;
            }
        };
    }

}
