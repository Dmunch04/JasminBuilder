/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.value;

import me.munchii.Jasmin.result.JasminError;
import me.munchii.Jasmin.result.JasminResult;
import me.munchii.Jasmin.type.ReturnableType;

public class Returnable {
    private final Object value;
    private final ReturnableType valueType;

    public Returnable() {
        this(null, ReturnableType.VOID);
    }

    public Returnable(Object value, ReturnableType valueType) {
        this.value = value;
        this.valueType = valueType;
    }

    public static JasminResult<Returnable> of(JasminValue value) {
        if (value.getValueType() instanceof ReturnableType type) {
            return JasminResult.ofResult(new Returnable(value.getValue(), type));
        }

        return JasminResult.ofInfo(new JasminError("type of provided value must be a returnable type"));
    }

    public static Returnable of() {
        return new Returnable();
    }
}
