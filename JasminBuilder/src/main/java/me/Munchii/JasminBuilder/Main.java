package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Instructions.IfInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Conditions.ConditionBuilder;
import me.Munchii.JasminBuilder.Conditions.JasminCondition;
import me.Munchii.JasminBuilder.Statements.ReturnStatement;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Utils.ExpressionBuilder;

import static java.util.Arrays.asList;

public class Main
{

    public static void main (String... Args)
    {
        // TODO: Find a way to push and store all different value types -> done?
        // TODO: How can we stop/prevent the user from chaining on the `JasminMethod` constructor method?
        // TODO: Make instructions for control-flow, loops, field init, etc. -> if/else statements done
        // TODO: Add multi dimensional support for arrays

        // TODO: Fix tests to use new variable system -> High priority!!

        //? TODO: Maybe rework how the error system works? Should it even abort/exit when error? And how would the user toggle that

        //? Maybe implement a method on the `JasminMethod` class, to automatically add return statement, depending on the `DataType` given
        //? When a value is provided to a field and added to a class, it adds ` = %value%` to the field declaration, but it doesn't seem like Jasmin supports this for some reason? Should it be removed?

        JasminFile File = new JasminFile ("/", "Test");
        JasminClass Class = new JasminClass ("Foo", ClassAccessSpec.Public);
        JasminField Field = new JasminField ("Hmm", DataType.Integer, new JasminValue (5, DataType.Integer), FieldAccessSpec.Public);

        // TODO: Make it nice to chain them with `and` (&&) and `or` (||)
        JasminMethod Method = new JasminMethod ("Test", DataType.Void, asList (MethodAccessSpec.Public), DataType.Integer);
        ConditionBuilder Condition = new ConditionBuilder (JasminCondition.Equals (
                new JasminValue (1, DataType.Integer),
                new JasminValue (1, DataType.Integer)
        ));
        Method.AddInstruction (new IfInstruction (
                new JasminBlock ("bbb").AddNoParameterStatement(NoParameterType.Return),

                new JasminConditionBlock (
                    Condition,
                    new JasminBlock ("aa")
                )
        ));

        Class.AddField (Field);
        Class.AddMethod (Method);
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
