package me.munchii.Jasmin;

import me.munchii.Jasmin.classes.ClassAccessSpec;
import me.munchii.Jasmin.classes.JasminClass;
import me.munchii.Jasmin.method.*;
import me.munchii.Jasmin.statement.PrintStatement;
import me.munchii.Jasmin.type.*;
import me.munchii.Jasmin.value.ConstantValue;

import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: i think pushing values is broken, so maybe find a way to store the instructions on the type classes kinda like representation

        // simple hello world test
        JasminFile file = new JasminFile("", "TEST");

        JasminClass testClass = new JasminClass("Foo", EnumSet.of(ClassAccessSpec.PUBLIC));
        ConstructorMethod constructor = new ConstructorMethod(testClass, EnumSet.of(MethodAccessSpec.PUBLIC), List.of())
                .initSuper();
        JasminMethod mainMethod = new JasminMethod(testClass,
                "main",
                EnumSet.of(MethodAccessSpec.PUBLIC, MethodAccessSpec.STATIC),
                ReturnableType.VOID,
                List.of(new ArrayType(JavaStd.JAVA_STRING_INSTANCE)))
                .addStatement(new PrintStatement(PrintStatement.PrintType.PRINTLN, new ConstantValue("Hello, World!", JavaStd.JAVA_STRING_INSTANCE)))
                .returnEnd();

        LocalVariable arg1 = mainMethod.getArg(1).get();
        //arg1.load().forEach(inst -> System.out.println(inst.getString()));
        //System.out.println(arg1.isArrayReference());
        // push first element of arg1 to stack. maybe not very elegant?
        if (arg1 instanceof VariableReferenceArray array) {
            array.loadElement(0).forEach(inst -> System.out.println(inst.getString()));
        }

        file.addClass(testClass);

        StringBuilder builder = new StringBuilder();
        file.write(builder);
        System.out.println(builder);
    }
}