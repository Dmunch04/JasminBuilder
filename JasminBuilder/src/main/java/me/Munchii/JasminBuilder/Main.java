package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.BranchType;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

public class Main
{

    // TODO: Implement local variables - partly done
    // TODO: Implement blocks (for switch, for, while, if, etc. statements) - mostly done
    // TODO: Allow for variables to be defined by stack value?

    public static void main (String... Args)
    {
        /*
        JasminMethod Method = new JasminMethod (MethodAccessSpec.Public, "foo", DataType.Void, DataType.StringInstance, DataType.Float, DataType.Integer);
        Method.AddInstruction (new PrintInstruction (new JasminValue ("Hello, World", DataType.String)));
        Method.AddLocalsLimit (1);
        // TODO: Maybe implement a method on the `JasminMethod` class, to automatically add return statement, depending on the `DataType` given
        Method.AddNoParameterStatement (NoParameterType.Return);

        JasminBlock Block = new JasminBlock ("Test")
                .AddStatement (new NoParameterStatement (NoParameterType.Nop));

        Method.AddBlock (Block);

        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "MyClass");

        JasminField field = new JasminField (FieldAccessSpec.Private, "yeet", DataType.Integer);
        field.Hook (Class); // TODO: Hmm?

        JasminVariable var = new JasminVariable ("myVar", new JasminValue ("Hello, World!", DataType.String));
        Method.DeclareVariable (var);
        JasminVariable second = new JasminVariable ("aa", var);
        Method.DeclareVariable (second);
        Method.StoreVariable (second, new JasminValue ("Hello, World!2", DataType.String));
        JasminVariable third = new JasminVariable ("bb", field);
        Method.DeclareVariable (third);

        //JasminClass Class = new JasminClass (ClassAccessSpec.Public, "MyClass");
        Class.AddField (new JasminField (FieldAccessSpec.Public, "bar", DataType.Integer));
        Class.AddField (field);
        Class.AddMethod (MethodCreator.CreateConstructorMethod ());
        Class.AddMethod (MethodCreator.CreateMainMethod ());
        Class.AddMethod (Method);

        JasminFile File = new JasminFile ("out/", "Test");
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
        */

        // TODO: Find a better way to handle variable references (not having to specify types)
        // TODO: Find a way to push and store all different value types
        // TODO: Handle fields better?
        // TODO: Handle variable references better?

        JasminFile File = new JasminFile ("/", "Test");
        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "Foo");
        JasminMethod Method = new JasminMethod (MethodAccessSpec.Public, "Bar", DataType.Void, DataType.Integer)
                .AddInstruction (new PrintInstruction (new VariableReference ("arg1", DataType.Integer)))
                .AddBlock (new JasminBlock ("a").AddInstruction (new PrintInstruction (new VariableReference ("arg1", DataType.Integer))));
                /*
                .AddBranchStatement (BranchType.Goto, "Test")
                .AddBlock (new JasminBlock ("Test")
                        .AddStatement (new NoParameterStatement (NoParameterType.Nop))
                        .LoadVariable (new VariableReference ("arg1")) // => no error
                        .LoadVariable (new VariableReference ("arg2")) // => no error
                )
                .AddBlock (new JasminBlock ("Yeet")
                        .AddStatement (new NoParameterStatement (NoParameterType.Nop))
                        .LoadVariable (new VariableReference ("this")) // => no error
                        .LoadVariable (new VariableReference ("this2")) // => error (variable doesn't exist)
                );
                */

        Class.AddMethod (Method);
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
