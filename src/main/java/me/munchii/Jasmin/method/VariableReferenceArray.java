/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.CommentInstruction;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.instruction.LocalVariableInstruction;
import me.munchii.Jasmin.instruction.NoParameterInstruction;
import me.munchii.Jasmin.instruction.type.LocalVariableInstructionType;
import me.munchii.Jasmin.instruction.type.NoParameterInstructionType;
import me.munchii.Jasmin.type.*;
import me.munchii.Jasmin.value.JasminValue;

import java.util.List;

public class VariableReferenceArray extends VariableReference {
    private final ArrayType arrayType;

    public VariableReferenceArray(String name, int id, ArrayType arrayType) {
        super(name, id, arrayType.getType());

        this.arrayType = arrayType;
    }

    @Override
    public boolean isArrayReference() {
        return true;
    }

    public List<IJasminInstruction> loadElement(int index) {
        IJasminInstruction pushRef = load().get(0);
        IJasminInstruction pushIndex = new JasminValue(index, ValueType.INTEGER).getDeclarationInstruction().get(0);
        if (variableType instanceof PrimitiveType primitiveType) {
            switch (primitiveType) {
                case BOOLEAN, BYTE -> {
                    return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_BYTE_BOOLEAN_FROM_ARRAY));
                }

                case CHAR -> {
                    return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_CHAR_FROM_ARRAY));
                }

                case DOUBLE -> {
                    return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_DOUBLE_FROM_ARRAY));
                }

                case FLOAT -> {
                    return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_FLOAT_FROM_ARRAY));
                }

                case INTEGER -> {
                    return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_INTEGER_FROM_ARRAY));
                }

                case LONG -> {
                    return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_LONG_FROM_ARRAY));
                }

                case SHORT -> {
                    return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_SHORT_FROM_ARRAY));
                }
            }
        } else if (variableType instanceof ArrayType) {
            return List.of(new CommentInstruction("no"));
        } else if (variableType instanceof ClassType || variableType instanceof StringType) {
            return List.of(pushRef, pushIndex, new NoParameterInstruction(NoParameterInstructionType.LOAD_REFERENCE_FROM_ARRAY));
        }

        return List.of();
    }

    public ArrayType getArrayType() {
        return arrayType;
    }
}
