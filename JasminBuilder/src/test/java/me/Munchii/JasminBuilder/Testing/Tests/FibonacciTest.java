package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.JasminFile;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.MethodInvokationType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Types.ObjectType;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

public class FibonacciTest implements TestCase
{

    @Override
    public void Run ()
    {
        JasminFile File = new JasminFile ("Out/", "Fibonacci");

        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "Fibonacci");

        JasminMethod InitMethod = MethodCreator.CreateConstructorMethod ();

        JasminMethod FibMethod = new JasminMethod (MethodAccessSpec.Public, "Fib", DataType.Integer, DataType.Integer)
                // Push the argument onto the stack
                .AddNoParameterStatement (NoParameterType.LoadInteger0)

                // TODO: Compare arg (if arg == 0 or arg == 1, return arg)

                // TODO: Call Fib(arg-1) + Fib(arg-2)

                // Return the value
                .AddNoParameterStatement (NoParameterType.ReturnInteger);

        JasminMethod MainMethod = MethodCreator.CreateMainMethod ()
                // Create a new instance of this class so we can access non-static fields and functions
                .AddObjectStatement (ObjectType.New, "Fibonacci")
                .AddNoParameterStatement (NoParameterType.DuplicateTopStackValue)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeSpecial, "Fibonacci/<init>", DataType.Void)
                .AddNoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable1)

                // Call the `Fib` function and store the result
                .AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable1)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", DataType.Integer, DataType.Integer)
                .AddNoParameterStatement (NoParameterType.StoreInteger0)

                // Print the result
                // TODO: Fix this when local variables have been implemented and print instruction can take in one as argument
                .AddInstruction (new PrintInstruction (new JasminValue (0, DataType.Integer)));

        Class.AddMethod (InitMethod);
        Class.AddMethod (FibMethod);
        Class.AddMethod (MainMethod);

        File.AddClass (Class);
        // TODO: Actually generate the output to file
        System.out.println (File.ToOutputString ());
    }

    @Override
    public String GetName ()
    {
        return "FibonacciTest";
    }

}
