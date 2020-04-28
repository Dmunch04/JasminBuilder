package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.MethodInvokationType;
import me.Munchii.JasminBuilder.Types.NoParameterType;

public class MethodCreator
{

    public static JasminMethod CreateMainMethod ()
    {
        JasminMethod Method = new JasminMethod (MethodAccessSpec.Static, "main", DataType.Void, DataType.MakeArray (1, DataType.StringInstance))
                .AddAccessSpec (MethodAccessSpec.Public);

        return Method;
    }

    public static JasminMethod CreateConstructorMethod ()
    {
        JasminMethod Method = new JasminMethod (MethodAccessSpec.Public, "<init>", DataType.Void);
        Method.AddComment ("Call super method")
                .AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeSpecial, "java/lang/Object/<init>", DataType.Void);

        return Method;
    }

}
