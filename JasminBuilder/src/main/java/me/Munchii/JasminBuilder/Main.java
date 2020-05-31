package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Instructions.IfInstruction;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Conditions.ConditionBuilder;
import me.Munchii.JasminBuilder.Conditions.JasminCondition;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String... args) {
        // TODO: How can we stop/prevent the user from chaining on the `JasminMethod` constructor method?
        // TODO: Make instructions for control-flow, loops, field init, etc. -> if/else statements done
        // TODO: Add multi dimensional support for arrays

        //? TODO: Maybe rework how the error system works? Should it even abort/exit when error? And how would the user toggle that

        //? When a value is provided to a field and added to a class, it adds ` = %value%` to the field declaration, but it doesn't seem like Jasmin supports this for some reason? Should it be removed?

        JasminFile file = new JasminFile("/", "Test");
        JasminClass testClass = new JasminClass("Foo", ClassAccessSpec.PUBLIC);
        JasminField field = new JasminField("Hmm", DataType.INTEGER, new JasminValue(5, DataType.INTEGER), FieldAccessSpec.PUBLIC);

        // TODO: Make it nice to chain them with `and` (&&) and `or` (||)
        JasminMethod method = new JasminMethod("Test", DataType.VOID, asList(MethodAccessSpec.PUBLIC), DataType.INTEGER);
        ConditionBuilder conditionBuilder = new ConditionBuilder(JasminCondition.equals(
                new JasminValue(1, DataType.INTEGER),
                new JasminValue(1, DataType.INTEGER)
        ));
        method.addInstruction(new IfInstruction(
                new JasminBlock("bbb")
                        .addInstruction(new PrintInstruction(new JasminValue("Hello, World!", DataType.STRING)))
                        .addInstruction(new PrintInstruction(new JasminValue(5, DataType.INTEGER)))
                        .returnValue(null),

                new JasminConditionBlock(
                        conditionBuilder,
                        new JasminBlock("aa")
                )
        ));

        testClass.addField(field);
        testClass.addMethod(method);
        file.addClass(testClass);
        System.out.println(file.toOutputString());
    }

}
