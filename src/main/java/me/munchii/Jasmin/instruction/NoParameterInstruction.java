package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.instruction.type.NoParameterInstructionType;

public class NoParameterInstruction implements IJasminInstruction {
    private final NoParameterInstructionType type;

    public NoParameterInstruction(NoParameterInstructionType type) {
        this.type = type;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation());
    }
}
