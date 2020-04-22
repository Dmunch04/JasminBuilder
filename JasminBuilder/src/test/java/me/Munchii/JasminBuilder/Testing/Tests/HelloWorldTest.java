package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.JasminFile;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

public class HelloWorldTest implements TestCase
{

    @Override
    public void Run ()
    {
        JasminFile File = new JasminFile ("Out/", "HelloWorld");

        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "HelloWorld");

        JasminMethod MainMethod = MethodCreator.CreateMainMethod ()
                .AddInstruction (new PrintInstruction (new JasminValue ("Hello, World!", DataType.String)));

        Class.AddMethod (MainMethod);

        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
        File.Write ();
    }

    @Override
    public String GetName ()
    {
        return "HelloWorldTest";
    }

}
