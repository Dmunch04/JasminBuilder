package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.*;
import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Conditions.JasminCondition;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Instructions.IfInstruction;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.References.ClassInstance;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.ExpressionBuilder;
import me.Munchii.JasminBuilder.Utils.MethodCreator;
import me.Munchii.JasminBuilder.Utils.MethodInvocation;

import java.util.ArrayList;

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
        Variable Arg1 = FibMethod.GetVariable ("arg1");
        FibMethod.AddStackLimit (50)
                .AddLocalsLimit (50)
                .AddInstruction (new IfInstruction (
                        // else return 1
                        new JasminBlock ("ELSE")
                                .Return (new JasminValue (1, DataType.Long)),

                        // if (n > 1)
                        new JasminConditionBlock (
                                new JasminCondition (Arg1, new JasminValue (1, DataType.Long), ConditionType.GreaterThan),
                                MakeFibBlock (FibMethod)
                        )
                ));

        JasminMethod MainMethod = MethodCreator.CreateMainMethod ();
        MainMethod.AddStackLimit (50);
        MainMethod.AddLocalsLimit (50);
        // Create a new instance of this class so we can access non-static fields and functions
        MainMethod.DeclareVariable (new ClassInstance ("this", "Fibonacci", new ArrayList<> ()));

        // Call the `Fib` function and store the result
        JasminPassable Result = MethodInvocation.CallMethod (Class, FibMethod, new JasminValue (45, DataType.Long));

        // Print the result
        MainMethod.AddInstruction (new PrintInstruction (Result));

        Class.AddMethod (InitMethod);
        Class.AddMethod (FibMethod);
        Class.AddMethod (MainMethod);

        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
        File.Write ();
    }

    private JasminBlock MakeFibBlock (JasminMethod Method)
    {
        Variable Arg1 = Method.GetVariable ("arg1");

        JasminBlock Block =  new JasminBlock ("FibThing");

        // a = n - 1
        JasminPassable ValueArg1 = ExpressionBuilder.Subtract (Arg1, new JasminValue (1, DataType.Long));
        // b = n - 2
        JasminPassable ValueArg2 = ExpressionBuilder.Subtract (Arg1, new JasminValue (2, DataType.Long));
        // val1 = fib(a)
        JasminPassable Value1 = MethodInvocation.CallMethod("Fibonacci", "Fib", asList (ValueArg1), DataType.Long, DataType.Long);
        // val2 = fib(b)
        JasminPassable Value2 = MethodInvocation.CallMethod("Fibonacci", "Fib", asList (ValueArg2), DataType.Long, DataType.Long);
        // return val1 + val2
        Block.Return (ExpressionBuilder.Add (Value1, Value2));

        return Block;
    }

    @Override
    public String GetName ()
    {
        return "FibonacciTest";
    }

}
