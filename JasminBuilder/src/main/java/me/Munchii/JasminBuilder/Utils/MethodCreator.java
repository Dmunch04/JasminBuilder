package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.VoidType;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Types.MethodInvokationType;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import static java.util.Arrays.asList;

public class MethodCreator
{

    public static JasminMethod CreateMainMethod ()
    {
        JasminMethod Method = new JasminMethod ("main", new VoidType (), asList (MethodAccessSpec.Public, MethodAccessSpec.Static), new ArrayType (DataType.StringInstance, 1));

        return Method;
    }

    public static JasminMethod CreateConstructorMethod ()
    {
        JasminMethod Method = new JasminMethod ("<init>", new VoidType (), MethodAccessSpec.Public);
        Method.AddComment ("Call super method")
                .AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeSpecial, "java/lang/Object/<init>", new VoidType ());

        return Method;
    }

}
