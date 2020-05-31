package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Utils.Helper;

public class InitFieldInstruction implements JasminInstruction {

    private final String className;
    private final JasminField field;
    private final JasminPassable value;
    private final String comment;

    public InitFieldInstruction(String className, JasminField field) {
        this(className, field, field.getValue());
    }

    public InitFieldInstruction(JasminClass jasminClass, JasminField field) {
        this(jasminClass, field, field.getValue());
    }

    public InitFieldInstruction(JasminClass jasminClass, JasminField field, JasminPassable value) {
        this(jasminClass.getClassName(), field, value);
    }

    public InitFieldInstruction(String className, JasminField field, JasminPassable value) {
        this.field = field;
        this.value = value;
        this.className = className;

        StringBuilder fieldComment = new StringBuilder();
        fieldComment.append("Field: ");
        field.getAccessSpec().forEach(Spec -> fieldComment.append(Spec.getRepresentation()).append(" "));
        fieldComment.append(Helper.getDataTypeName(field.getType())).append(" ");
        // TODO: Find a way to represent value (`JasminPassable`)
        fieldComment.append(field.getFieldName()).append(" = ").append(value).append(";");
        this.comment = fieldComment.toString();
    }

    @Override
    public void write(JasminMethod method) {
        method.addComment(comment);

        method.addStatements(value.pushToStack());

        if (field.getAccessSpec().contains(FieldAccessSpec.STATIC)) {
            method.addFieldManipulationStatement(FieldManipulationType.PUT_STATIC, Helper.makeFieldSpec(className, field.getFieldName()), field.getType());
        } else {
            method.addFieldManipulationStatement(FieldManipulationType.PUT_FIELD, Helper.makeFieldSpec(className, field.getFieldName()), field.getType());
        }
    }

    @Override
    public void write(JasminBlock block) {
        block.addComment(comment);

        block.addStatements(value.pushToStack());

        if (field.getAccessSpec().contains(FieldAccessSpec.STATIC)) {
            block.addFieldManipulationStatement(FieldManipulationType.PUT_STATIC, Helper.makeFieldSpec(className, field.getFieldName()), field.getType());
        } else {
            block.addFieldManipulationStatement(FieldManipulationType.PUT_FIELD, Helper.makeFieldSpec(className, field.getFieldName()), field.getType());
        }
    }
}
