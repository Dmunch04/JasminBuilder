package me.munchii.Jasmin.value;

import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.type.*;

import java.util.List;

public class JasminValue {
    private Object value;
    private ValueType valueType;

    public JasminValue(Object value, ValueType valueType) {
        this.value = value;
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValue(Object value, ValueType valueType) {
        this.value = value;
        this.valueType = valueType;
    }

    public List<IJasminInstruction> getDeclarationInstruction() {
        if (valueType instanceof PrimitiveType type) {
            switch (type) {
                case BOOLEAN -> {
                    boolean val = (boolean) value;

                    return List.of(new Instruction(val ? JasminInstructions.INTEGER_CONSTANT_1 : JasminInstructions.INTEGER_CONSTANT_0));
                }
                case BYTE -> {
                    byte val = (byte) value;

                    return List.of(new Instruction(JasminInstructions.BI_PUSH, String.valueOf(val)));
                }
                case CHAR -> {
                    char val = (char) value;

                    return List.of(new Instruction(JasminInstructions.BI_PUSH, String.valueOf((int) val)));
                }
                case DOUBLE -> {
                    double val = (double) value;

                    if (val == 0.0d) {
                        return List.of(new Instruction(JasminInstructions.PUSH_DOUBLE_0));
                    } else if (val == 1.0d) {
                        return List.of(new Instruction(JasminInstructions.PUSH_DOUBLE_1));
                    } else {
                        return List.of(new Instruction(JasminInstructions.LOAD_CONSTANT_2_WIDE, String.valueOf(val)));
                    }
                }
                case FLOAT -> {
                    float val = (float) value;

                    if (val == 0.0f) {
                        return List.of(new Instruction(JasminInstructions.FLOAT_CONSTANT_0));
                    } else if (val == 1.0f) {
                        return List.of(new Instruction(JasminInstructions.FLOAT_CONSTANT_1));
                    } else if (val == 2.0f) {
                        return List.of(new Instruction(JasminInstructions.FLOAT_CONSTANT_2));
                    } else {
                        return List.of(new Instruction(JasminInstructions.LOAD_CONSTANT, String.valueOf(val)));
                    }
                }
                case INTEGER -> {
                    int val = (int) value;

                    if (val == -1) {
                        return List.of(new Instruction(JasminInstructions.INTEGER_CONSTANT_NEGATIVE_1));
                    } else if (val == 0) {
                        return List.of(new Instruction(JasminInstructions.INTEGER_CONSTANT_0));
                    } else if (val == 1) {
                        return List.of(new Instruction(JasminInstructions.INTEGER_CONSTANT_1));
                    } else if (val == 2) {
                        return List.of(new Instruction(JasminInstructions.INTEGER_CONSTANT_2));
                    } else if (val == 3) {
                        return List.of(new Instruction(JasminInstructions.INTEGER_CONSTANT_3));
                    } else if (val == 4) {
                        return List.of(new Instruction(JasminInstructions.INTEGER_CONSTANT_4));
                    } else if (val == 5) {
                        return List.of(new Instruction(JasminInstructions.INTEGER_CONSTANT_5));
                    } else {
                        return List.of(new Instruction(JasminInstructions.BI_PUSH, String.valueOf(val)));
                    }
                }
                case LONG -> {
                    long val = (long) value;

                    if (val == 0) {
                        return List.of(new Instruction(JasminInstructions.LONG_CONSTANT_0));
                    } else if (val == 1) {
                        return List.of(new Instruction(JasminInstructions.LONG_CONSTANT_1));
                    } else {
                        return List.of(new Instruction(JasminInstructions.LOAD_CONSTANT_2_WIDE, String.valueOf(val)));
                    }
                }
                case SHORT -> {
                    short val = (short) value;

                    return List.of(new Instruction(JasminInstructions.SI_PUSH, String.valueOf(val)));
                }
            };
        /*} else if (valueType instanceof ReferenceType referenceType) {
            //instructions.add(new Instruction(JasminInstructions.NOP, " ; how?"));
            // TODO: this should call constructor right? if so, what about the args
            String val = String.valueOf(value);
            return new Instruction(JasminInstructions.LOAD_CONSTANT, val);
         */
        } else if (valueType instanceof ClassType classType) {
            //instructions.add(new Instruction(JasminInstructions.NOP, " ; how?"));
            // TODO: this should call constructor right? if so, what about the args
            String val = String.valueOf(value);
            return List.of(new Instruction(JasminInstructions.LOAD_CONSTANT, val));
        } else if (valueType instanceof StringType) {
            String val = "\"" + value + "\"";
            return List.of(new Instruction(JasminInstructions.LOAD_CONSTANT, val));
        } else if (valueType instanceof ArrayType arrayType) {
            return List.of(new Instruction(JasminInstructions.A_NEW_ARRAY, valueType.getRepresentation()));
        } else {
            return List.of(new Instruction(JasminInstructions.NOP, " ; idk?"));
        }

        return List.of(new Instruction(JasminInstructions.NOP, " ; idk?"));
    }

    public IJasminInstruction getStoreInstruction(int id) {
        String index = String.valueOf(id);
        if (valueType instanceof PrimitiveType type) {
            return switch (type) {
                case BOOLEAN, BYTE, CHAR, INTEGER, SHORT -> new Instruction(JasminInstructions.STORE_INTEGER, index);
                case DOUBLE -> new Instruction(JasminInstructions.STORE_DOUBLE, index);
                case FLOAT -> new Instruction(JasminInstructions.STORE_FLOAT, index);
                case LONG -> new Instruction(JasminInstructions.STORE_LONG, index);
            };
        /*} else if (valueType instanceof ReferenceType referenceType) {
            return new Instruction(JasminInstructions.STORE_REFERENCE, index);
         */
        } else if (valueType instanceof ClassType classType) {
            return new Instruction(JasminInstructions.STORE_REFERENCE, index);
        } else if (valueType instanceof StringType stringType) {
            return new Instruction(JasminInstructions.STORE_REFERENCE, index);
        } else if (valueType instanceof ArrayType arrayType) {
            // TODO ?
            return new Instruction(JasminInstructions.STORE_REFERENCE, index);
        } else {
            return new Instruction(JasminInstructions.NOP, " ; idk?");
        }
    }

    public IJasminInstruction getLoadInstruction(int id) {
        String index = String.valueOf(id);
        if (valueType instanceof PrimitiveType type) {
            return switch (type) {
                case BOOLEAN, SHORT, INTEGER, BYTE, CHAR -> new Instruction(JasminInstructions.LOAD_INTEGER, index);
                case DOUBLE -> new Instruction(JasminInstructions.LOAD_DOUBLE, index);
                case FLOAT -> new Instruction(JasminInstructions.LOAD_FLOAT, index);
                case LONG -> new Instruction(JasminInstructions.LOAD_LONG, index);
            };
        /*} else if (valueType instanceof ReferenceType referenceType) {
            return new Instruction(JasminInstructions.LOAD_REFERENCE, index);
         */
        } else if (valueType instanceof ClassType classType) {
            return new Instruction(JasminInstructions.LOAD_REFERENCE, index);
        } else if (valueType instanceof ArrayType arrayType) {
            return new Instruction(JasminInstructions.LOAD_REFERENCE, index);
        } else {
            return new Instruction(JasminInstructions.NOP, " ; idk?");
        }
    }
}
