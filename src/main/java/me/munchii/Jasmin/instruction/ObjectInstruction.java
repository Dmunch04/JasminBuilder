package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.instruction.type.ObjectInstructionType;
import me.munchii.Jasmin.type.ReferenceType;

public class ObjectInstruction implements IJasminInstruction {
    private final ObjectInstructionType type;
    private final ReferenceType classReference;

    public ObjectInstruction(ObjectInstructionType type, ReferenceType classReference) {
        this.type = type;
        this.classReference = classReference;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation()).append(" ").append(classReference.getRepresentation());
    }

    @Override
    public int getStackChange() {
        return type.getStackChange();
    }
}
