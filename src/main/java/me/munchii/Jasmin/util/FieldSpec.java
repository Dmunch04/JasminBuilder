package me.munchii.Jasmin.util;

import me.munchii.Jasmin.field.JasminField;
import me.munchii.Jasmin.type.ReferenceType;

public class FieldSpec {
    public static String makeFieldSpec(ReferenceType classReference, JasminField field) {
        return makeFieldSpec(classReference, field.fieldName);
    }

    public static String makeFieldSpec(ReferenceType classReference, String fieldName) {
        return classReference + "/" + fieldName;
    }
}
