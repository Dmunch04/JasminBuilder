package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Instructions.InitFieldInstruction;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.References.VariableReference;
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
        // TODO: Allow for multiple AccessSpec in constructor for `Field`, `Method` and `Class`
        // TODO: Fix arrays and figure out how they should work

        // TODO: Fix tests to use new variable system

        //? Handle fields better?
        //? Maybe implement a method on the `JasminMethod` class, to automatically add return statement, depending on the `DataType` given
        //? Allow for variables to be defined by stack value?

        JasminFile File = new JasminFile ("/", "Test");
        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "Foo");
        JasminField Field = new JasminField (FieldAccessSpec.Public, "Hmm", DataType.Integer, new JasminValue (5, DataType.Integer));

        Class.AddMethod (MethodCreator.CreateConstructorMethod ().AddInstruction (new InitFieldInstruction (Class, Field)));
        Class.AddMethod (MethodCreator.CreateMainMethod ());

        //* Method declaration and building should from now on be SEPARATED! Else you can't get variables like `this`, `arg1`, `arg2`, .. `argN`
        JasminMethod Method = new JasminMethod (MethodAccessSpec.Public, "Bar", DataType.Void, DataType.Integer);
        JasminVariable Variable = new JasminVariable ("Yeet", ExpressionBuilder.Negate (ExpressionBuilder.Add (Method.GetVariable ("arg1"), new JasminValue (10, DataType.Integer))));
        Method.DeclareVariable (Variable);
        Method.AddInstruction (new PrintInstruction (Variable));
        Method.AddInstruction (new PrintInstruction (Method.GetVariable (new VariableReference ("arg1"))));

        Class.AddField (Field);
        Class.AddMethod (Method);
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
