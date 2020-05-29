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
import me.Munchii.JasminBuilder.Utils.ClassInvocation;
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
                .AddInstruction (new IfInstruction (
                        new JasminBlock ("ELSE")
                                .AddNoParameterStatement (NoParameterType.LongConstant1)
                                .AddNoParameterStatement (NoParameterType.ReturnLong),

                        new JasminConditionBlock (
                                new JasminCondition (Arg1, new JasminValue (1, DataType.Long), ConditionType.GreaterThan),
                                MakeFibBlock (FibMethod)
                        )
                ));

        JasminMethod MainMethod = MethodCreator.CreateMainMethod ();
        MainMethod.AddStackLimit (50);
        MainMethod.AddLocalsLimit (50);
        // Create a new instance of this class so we can access non-static fields and functions
        MainMethod.DeclareVariable (new JasminVariable ("this", ClassInvocation.NewClassInstance (Class)));

        // Call the `Fib` function and store the result
        // TODO: `this` does not exist yet!
        JasminPassable Result = MethodInvocation.CallMethod(Class, FibMethod, new JasminValue (45, DataType.Long));

        // Print the result
        //MainMethod.AddInstruction (new PrintInstruction (Result));

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
    }

    @Override
    public String GetName ()
    {
        return "FibonacciTest";
    }

}
