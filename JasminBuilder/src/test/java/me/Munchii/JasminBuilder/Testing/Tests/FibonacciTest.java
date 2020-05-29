package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Conditions.JasminCondition;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Instructions.IfInstruction;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.JasminFile;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Statements.ReturnStatement;
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.ExpressionBuilder;
import me.Munchii.JasminBuilder.Utils.MethodCreator;
import me.Munchii.JasminBuilder.Utils.MethodInvocation;

import static java.util.Arrays.asList;

public class FibonacciTest implements TestCase
{

    @Override
    public void Run ()
    {
        JasminFile File = new JasminFile ("Out/", "Fibonacci");

        JasminClass Class = new JasminClass ("Fibonacci", ClassAccessSpec.Public);

        JasminMethod InitMethod = MethodCreator.CreateConstructorMethod ();

        JasminMethod FibMethod = new JasminMethod ("Fib", DataType.Long, asList (MethodAccessSpec.Public), DataType.Long);
        JasminVariable Arg1 = FibMethod.GetVariable ("arg1");
        FibMethod.AddStackLimit (50)
                .AddLocalsLimit (50)
                // TODO: Compare arg (if arg == 0 or arg == 1, return arg)
                // if (arg1 > 1) { %fib% }
                //.LoadVariable (new VariableReference ("arg1"))
                //.AddNoParameterStatement (NoParameterType.LongConstant1)
                //.AddNoParameterStatement (NoParameterType.CompareLong)
                //.AddBranchStatement (BranchType.IfGreaterThan, "FibThing")
                .AddInstruction (new IfInstruction (
                        new JasminBlock ("ELSE")
                                .AddNoParameterStatement (NoParameterType.LongConstant1)
                                .AddNoParameterStatement (NoParameterType.ReturnLong),

                        new JasminConditionBlock (
                                new JasminCondition (Arg1, new JasminValue (1, DataType.Long), ConditionType.GreaterThan),
                                MakeFibBlock (FibMethod)
                        )
                ));
/*
                // Return 1
                .AddNoParameterStatement (NoParameterType.LongConstant1)
                .AddNoParameterStatement (NoParameterType.ReturnLong)

                // TODO: Call Fib(arg-1) + Fib(arg-2)
                .AddBlock (new JasminBlock ("FibThing")
                        // this.Fib(n - 1)
                        // TODO: Use `ExpressionBuilder` here instead, but how? Perhaps make a MethodCall instruction? That way we should also be able to make it a variable?
                        .LoadVariable (new VariableReference ("this"))
                        .LoadVariable (new VariableReference ("arg1"))
                        .AddNoParameterStatement (NoParameterType.LongConstant1)
                        .AddNoParameterStatement (NoParameterType.SubtractLong)
                        .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", DataType.Long, DataType.Long)

                        // this.Fib(n - 2)
                        .LoadVariable (new VariableReference ("this"))
                        .LoadVariable (new VariableReference ("arg1"))
                        .AddLoadConstantStatement (LoadConstantType.LoadConstant2Wide, new JasminValue (2L, DataType.Long))
                        .AddNoParameterStatement (NoParameterType.SubtractLong)
                        .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "Fibonacci/Fib", DataType.Long, DataType.Long)

                        // left + right
                        .AddStatement (new NoParameterStatement (NoParameterType.AddLong))

                        // return
                        .AddStatement (new NoParameterStatement (NoParameterType.ReturnLong))
                );*/

        JasminMethod MainMethod = MethodCreator.CreateMainMethod ()
                // Create a new instance of this class so we can access non-static fields and functions
                .AddObjectStatement (ObjectType.New, "Fibonacci")
                .AddNoParameterStatement (NoParameterType.DuplicateTopStackValue)
                .AddMethodInvokationStatement (MethodInvocationType.InvokeSpecial, "Fibonacci/<init>", DataType.Void)
                .AddNoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable1)

                // Call the `Fib` function and store the result
                .AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable1)
                .AddMethodInvokationStatement (MethodInvocationType.InvokeVirtual, "Fibonacci/Fib", DataType.Long, DataType.Long)
                .AddNoParameterStatement (NoParameterType.StoreLong2)

                // Print the result
                // TODO: Fix this when local variables have been implemented and print instruction can take in one as argument
                .AddInstruction (new PrintInstruction (new JasminValue (0, DataType.Long)));

        Class.AddMethod (InitMethod);
        Class.AddMethod (FibMethod);
        Class.AddMethod (MainMethod);

        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
        File.Write ();
    }

    private JasminBlock MakeFibBlock(JasminMethod Method)
    {
        JasminVariable This = Method.GetVariable ("this");
        JasminVariable Arg1 = Method.GetVariable ("arg1");

        JasminBlock Block =  new JasminBlock ("FibThing");

        JasminPassable ValueArg1 = ExpressionBuilder.Subtract (Arg1, new JasminValue (1, DataType.Long));
        JasminPassable ValueArg2 = ExpressionBuilder.Subtract (Arg1, new JasminValue (2, DataType.Long));
        JasminPassable Value1 = MethodInvocation.CallMethod("Fibonacci", "Fib", asList (ValueArg1), DataType.Long, DataType.Long);
        JasminPassable Value2 = MethodInvocation.CallMethod("Fibonacci", "Fib", asList (ValueArg2), DataType.Long, DataType.Long);
        Block.Return (ExpressionBuilder.Add (Value1, Value2));

        return Block;

        /*
            // this.Fib(n - 1)
            // TODO: Use `ExpressionBuilder` here instead, but how? Perhaps make a MethodCall instruction? That way we should also be able to make it a variable?
            .LoadVariable (new VariableReference ("this"))
            .LoadVariable (new VariableReference ("arg1"))
            .AddNoParameterStatement (NoParameterType.LongConstant1)
            .AddNoParameterStatement (NoParameterType.SubtractLong)
            .AddMethodInvokationStatement (MethodInvocationType.InvokeVirtual, "Fibonacci/Fib", DataType.Long, DataType.Long)

            // this.Fib(n - 2)
            .LoadVariable (new VariableReference ("this"))
            .LoadVariable (new VariableReference ("arg1"))
            .AddLoadConstantStatement (LoadConstantType.LoadConstant2Wide, new JasminValue (2L, DataType.Long))
            .AddNoParameterStatement (NoParameterType.SubtractLong)
            .AddMethodInvokationStatement (MethodInvocationType.InvokeVirtual, "Fibonacci/Fib", DataType.Long, DataType.Long)

            // left + right
            .AddStatement (new NoParameterStatement (NoParameterType.AddLong))

            // return
            .AddStatement (new NoParameterStatement (NoParameterType.ReturnLong));
         */
    }

    @Override
    public String GetName ()
    {
        return "FibonacciTest";
    }

}
