package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.IntegerType;
import me.Munchii.JasminBuilder.DataTypes.LongType;
import me.Munchii.JasminBuilder.DataTypes.VoidType;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.JasminFile;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.References.VariableReference;
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

        JasminClass Class = new JasminClass ("Fibonacci", ClassAccessSpec.Public);

        JasminMethod InitMethod = MethodCreator.CreateConstructorMethod ();

        JasminMethod FibMethod = new JasminMethod ("Fib", new LongType (), asList (MethodAccessSpec.Public), new LongType ())
                // TODO: Compare arg (if arg == 0 or arg == 1, return arg)
                // if (arg1 > 1) { %fib% }
                .LoadVariable (new VariableReference ("arg1"))
                .AddNoParameterStatement (NoParameterType.LongConstant1)
                .AddNoParameterStatement (NoParameterType.CompareLong)
                .AddBranchStatement (BranchType.IfGreaterThan, "FibThing")

                // TODO: Call Fib(arg-1) + Fib(arg-2)
                .AddBlock (new JasminBlock ("FibThing")
                        // this.Fib(n - 1)
                        // TODO: Use `ExpressionBuilder` here instead, but how? Perhaps make a MethodCall instruction? That way we should also be able to make it a variable?
                        .LoadVariable (new VariableReference ("this"))
                        .LoadVariable (new VariableReference ("arg1"))
                        .AddNoParameterStatement (NoParameterType.LongConstant1)
                        .AddNoParameterStatement (NoParameterType.SubtractLong)
                        .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", new LongType (), new LongType ())
                        .AddNoParameterStatement (NoParameterType.StoreLong2)

                        // this.Fib(n - 2)
                        .LoadVariable (new VariableReference ("this"))
                        .LoadVariable (new VariableReference ("arg1"))
                        .AddLoadConstantStatement (LoadConstantType.LoadConstant2Wide, new JasminValue (2L, new LongType ()))
                        .AddNoParameterStatement (NoParameterType.SubtractLong)
                        .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", new LongType (), new LongType ())
                        .AddNoParameterStatement (NoParameterType.StoreLong3)

                        // left + right
                        .AddStatement (new NoParameterStatement (NoParameterType.LoadLong2))
                        .AddStatement (new NoParameterStatement (NoParameterType.LoadLong3))
                        .AddStatement (new NoParameterStatement (NoParameterType.AddLong))

                        // return
                        .AddStatement (new NoParameterStatement (NoParameterType.ReturnLong))
                )

                // Return the value
                .AddNoParameterStatement (NoParameterType.LongConstant1)
                .AddNoParameterStatement (NoParameterType.ReturnLong);

        JasminMethod MainMethod = MethodCreator.CreateMainMethod ()
                // Create a new instance of this class so we can access non-static fields and functions
                .AddObjectStatement (ObjectType.New, "Fibonacci")
                .AddNoParameterStatement (NoParameterType.DuplicateTopStackValue)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeSpecial, "Fibonacci/<init>", new VoidType ())
                .AddNoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable1)

                // Call the `Fib` function and store the result
                .AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable1)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", new IntegerType (), new IntegerType ())
                .AddNoParameterStatement (NoParameterType.StoreInteger0)

                // Print the result
                // TODO: Fix this when local variables have been implemented and print instruction can take in one as argument
                .AddInstruction (new PrintInstruction (new JasminValue (0, new IntegerType ())));

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
