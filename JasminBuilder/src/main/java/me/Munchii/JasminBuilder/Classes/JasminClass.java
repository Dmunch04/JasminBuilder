package me.Munchii.JasminBuilder.Classes;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class JasminClass implements Builder {

    private final List<ClassAccessSpec> accessSpecs;
    private final String className;
    private final String superClass;
    private final List<String> implementsClasses;

    private final List<JasminField> fields;
    private final List<JasminMethod> methods;

    public JasminClass(String className, ClassAccessSpec... accessSpecs) {
        this(className, DataType.OBJECT.getRepresentation(), accessSpecs);
    }

    public JasminClass(String className, String superClass, ClassAccessSpec... accessSpecs) {
        this.accessSpecs = new ArrayList<>();
        this.accessSpecs.addAll(asList(accessSpecs));
        this.className = className;
        this.superClass = superClass;
        this.implementsClasses = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    @Override
    public String toOutputString() {
        StringBuilder builder = new StringBuilder();
        builder.append(".class").append(" ");
        accessSpecs.forEach(accessSpec -> builder.append(accessSpec.getRepresentation()).append(" "));
        builder.append(className).append("\n");
        builder.append(".super").append(" ");
        builder.append(superClass).append("\n");

        for (String implement : implementsClasses) {
            builder.append(".super").append(" ");
            builder.append(implement).append("\n");
        }

        builder.append("\n");

        for (JasminField field : fields) {
            builder.append(field.toOutputString()).append("\n");
        }

        if (!fields.isEmpty())
            builder.append("\n");

        for (JasminMethod method : methods) {
            StringBuilder methodComment = new StringBuilder();
            methodComment.append("; Method: ");
            // Access specs
            method.getAccessSpecs().forEach(accessSpec -> methodComment.append(accessSpec.getRepresentation()).append(" "));
            // Return type
            methodComment.append(Helper.getDataTypeName(method.getMethodReturnType())).append(" ");
            // Method name
            methodComment.append(method.getMethodName()).append(" (");
            // Params
            String paramTypes = String.join(", ", method.getParamTypes().stream().map(Helper::getDataTypeName).collect(Collectors.toList()));
            methodComment.append(paramTypes).append(");");

            builder.append(methodComment.toString()).append("\n");
            builder.append(method.toOutputString()).append("\n").append("\n");
        }

        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    // ========================
    // Getters & Setters
    // ========================

    public List<ClassAccessSpec> getAccessSpecs() {
        return accessSpecs;
    }

    public JasminClass addAccessSpec(ClassAccessSpec accessSpec) {
        if (!this.accessSpecs.contains(accessSpec))
            this.accessSpecs.add(accessSpec);

        return this;
    }

    public JasminClass removeAccessSpec(ClassAccessSpec accessSpec) {
        this.accessSpecs.remove(accessSpec);

        return this;
    }

    public String getClassName() {
        return className;
    }

    public String getSuperClass() {
        return superClass;
    }

    public List<String> getImplementsClasses() {
        return implementsClasses;
    }

    public JasminClass addImplement(String s) {
        implementsClasses.add(s);
        return this;
    }

    public List<JasminField> getFields() {
        return fields;
    }

    public JasminClass addField(JasminField field) {
        fields.add(field);
        field.hook(this);
        return this;
    }

    public List<JasminMethod> getMethods() {
        return methods;
    }

    public JasminClass addMethod(JasminMethod method) {
        methods.add(method);
        return this;
    }

}
