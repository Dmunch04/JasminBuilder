package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.instruction.type.LocalVariableInstructionType;

public class LocalVariableInstruction implements IJasminInstruction {
    private final LocalVariableInstructionType type;
    private final int index;

    public LocalVariableInstruction(LocalVariableInstructionType type, int index) {
        this.type = type;
        this.index = index;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation()).append(" ").append(index);
    }

    @Override
    public int getStackChange() {
        return type.getStackChange();
    }
}
