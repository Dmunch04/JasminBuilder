package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.type.*;
import me.munchii.Jasmin.value.JasminValue;

import java.util.ArrayList;
import java.util.List;

public class VariableReference extends LocalVariable {
    // TODO: allow to reassign this somehow (maybe this already works with super.storeNewValue(value);
    public final ValueType variableType;

    public VariableReference(String name, int id, ValueType variableType) {
        super(name, id, new JasminValue(null, variableType));

        this.variableType = variableType;
    }

    @Override
    public boolean isReference() {
        return true;
    }
}
