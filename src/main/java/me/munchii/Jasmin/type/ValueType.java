/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.type;

import me.munchii.Jasmin.instruction.IJasminInstruction;

public interface ValueType extends JasminType {
    ValueType BOOLEAN = PrimitiveType.BOOLEAN;
    ValueType BYTE = PrimitiveType.BYTE;
    ValueType CHAR = PrimitiveType.CHAR;
    ValueType DOUBLE = PrimitiveType.DOUBLE;
    ValueType FLOAT = PrimitiveType.FLOAT;
    ValueType INTEGER = PrimitiveType.INTEGER;
    ValueType LONG = PrimitiveType.LONG;
    ValueType SHORT = PrimitiveType.SHORT;

    ValueType STRING = new StringType();

    // TODO: should we do it like this?

    // classes/objects needs to be initialized. so what about
    // List<IJasminInstruction> getDeclarationInstructions(Object value);
    // so obviously for primitives and strings, the value would be the actual value you want to push
    // but for classes/objects value would be the arg. but how do we handle multiple args for constructors?
    // what about a class like `ArgumentContainer` which extends `JasminValue` and then instead of `Object value` we
    // do `JasminValue value`. this would however not make much sense for primitives and strings, needing to do
    // ValueType.STRING.getDeclarationInstructions(new JasminValue("hello", ValueType.STRING));
    // which means we would have to give in the type again. also this would not really be possible, because wouldn't
    // the `JasminValue` use the `StringType` to get the declaration instructions, and if we pass the `JasminValue` to
    // the `ValueType.getDeclarationInstructions(JasminValue value)` then use the `JasminValue.getDeclarationInstructions()`?
    // So maybe make another class?

    // maybe this:
    // List<IJasminInstruction> getDeclarationInstructions(JavaValue value);
    // but how would we know how to push the value? should we just make a utility class that handles that logic?
    // and what about arg container. the `JavaValue` class needs to return a list of `IJasminInstruction` to allow for all the args
    // and what if we want to use a local variable or a value/variable reference as an argument for the constructor?

    //IJasminInstruction getPushInstruction(Object value);

    //IJasminInstruction getStoreInstruction(int id);

    //IJasminInstruction getLoadInstruction(int id);
}
