package me.munchii.Jasmin.field;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.classes.JasminClass;
import me.munchii.Jasmin.type.JasminType;

import java.util.EnumSet;
import java.util.stream.Collectors;

public class JasminField implements IWritable {
    public final JasminClass parent;
    public final EnumSet<FieldAccessSpec> accessSpec;
    public final String fieldName;
    public final JasminType fieldType;
    // TODO: allow for value holding (value upon creation - .field <spec> <name> <type> = VALUE - is only allowed for final fields)

    public JasminField(JasminClass parent, String fieldName, EnumSet<FieldAccessSpec> accessSpec, JasminType fieldType) {
        this.parent = parent;
        this.accessSpec = accessSpec;
        this.fieldName = fieldName;
        this.fieldType = fieldType;

        parent.registerField(this);
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(".field ")
                .append(String.join(" ", accessSpec.stream().map(FieldAccessSpec::getValue).collect(Collectors.toSet()))).append(" ")
                .append(fieldName).append(" ")
                .append(fieldType.getRepresentation());
    }
}
