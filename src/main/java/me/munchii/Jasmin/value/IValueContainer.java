package me.munchii.Jasmin.value;

import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.instruction.LoadConstantInstruction;
import me.munchii.Jasmin.instruction.type.LoadConstantInstructionType;
import me.munchii.Jasmin.type.*;

public interface IValueContainer {
    JasminValue getValue();

    default IJasminInstruction pushValue() {
        JasminValue value = getValue();

        if (value.getValueType() instanceof PrimitiveType valueType) {
            switch (valueType) {
                case BOOLEAN -> {
                    boolean val = (boolean) value.getValue();
                    return new Instruction(val ? JasminInstructions.INTEGER_CONSTANT_1 : JasminInstructions.INTEGER_CONSTANT_0);
                }
                case BYTE -> {
                    byte val = (byte) value.getValue();
                    return new Instruction(JasminInstructions.BI_PUSH, String.valueOf(val));
                }
                case CHAR -> {
                    char val = (char) value.getValue();
                    return new Instruction(JasminInstructions.BI_PUSH, String.valueOf((int) val));
                }
                case DOUBLE -> {
                    double val = (double) value.getValue();

                    if (val == 0.0d) {
                        return new Instruction(JasminInstructions.PUSH_DOUBLE_0);
                    } else if (val == 1.0d) {
                        return new Instruction(JasminInstructions.PUSH_DOUBLE_1);
                    } else {
                        return new Instruction(JasminInstructions.LOAD_CONSTANT_2_WIDE, String.valueOf(val));
                    }
                }
                case FLOAT -> {
                    float val = (float) value.getValue();

                    if (val == 0.0f) {
                        return new Instruction(JasminInstructions.FLOAT_CONSTANT_0);
                    } else if (val == 1.0f) {
                        return new Instruction(JasminInstructions.FLOAT_CONSTANT_1);
                    } else if (val == 2.0f) {
                        return new Instruction(JasminInstructions.FLOAT_CONSTANT_2);
                    } else {
                        return new Instruction(JasminInstructions.LOAD_CONSTANT, String.valueOf(val));
                    }
                }
                case INTEGER -> {
                    int val = (int) value.getValue();

                    if (val == -1) {
                        return new Instruction(JasminInstructions.INTEGER_CONSTANT_NEGATIVE_1);
                    } else if (val == 0) {
                        return new Instruction(JasminInstructions.INTEGER_CONSTANT_0);
                    } else if (val == 1) {
                        return new Instruction(JasminInstructions.INTEGER_CONSTANT_1);
                    } else if (val == 2) {
                        return new Instruction(JasminInstructions.INTEGER_CONSTANT_2);
                    } else if (val == 3) {
                        return new Instruction(JasminInstructions.INTEGER_CONSTANT_3);
                    } else if (val == 4) {
                        return new Instruction(JasminInstructions.INTEGER_CONSTANT_4);
                    } else if (val == 5) {
                        return new Instruction(JasminInstructions.INTEGER_CONSTANT_5);
                    } else {
                        return new Instruction(JasminInstructions.BI_PUSH, String.valueOf(val));
                    }
                }
                case LONG -> {
                    long val = (long) value.getValue();

                    if (val == 0) {
                        return new Instruction(JasminInstructions.LONG_CONSTANT_0);
                    } else if (val == 1) {
                        return new Instruction(JasminInstructions.LONG_CONSTANT_1);
                    } else {
                        return new Instruction(JasminInstructions.LOAD_CONSTANT_2_WIDE, String.valueOf(val));
                    }
                }
                case SHORT -> {
                    short val = (short) value.getValue();

                    return new Instruction(JasminInstructions.SI_PUSH, String.valueOf(val));
                }
            };
        /*} else if (value.getValueType() instanceof ReferenceType referenceType) {
            return new LoadConstantInstruction(LoadConstantInstructionType.LOAD_CONSTANT, String.valueOf(value.getValue()));
         */
        } else if (value.getValueType() instanceof ClassType classType) {
            return new LoadConstantInstruction(LoadConstantInstructionType.LOAD_CONSTANT, String.valueOf(value.getValue()));
        } else if (value.getValueType() instanceof ArrayType arrayType) {
            // TODO: ?
            return new Instruction(JasminInstructions.A_NEW_ARRAY, value.getValueType().getRepresentation());
        } else {
            return new Instruction(JasminInstructions.NOP, " ; idk?");
        }

        return new Instruction(JasminInstructions.NOP, " ; idk?");
    }
}
