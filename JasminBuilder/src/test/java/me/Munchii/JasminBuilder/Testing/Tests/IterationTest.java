package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Conditions.JasminCondition;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.Instructions.WhileInstruction;
import me.Munchii.JasminBuilder.JasminFile;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.References.ClassInstance;
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Types.ConditionType;
import me.Munchii.JasminBuilder.Types.PrintType;
import me.Munchii.JasminBuilder.Utils.ExpressionBuilder;
import me.Munchii.JasminBuilder.Utils.MethodCreator;
import me.Munchii.JasminBuilder.Utils.MethodInvocation;

import java.util.ArrayList;

public class IterationTest implements TestCase {

    @Override
    public void run() {
        JasminFile file = new JasminFile("out/", "Iteration");
        JasminClass iterationClass = new JasminClass("Iteration", ClassAccessSpec.PUBLIC);

        JasminMethod initMethod = MethodCreator.createConstructorMethod();

        JasminMethod whileMethod = new JasminMethod("testWhile", DataType.VOID);
        JasminVariable whileX = new JasminVariable("x", new JasminValue(5, DataType.INTEGER));
        whileMethod.declareVariable(whileX);
        whileMethod.addInstruction(new WhileInstruction(new JasminConditionBlock(
                new JasminCondition(whileX, new JasminValue(0, DataType.INTEGER), ConditionType.GREATER_THAN),
                new JasminBlock("whileBlock")
                        .addInstruction(new PrintInstruction(new JasminValue("While Iteration No.", DataType.STRING), PrintType.PRINT))
                        .addInstruction(new PrintInstruction(whileMethod.getVariable("x"), PrintType.PRINTLN))
                        .addStatements(ExpressionBuilder.subtract(whileX, new JasminValue(1, DataType.INTEGER)).pushToStack())
                        .addStatement(whileX.store())
        )));

        JasminMethod mainMethod = MethodCreator.createMainMethod();
        mainMethod.declareVariable(new ClassInstance("this", "Iteration", new ArrayList<>()));
        mainMethod.addStatements(MethodInvocation.callMethod(iterationClass, whileMethod).pushToStack());

        iterationClass.addMethod(initMethod);
        iterationClass.addMethod(mainMethod);
        iterationClass.addMethod(whileMethod);

        file.addClass(iterationClass);
        System.out.println(file.toOutputString());
        file.write();
    }

    @Override
    public String getName() {
        return "IterationTest";
    }

}
