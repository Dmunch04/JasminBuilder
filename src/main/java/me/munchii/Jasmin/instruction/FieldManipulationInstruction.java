package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.field.JasminField;
import me.munchii.Jasmin.instruction.type.FieldManipulationInstructionType;
import me.munchii.Jasmin.type.JasminType;
import me.munchii.Jasmin.type.ReferenceType;
import me.munchii.Jasmin.type.ValueType;
import me.munchii.Jasmin.util.FieldSpec;

public class FieldManipulationInstruction implements IJasminInstruction {
    private final FieldManipulationInstructionType type;
    private final String fieldSpec;
    private final ValueType fieldType;

    public FieldManipulationInstruction(FieldManipulationInstructionType type, ReferenceType classReference, JasminField field) {
        this(type, FieldSpec.makeFieldSpec(classReference, field), field.fieldType);
    }

    public FieldManipulationInstruction(FieldManipulationInstructionType type, String fieldSpec, ValueType fieldType) {
        this.type = type;
        this.fieldSpec = fieldSpec;
        this.fieldType = fieldType;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation()).append(" ").append(fieldSpec).append(" ").append(fieldType.getRepresentation());
    }

    @Override
    public int getStackChange() {
        return type.getStackChange();
    }
}
