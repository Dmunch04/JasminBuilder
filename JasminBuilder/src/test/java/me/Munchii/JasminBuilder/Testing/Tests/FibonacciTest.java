package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.JasminFile;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Statements.MethodInvokationStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

import static java.util.Arrays.asList;

public class FibonacciTest implements TestCase
{

    @Override
    public void Run ()
    {
        JasminFile File = new JasminFile ("Out/", "Fibonacci");

        JasminClass Class = new JasminClass (ClassAccessSpec.Public, "Fibonacci");

        JasminMethod InitMethod = MethodCreator.CreateConstructorMethod ();

        JasminMethod FibMethod = new JasminMethod (MethodAccessSpec.Public, "Fib", DataType.Integer, DataType.Integer)
                // TODO: Compare arg (if arg == 0 or arg == 1, return arg)
                //.AddNoParameterStatement (NoParameterType.LoadInteger1)
                .LoadVariable ("arg1")
                .AddNoParameterStatement (NoParameterType.IntegerConstant1)
                .AddBranchStatement (BranchType.IfIntegerCompareGreaterThan, "FibThing")

                .LoadVariable ("arg1")
                //.AddNoParameterStatement (NoParameterType.LoadInteger1)

                // TODO: Call Fib(arg-1) + Fib(arg-2)
                .AddBlock (new JasminBlock ("FibThing")
                        // this.Fib(n - 1)
                        //.AddStatement (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0))
                        .LoadVariable ("this")
                        //.AddStatement (new NoParameterStatement (NoParameterType.LoadInteger1))
                        .LoadVariable ("arg1")
                        // TODO: Use `ExpressionBuilder` here instead
                        .AddStatement (new NoParameterStatement (NoParameterType.IntegerConstant1))
                        .AddStatement (new NoParameterStatement (NoParameterType.SubtractInteger))
                        .AddStatement (new MethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", DataType.Integer, asList (DataType.Integer)))
                        .AddStatement (new NoParameterStatement (NoParameterType.StoreInteger2))

                        // this.Fib(n - 2)
                        //.AddStatement (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0))
                        .LoadVariable ("this")
                        //.AddStatement (new NoParameterStatement (NoParameterType.LoadInteger1))
                        .LoadVariable ("arg1")
                        .AddStatement (new NoParameterStatement (NoParameterType.IntegerConstant2))
                        .AddStatement (new NoParameterStatement (NoParameterType.SubtractInteger))
                        .AddStatement (new MethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", DataType.Integer, asList (DataType.Integer)))
                        .AddStatement (new NoParameterStatement (NoParameterType.StoreInteger3))

                        // left + right
                        .AddStatement (new NoParameterStatement (NoParameterType.LoadInteger2))
                        .AddStatement (new NoParameterStatement (NoParameterType.LoadInteger3))
                        .AddStatement (new NoParameterStatement (NoParameterType.AddInteger))

                        // return
                        .AddStatement (new NoParameterStatement (NoParameterType.ReturnInteger))
                )

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
        System.out.println (File.ToOutputString ());
        File.Write ();
    }

    @Override
    public String GetName ()
    {
        return "FibonacciTest";
    }

}
