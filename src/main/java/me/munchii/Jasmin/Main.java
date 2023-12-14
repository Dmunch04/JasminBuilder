package me.munchii.Jasmin;

import me.munchii.Jasmin.classes.ClassAccessSpec;
import me.munchii.Jasmin.classes.JasminClass;
import me.munchii.Jasmin.instruction.FieldManipulationInstruction;
import me.munchii.Jasmin.instruction.LoadConstantInstruction;
import me.munchii.Jasmin.instruction.MethodInvokationInstruction;
import me.munchii.Jasmin.instruction.type.FieldManipulationInstructionType;
import me.munchii.Jasmin.instruction.type.LoadConstantInstructionType;
import me.munchii.Jasmin.instruction.type.MethodInvokationInstructionType;
import me.munchii.Jasmin.method.ConstructorMethod;
import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.method.MethodAccessSpec;
import me.munchii.Jasmin.type.ArrayType;
import me.munchii.Jasmin.type.ClassType;
import me.munchii.Jasmin.type.JavaStd;
import me.munchii.Jasmin.type.ReturnableType;

import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JasminFile file = new JasminFile("", "TEST");

        JasminClass testClass = new JasminClass("Foo", EnumSet.of(ClassAccessSpec.PUBLIC));
        ConstructorMethod constructor = new ConstructorMethod(testClass, EnumSet.of(MethodAccessSpec.PUBLIC), List.of())
                .initSuper();
        JasminMethod mainMethod = new JasminMethod(testClass,
                "main",
                EnumSet.of(MethodAccessSpec.PUBLIC, MethodAccessSpec.STATIC),
                ReturnableType.VOID,
                List.of(new ArrayType(JavaStd.JAVA_STRING_INSTANCE)))
                .addInstruction(new FieldManipulationInstruction(FieldManipulationInstructionType.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE))
                .addInstruction(new LoadConstantInstruction(LoadConstantInstructionType.LOAD_CONSTANT, "Hello, World!"))
                .addInstruction(new MethodInvokationInstruction(MethodInvokationInstructionType.INVOKE_VIRTUAL, JavaStd.JAVA_PRINT_STREAM_REFERENCE, "println", ReturnableType.VOID, List.of(JavaStd.JAVA_STRING_INSTANCE)))
                .returnEnd();

        file.addClass(testClass);

        StringBuilder builder = new StringBuilder();
        file.write(builder);
        System.out.println(builder);
    }

    /*
    public static void main(String[] args) {
        JasminFile file = new JasminFile("", "TEST");
        JasminClass testClass = new JasminClass("Foo", EnumSet.of(ClassAccessSpec.PUBLIC));

        JasminMethod method = new JasminMethod(testClass,
                "main",
                EnumSet.of(MethodAccessSpec.PUBLIC, MethodAccessSpec.FINAL),
                PrimitiveType.VOID_TYPE,
                List.of(new ArrayType(new ClassType("java/lang/String"))));

        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", "Ljava/io/PrintStream;"));
        method.addInstruction(new Instruction(JasminInstructions.LOAD_CONSTANT, "\"Hello, World\""));
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, "java/io/PrintStream/println(Ljava/lang/String;)V"));
        method.addInstruction(new Instruction(JasminInstructions.RETURN));

        method.declareVariable("test", new JasminValue(3, PrimitiveType.INTEGER));
        method.addInstructions(method.getVariable("this").get().load());
        PrintStatement.println(method, new ConstantValue("Hello, World!", JavaStd.JAVA_STRING_INSTANCE));
        PrintStatement.print(method, new ConstantValue(5, PrimitiveType.INTEGER));

        JasminBlock block = new JasminBlock(method, "Test");
        block.addInstruction(new Instruction(JasminInstructions.NOP, "YEEEEEEET"));
        block.declareVariable("yeet", new JasminValue("hehe", new StringType()));
        block.addInstructions(method.getVariable("this").get().load());
        block.addInstructions(method.getVariable("yeet").get().load());
        block.addInstructions(method.getVariable("arg1").get().load());
        block.addInstruction(new Instruction(JasminInstructions.LOAD_CONSTANT, "\"adsadsa\""));
        block.addInstructions(method.getVariable("arg1").get().store());

        //testClass.registerMethod(method);
        file.addClass(testClass);

        StringBuilder builder = new StringBuilder();
        file.write(builder);
        //System.out.println(builder);

        test();

        /*
        StringBuilder builder = new StringBuilder();
        method.write(builder);
        System.out.println(builder.toString());

        System.out.println(MethodSpec.makeMethodSpec(
                new ReferenceType("java/io/PrintStream"),
                "println",
                ValueType.VOID_TYPE,
                new ClassType("java/lang/String")
        ));
         */
    /*
    }

    public static void test() {
        JasminFile accountFile = new JasminFile("", "Account");
        JasminClass accountClass = new JasminClass("Account", EnumSet.of(ClassAccessSpec.PUBLIC));
        JasminField accountFieldBalance = new JasminField(accountClass, "balance", EnumSet.of(FieldAccessSpec.PRIVATE), PrimitiveType.DOUBLE);
        // constructor
        JasminMethod accountMethodGetBalance = new JasminMethod(accountClass, "getBalance", EnumSet.of(MethodAccessSpec.PUBLIC), PrimitiveType.DOUBLE);
        // .returnField(JasminClass/String class, JasminField field)
        // .returnField(JasminClass/String class, String fieldName, IDataType fieldType)
        accountMethodGetBalance.addInstructions(accountMethodGetBalance.getVariable("this").get().load())
                .addInstruction(new Instruction(JasminInstructions.GET_FIELD, "Account/balance", "D"))
                .addInstruction(new Instruction(JasminInstructions.RETURN_DOUBLE));

        JasminMethod accountMethodDeposit = new JasminMethod(accountClass, "deposit", EnumSet.of(MethodAccessSpec.PUBLIC), PrimitiveType.VOID_TYPE, List.of(PrimitiveType.DOUBLE));
        accountMethodDeposit.addInstructions(accountMethodDeposit.getVariable("this").get().load())
                .addInstructions(accountMethodDeposit.getVariable("this").get().load())
                .addInstruction(new Instruction(JasminInstructions.GET_FIELD, "Account/balance", "D"))
                .addInstructions(accountMethodDeposit.getVariable("arg1").get().load())
                .addInstruction(new Instruction(JasminInstructions.ADD_DOUBLE))
                .addInstruction(new Instruction(JasminInstructions.PUT_FIELD, "Account/balance", "D"))
                .addInstruction(new Instruction(JasminInstructions.RETURN));

        //accountClass.registerField(accountFieldBalance);
        //accountClass.registerMethod(accountMethodGetBalance);
        //accountClass.registerMethod(accountMethodDeposit);
        accountFile.addClass(accountClass);

        StringBuilder builder = new StringBuilder();
        accountFile.write(builder);
        System.out.println(builder);
    }
    */
}