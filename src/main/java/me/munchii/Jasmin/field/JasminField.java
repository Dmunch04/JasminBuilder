package me.munchii.Jasmin.field;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.type.IDataType;

public class JasminField implements IWritable {
    public final FieldAccessSpec accessSpec;
    public final String fieldName;
    public final IDataType fieldType;
    // TODO: allow for value holding (value upon creation - .field <spec> <name> <type> = VALUE - is only allowed for final fields)

    public JasminField(String fieldName, FieldAccessSpec accessSpec, IDataType fieldType) {
        this.accessSpec = accessSpec;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(".field ")
                .append(accessSpec.getValue()).append(" ")
                .append(fieldName).append(" ")
                .append(fieldType.getRepresentation());
    }
}
