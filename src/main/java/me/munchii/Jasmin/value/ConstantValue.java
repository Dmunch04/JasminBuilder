package me.munchii.Jasmin.value;

import me.munchii.Jasmin.type.*;

public class ConstantValue implements IValueContainer {
    private JasminValue value;
    
    public ConstantValue(Object value, JasminType valueType) {
        this(new JasminValue(value, valueType));
    }
    
    public ConstantValue(JasminValue value) {
        this.value = value;
    }

    @Override
    public JasminValue getValue() {
        return value;
    }
}
