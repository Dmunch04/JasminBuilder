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

    //IJasminInstruction getPushInstruction(Object value);

    //IJasminInstruction getStoreInstruction(int id);

    //IJasminInstruction getLoadInstruction(int id);
}
