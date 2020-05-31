package me.Munchii.JasminBuilder.Fields;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Statements.FieldManipulationStatement;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JasminField implements Builder, JasminPassable {

    // TODO: Surely there must be a better way, than using the `Hook` method

    private final List<FieldAccessSpec> accessSpecs;
    private final String fieldName;
    private final DataType descriptor;
    private final JasminValue value;

    /**
     * @param accessSpecs The access modifiers for the field
     * @param fieldName  The fields name. Used together with class name to access it later on
     * @param descriptor The fields data type
     */
    public JasminField(String fieldName, DataType descriptor, FieldAccessSpec... accessSpecs) {
        this(fieldName, descriptor, null, accessSpecs);
    }

    /**
     * @param accessSpecs The access modifiers for the field
     * @param fieldName  The fields name. Used together with class name to access it later on
     * @param descriptor The fields data type
     * @param value      The fields type
     */
    public JasminField(String fieldName, DataType descriptor, JasminValue value, FieldAccessSpec... accessSpecs) {
        this.accessSpecs = new ArrayList<>();
        this.accessSpecs.addAll(asList(accessSpecs));
        this.fieldName = fieldName;
        this.descriptor = descriptor;
        this.value = value;
    }

    @Override
    public String toOutputString() {
        StringBuilder builder = new StringBuilder();
        builder.append(".field").append(" ");
        accessSpecs.forEach(accessSpec -> builder.append(accessSpec.getRepresentation()).append(" "));
        builder.append(fieldName).append(" ");
        builder.append(descriptor.getRepresentation());

        if (value != null)
            builder.append(" = ").append(value.toOutputString());

        return builder.toString();
    }

    @Override
    public List<JasminStatement> pushToStack() {
        if (targetClass == null)
            throw new IllegalArgumentException("Field class is not set!");

        // TODO: How would we make field spec without knowing the class?
        // Like this?
        if (accessSpecs.contains(FieldAccessSpec.STATIC))
            return asList(new FieldManipulationStatement(FieldManipulationType.GET_STATIC, Helper.makeFieldSpec(targetClass.getClassName(), fieldName), descriptor.getRepresentation()));

        return asList(new FieldManipulationStatement(FieldManipulationType.GET_FIELD, Helper.makeFieldSpec(targetClass.getClassName(), fieldName), descriptor.getRepresentation()));
    }

    @Override
    public DataType getType() {
        return descriptor;
    }

    // Like this?
    private JasminClass targetClass = null;

    public JasminField hook(JasminClass targetClass) {
        this.targetClass = targetClass;
        return this;
    }

    public JasminField dehook() {
        this.targetClass = null;
        return this;
    }

    // ========================
    // Getters & Setters
    // ========================

    public List<FieldAccessSpec> getAccessSpec() {
        return accessSpecs;
    }

    public JasminField addAccessSpec(FieldAccessSpec accessSpec) {
        if (!this.accessSpecs.contains(accessSpec))
            this.accessSpecs.add(accessSpec);

        return this;
    }

    public JasminField removeAccessSpec(FieldAccessSpec accessSpec) {
        this.accessSpecs.remove(accessSpec);

        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public DataType getDescriptor() {
        return descriptor;
    }

    public JasminValue getValue() {
        return value;
    }

}
