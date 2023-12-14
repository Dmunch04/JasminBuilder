/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.method;

import me.munchii.Jasmin.classes.JasminClass;
import me.munchii.Jasmin.type.ReturnableType;

import java.util.EnumSet;

public class StaticConstructorMethod extends JasminMethod {
    // TODO: can we have params? or access specs?
    public StaticConstructorMethod(JasminClass parent) {
        super(parent, "<clinit>", EnumSet.of(MethodAccessSpec.STATIC), ReturnableType.VOID);
    }
}
