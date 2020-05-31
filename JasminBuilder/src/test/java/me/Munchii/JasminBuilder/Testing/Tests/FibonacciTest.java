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
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.ExpressionBuilder;
import me.Munchii.JasminBuilder.Utils.MethodCreator;
import me.Munchii.JasminBuilder.Utils.MethodInvocation;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class FibonacciTest implements TestCase {

    @Override
    public void run() {
        JasminFile file = new JasminFile("out/", "Fibonacci");
        JasminClass fibonacciClass = new JasminClass("Fibonacci", ClassAccessSpec.PUBLIC);

        JasminMethod initMethod = MethodCreator.createConstructorMethod();

        JasminMethod fibMethod = new JasminMethod("Fib", DataType.LONG, asList(MethodAccessSpec.PUBLIC), DataType.LONG);
        Variable arg1 = fibMethod.getVariable("arg1");
        fibMethod.addStackLimit(50)
                .addLocalsLimit(50)
                .addInstruction(new IfInstruction(
                        // else return 1
                        new JasminBlock("ELSE")
                                .returnValue(new JasminValue(1, DataType.LONG)),

                        // if (n > 1)
                        new JasminConditionBlock(
                                new JasminCondition(arg1, new JasminValue(1, DataType.LONG), ConditionType.GREATER_THAN),
                                makeFibBlock(fibMethod)
                        )
                ));

        JasminMethod mainMethod = MethodCreator.createMainMethod();
        mainMethod.addStackLimit(50);
        mainMethod.addLocalsLimit(50);
        // Create a new instance of this class so we can access non-static fields and functions
        mainMethod.declareVariable(new ClassInstance("this", "Fibonacci", new ArrayList<>()));

        // Call the `Fib` function and store the result
        JasminPassable result = MethodInvocation.callMethod(fibonacciClass, fibMethod, new JasminValue(45, DataType.LONG));

        // Print the result
        mainMethod.addInstruction(new PrintInstruction(result));

        fibonacciClass.addMethod(initMethod);
        fibonacciClass.addMethod(fibMethod);
        fibonacciClass.addMethod(mainMethod);

        file.addClass(fibonacciClass);
        System.out.println(file.toOutputString());
        file.write();
    }

    private JasminBlock makeFibBlock(JasminMethod method) {
        Variable arg1 = method.getVariable("arg1");
        JasminBlock block = new JasminBlock("FibThing");

        // a = n - 1
        JasminPassable value1 = ExpressionBuilder.subtract(arg1, new JasminValue(1, DataType.LONG));
        // b = n - 2
        JasminPassable value2 = ExpressionBuilder.subtract(arg1, new JasminValue(2, DataType.LONG));
        // val1 = fib(a)
        JasminPassable result1 = MethodInvocation.callMethod("Fibonacci", "Fib", asList(value1), DataType.LONG, DataType.LONG);
        // val2 = fib(b)
        JasminPassable result2 = MethodInvocation.callMethod("Fibonacci", "Fib", asList(value2), DataType.LONG, DataType.LONG);
        // return val1 + val2
        block.returnValue(ExpressionBuilder.add(result1, result2));

        return block;
    }

    @Override
    public String getName() {
        return "FibonacciTest";
    }

}
