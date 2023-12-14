/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.value;

import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.type.ValueType;

public class ValueReference implements IValueContainer {
    private final JasminMethod parent;
    private final int id;
    private final ValueType type;

    public ValueReference(JasminMethod parent, int id, ValueType type) {
        this.parent = parent;
        this.id = id;
        this.type = type;
    }

    @Override
    public JasminValue getValue() {
        return null;
    }
}
