package me.munchii.Jasmin.classes;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.field.JasminField;
import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.type.IDataType;
import me.munchii.Jasmin.type.ReferenceType;
import me.munchii.Jasmin.util.DataTypeConversion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JasminClass implements IWritable {
    public final ClassAccessSpec accessSpec;
    public final String className;
    public final String superClass;

    private final List<String> implementsClasses;

    private final List<JasminField> fields;
    private final List<JasminMethod> methods;

    public JasminClass(String className, ClassAccessSpec accessSpec) {
        this(className, accessSpec, "java/lang/Object");
    }

    public JasminClass(String className, ClassAccessSpec accessSpec, String superClass) {
        this.accessSpec = accessSpec;
        this.className = className;
        this.superClass = superClass;

        this.implementsClasses = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public JasminClass implement(ReferenceType classReference) {
        return implement(classReference.getRepresentation());
    }

    public JasminClass implement(String classPath) {
        implementsClasses.add(classPath);

        return this;
    }

    public JasminClass genConstructor(List<IDataType> paramTypes) {
        this.methods.add(null);

        return this;
    }

    public String getSignature() {
        return ".class " + accessSpec.getValue() + " " + className;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(getSignature()).append("\n")
                .append(".super ").append(superClass).append("\n");

        implementsClasses.forEach(cl -> builder.append(".implements ").append(cl).append("\n"));
        builder.append("\n");

        fields.forEach(field -> {
            field.write(builder);
            builder.append("\n");
        });

        methods.forEach(method -> {
            StringBuilder methodComment = new StringBuilder();
            methodComment.append("; method: ")
                    .append(method.accessSpec.getValue()).append(" ")
                    .append(DataTypeConversion.getJavaTypeName(method.returnType)).append(" ")
                    .append(method.methodName).append("(")
                    .append(method.paramTypes.stream().map(DataTypeConversion::getJavaTypeName).collect(Collectors.joining(", ")))
                    .append(");").append("\n");

            builder.append(methodComment);
            method.write(builder);
            builder.append("\n");
        });
    }

    public JasminClass registerField(JasminField field) {
        fields.add(field);

        return this;
    }

    public JasminClass registerMethod(JasminMethod method) {
        methods.add(method);

        return this;
    }
}
