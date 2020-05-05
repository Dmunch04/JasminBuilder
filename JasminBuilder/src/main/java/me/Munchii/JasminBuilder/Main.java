package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.IntegerType;
import me.Munchii.JasminBuilder.DataTypes.VoidType;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Instructions.InitFieldInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

import static java.util.Arrays.asList;

public class Main
{

    public static void main (String... Args)
    {
        // TODO: Find a way to push and store all different value types -> mostly done
        // TODO: Find a way to represent arrays and push items into the array and load them from array -> done?
        // TODO: How can we stop/prevent the user from chaining on the `JasminMethod` constructor method?
        // TODO: How should conditions work?
        // TODO: Make instructions for control-flow, loops, field init, etc.

        // TODO: Fix tests to use new variable system

        //? Handle fields better?
        //? Maybe implement a method on the `JasminMethod` class, to automatically add return statement, depending on the `DataType` given
        //? Allow for variables to be defined by stack value?

        JasminFile File = new JasminFile ("/", "Test");
        JasminClass Class = new JasminClass ("Foo", ClassAccessSpec.Public);
        JasminField Field = new JasminField ("Hmm", new IntegerType (), new JasminValue (5, new IntegerType ()), FieldAccessSpec.Public);

        Class.AddMethod (MethodCreator.CreateConstructorMethod ().AddInstruction (new InitFieldInstruction (Class, Field)));
        Class.AddMethod (MethodCreator.CreateMainMethod ());

        //* Method declaration and building should from now on be SEPARATED! Else you can't get variables like `this`, `arg1`, `arg2`, .. `argN`
        JasminMethod Method = new JasminMethod ("Bar", new VoidType (), asList (MethodAccessSpec.Public), new IntegerType ());
        JasminBlock Block = new JasminBlock ("BLOCK");
        JasminArray Array = new JasminArray ("a", new IntegerType (), 5);
        Array.AddElement (new JasminValue (10, new IntegerType ()));
        Block.DeclareVariable (Array);
        Block.StoreVariable (Array, new JasminValue (5, new IntegerType ()));
        Method.AddBlock (Block);

        // TODO: This here will NOT work because blocks are evaluated after the methods main block, so the id would just be -1
        // TODO: Find a good fix
        Method.DeclareVariable (new JasminVariable ("b", Array.GetElement (0)));

        Class.AddField (Field);
        Class.AddMethod (Method);
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
