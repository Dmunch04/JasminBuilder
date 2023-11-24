/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.type;

public class StringType implements IDataType {
    @Override
    public String getRepresentation() {
        // TODO: which?
        //return JavaStd.JAVA_STRING_INSTANCE.getRepresentation();
        return JavaStd.JAVA_STRING_REFERENCE.getRepresentation();
    }

    @Override
    public String getIdentifier() {
        return "a";
    }

    @Override
    public boolean isReference() {
        return true;
    }
}
