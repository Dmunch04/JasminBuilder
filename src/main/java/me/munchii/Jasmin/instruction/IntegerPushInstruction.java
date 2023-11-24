package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.instruction.type.IntegerPushInstructionType;

public class IntegerPushInstruction implements IJasminInstruction {
    private final IntegerPushInstructionType type;
    private final int value;

    public IntegerPushInstruction(IntegerPushInstructionType type, int value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation()).append(" ").append(value);
    }
}
