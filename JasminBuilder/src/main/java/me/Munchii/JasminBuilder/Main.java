package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Methods.MethodAccessSpec;
import me.Munchii.JasminBuilder.Types.ConditionType;
import me.Munchii.JasminBuilder.Utils.ConditionBuilder;
import me.Munchii.JasminBuilder.Utils.JasminCondition;

import static java.util.Arrays.asList;

public class Main
{

    public static void main (String... Args)
    {
        // TODO: Find a way to push and store all different value types -> mostly done
        // TODO: How can we stop/prevent the user from chaining on the `JasminMethod` constructor method?
        // TODO: How should conditions work?
        // TODO: Make instructions for control-flow, loops, field init, etc.
        // TODO: Add multi dimensional support for arrays

        // TODO: Fix tests to use new variable system

        //? TODO: Maybe rework how the error system works? Should it even abort/exit when error? And how would the user toggle that

        //? Handle fields better?
        //? Maybe implement a method on the `JasminMethod` class, to automatically add return statement, depending on the `DataType` given
        //? Allow for variables to be defined by stack value?
        //? When a value is provided to a field and added to a class, it adds ` = %value%` to the field declaration, but it doesn't seem like Jasmin supports this for some reason? Should it be removed?

        JasminFile File = new JasminFile ("/", "Test");
        JasminClass Class = new JasminClass ("Foo", ClassAccessSpec.Public);
        JasminField Field = new JasminField ("Hmm", DataType.Integer, new JasminValue (5, DataType.Integer), FieldAccessSpec.Public);

        // TODO: Make it nice to chain them with `and` (&&) and `or` (||)
        JasminMethod Method = new JasminMethod ("Test", DataType.Void, asList (MethodAccessSpec.Public), DataType.Integer);
        // if ("HELLO" == null)
        Method.AddStatements (new ConditionBuilder ().AddCondition (new JasminCondition (
                new JasminValue ("HELLO", DataType.String),
                new JasminValue (null, DataType.Void),
                ConditionType.Equals
        )).Write ("aa"));
        // if (null == "HELLO")
        Method.AddStatements (new ConditionBuilder ().AddCondition (new JasminCondition (
                new JasminValue (null, DataType.Void),
                new JasminValue ("HELLO", DataType.String),
                ConditionType.Equals
        )).Write ("aa"));
        // if (null = new int[])
        Method.AddStatements (new ConditionBuilder ().AddCondition (new JasminCondition (
                new JasminValue (null, DataType.Void),
                new JasminValue ("HELLO", new ArrayType (DataType.Integer)),
                ConditionType.Equals
        )).Write ("aa"));
        // if (new int[] == null)
        Method.AddStatements (new ConditionBuilder ().AddCondition (new JasminCondition (
                new JasminValue ("HELLO", new ArrayType (DataType.Integer)),
                new JasminValue (null, DataType.Void),
                ConditionType.Equals
        )).Write ("aa"));

        // TODO: This here will NOT work because blocks are evaluated after the methods main block, so the id would just be -1
        // TODO: Find a good fix
        //? Why did I write this? Ofc. you shouldn't be able to access a local scope/block variable outside the block
        //Method.DeclareVariable (new JasminVariable ("b", Array.GetElement (0)));

        Class.AddField (Field);
        Class.AddMethod (Method);
        File.AddClass (Class);
        System.out.println (File.ToOutputString ());
    }

}
