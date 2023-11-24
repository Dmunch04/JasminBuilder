package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.instruction.type.LoadConstantInstructionType;

public class LoadConstantInstruction implements IJasminInstruction {
    private final LoadConstantInstructionType type;
    private final String value;

    private boolean isString = false;

    public LoadConstantInstruction(LoadConstantInstructionType type, int value) {
        this.type = type;
        this.value = String.valueOf(value);
    }

    public LoadConstantInstruction(LoadConstantInstructionType type, long value) {
        this.type = type;
        this.value = String.valueOf(value);
    }

    public LoadConstantInstruction(LoadConstantInstructionType type, float value) {
        this.type = type;
        this.value = String.valueOf(value);
    }

    public LoadConstantInstruction(LoadConstantInstructionType type, double value) {
        this.type = type;
        this.value = String.valueOf(value);
    }

    public LoadConstantInstruction(LoadConstantInstructionType type, String value) {
        this.type = type;
        this.value = value;
        this.isString = true;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation()).append(" ");
        if (isString) {
            builder.append("\"").append(value).append("\"");
        } else {
            builder.append(value);
        }
    }
}
