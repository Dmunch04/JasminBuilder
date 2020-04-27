package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Utils.ExpressionBuilder;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

public class Main
{

    public static void main (String... Args)
    {
        // TODO: Find a way to push and store all different value types - mostly done
        // TODO: Find a way to represent arrays and push items into the array and load them from array
        // TODO: How can we stop/prevent the user from chaining on the `JasminMethod` constructor method?
        // TODO: How should conditions work?
        // TODO: Make instructions for control-flow, loops, field init, etc.

        // TODO: Fix tests to use new variable system

        //? Handle fields better?
        //? Maybe implement a method on the `JasminMethod` class, to automatically add return statement, depending on the `DataType` given
        //? Allow for variables to be defined by stack value?

        JasminFile File = new JasminFile ("/", "Test");
        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "Foo");

        Class.AddMethod (MethodCreator.CreateConstructorMethod ());
        Class.AddMethod (MethodCreator.CreateMainMethod ());

        //* Method declaration and building should from now on be SEPARATED! Else you can't get variables like `this`, `arg1`, `arg2`, .. `argN`
        JasminMethod Method = new JasminMethod (MethodAccessSpec.Public, "Bar", DataType.Void, DataType.Integer);
        Method.DeclareVariable (new JasminVariable ("Yeet", ExpressionBuilder.Negate (ExpressionBuilder.Add (Method.GetVariable ("arg1"), new JasminValue (10, DataType.Integer)))));

        Class.AddMethod (Method);
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
