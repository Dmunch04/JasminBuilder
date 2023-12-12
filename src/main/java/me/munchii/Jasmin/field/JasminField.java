package me.munchii.Jasmin.field;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.type.JasminType;

import java.util.EnumSet;
import java.util.stream.Collectors;

public class JasminField implements IWritable {
    public final EnumSet<FieldAccessSpec> accessSpec;
    public final String fieldName;
    public final JasminType fieldType;
    // TODO: allow for value holding (value upon creation - .field <spec> <name> <type> = VALUE - is only allowed for final fields)

    public JasminField(String fieldName, EnumSet<FieldAccessSpec> accessSpec, JasminType fieldType) {
        this.accessSpec = accessSpec;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(".field ")
                .append(String.join(" ", accessSpec.stream().map(FieldAccessSpec::getValue).collect(Collectors.toSet()))).append(" ")
                .append(fieldName).append(" ")
                .append(fieldType.getRepresentation());
    }
}
