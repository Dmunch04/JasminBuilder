package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import static java.util.Arrays.asList;

public class MethodCreator {

    public static JasminMethod createMainMethod() {
        return new JasminMethod("main", DataType.VOID, asList(MethodAccessSpec.PUBLIC, MethodAccessSpec.STATIC), new ArrayType(DataType.STRING_INSTANCE, 1));
    }

    public static JasminMethod createConstructorMethod() {
        JasminMethod method = new JasminMethod("<init>", DataType.VOID, MethodAccessSpec.PUBLIC);
        method.addComment("Call super method")
                .addNoParameterStatement(NoParameterType.LOAD_REFERENCE_FROM_LOCAL_VARIABLE_0)
                .addMethodInvocationStatement(MethodInvocationType.INVOKE_SPECIAL, "java/lang/Object/<init>", DataType.VOID);

        return method;
    }

}
