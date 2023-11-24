package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.value.JasminValue;
import me.munchii.Jasmin.value.IValueContainer;

import java.util.List;

public class LocalVariable implements IValueContainer {
    public final String name;
    public final int id;

    private JasminValue value;

    public LocalVariable(String name, int id, JasminValue value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    public List<IJasminInstruction> declare() {
        return List.of(value.getDeclarationInstruction(), value.getStoreInstruction(id));
    }

    public List<IJasminInstruction> load() {
        return List.of(value.getLoadInstruction(id));
    }

    public List<IJasminInstruction> store() {
        return List.of(value.getStoreInstruction(id));
    }

    public List<IJasminInstruction> storeNewValue(JasminValue value) {
        this.value = value;
        return declare();
    }

    @Override
    public JasminValue getValue() {
        return value;
    }
}
