package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Instructions.InitFieldInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

public class Main
{

    public static void main (String... Args)
    {
        // TODO: Find a way to push and store all different value types -> mostly done
        // TODO: Find a way to represent arrays and push items into the array and load them from array -> done?
        // TODO: How can we stop/prevent the user from chaining on the `JasminMethod` constructor method?
        // TODO: How should conditions work?
        // TODO: Make instructions for control-flow, loops, field init, etc.
        // TODO: Allow for multiple AccessSpec in constructor for `Field`, `Method` and `Class`

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
        JasminBlock Block = new JasminBlock ("BLOCK");
        JasminArray Array = new JasminArray ("a", DataType.Integer, 5);
        Array.AddElement (new JasminValue (10, DataType.Integer));
        Block.DeclareVariable (Array);
        Block.StoreVariable (Array, new JasminValue (5, DataType.Integer));
        Method.AddBlock (Block);

        // TODO: This here will NOT work because blocks are evaluated after the methods main block, so the id would just be -1
        Method.DeclareVariable (new JasminVariable ("b", Array.GetElement (0)));

        Class.AddField (Field);
        Class.AddMethod (Method);
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
