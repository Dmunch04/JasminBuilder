package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

public class Main
{

    // TODO: Implement local variables

    public static void main (String... Args)
    {
        JasminMethod Method = new JasminMethod (MethodAccessSpec.Public, "foo", DataType.Void, DataType.StringInstance, DataType.Float, DataType.Integer);
        Method.AddInstruction (new PrintInstruction (new JasminValue ("Hello, World", DataType.String)));
        Method.AddLocalsLimit (1);
        // TODO: Maybe implement a method on the `JasminMethod` class, to automatically add return statement, depending on the `DataType` given
        Method.AddNoParameterStatement (NoParameterType.Return);

        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "MyClass");
        Class.AddField (new JasminField (FieldAccessSpec.Public, "bar", DataType.Integer));
        Class.AddMethod (MethodCreator.CreateConstructorMethod ());
        Class.AddMethod (MethodCreator.CreateMainMethod ());
        Class.AddMethod (Method);

        JasminFile File = new JasminFile ("out/", "Test");
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
