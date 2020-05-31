package me.Munchii.JasminBuilder.Testing.Tests;

import me.Munchii.JasminBuilder.Classes.ClassAccessSpec;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Instructions.PrintInstruction;
import me.Munchii.JasminBuilder.JasminFile;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Testing.TestCase;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

public class HelloWorldTest implements TestCase {

    @Override
    public void run() {
        JasminFile file = new JasminFile("out/", "HelloWorld");
        JasminClass helloWorldClass = new JasminClass("HelloWorld", ClassAccessSpec.PUBLIC);

        JasminMethod mainMethod = MethodCreator.createMainMethod()
                .addInstruction(new PrintInstruction(new JasminValue("Hello, World!", DataType.STRING)));

        helloWorldClass.addMethod(mainMethod);

        file.addClass(helloWorldClass);
        System.out.println(file.toOutputString());
        file.write();
    }

    @Override
    public String getName() {
        return "HelloWorldTest";
    }

}
