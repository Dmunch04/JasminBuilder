package me.munchii.Jasmin.value;

import me.munchii.Jasmin.instruction.IJasminInstruction;

import java.util.List;

public interface IValueContainer {
    JasminValue getValue();

    default List<IJasminInstruction> pushValue() {
        return getValue().getDeclarationInstruction();
    }
}
