package me.munchii.Jasmin.classes;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.field.JasminField;
import me.munchii.Jasmin.method.ConstructorMethod;
import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.method.MethodAccessSpec;
import me.munchii.Jasmin.type.JasminType;
import me.munchii.Jasmin.type.ReferenceType;
import me.munchii.Jasmin.type.ValueType;
import me.munchii.Jasmin.util.DataTypeConversion;
import me.munchii.Jasmin.util.FieldSpec;
import me.munchii.Jasmin.util.MethodSpec;

import java.util.*;
import java.util.stream.Collectors;

public class JasminClass implements IWritable {
    public final EnumSet<ClassAccessSpec> accessSpec;
    public final String className;
    public final String superClass;

    private final List<String> implementsClasses;

    private final Map<String, JasminField> fields;
    private final Map<String, ConstructorMethod> constructors;
    private final Map<String, JasminMethod> methods;

    public JasminClass(String className, EnumSet<ClassAccessSpec> accessSpec) {
        this(className, accessSpec, "java/lang/Object");
    }

    public JasminClass(String className, EnumSet<ClassAccessSpec> accessSpec, String superClass) {
        this.accessSpec = accessSpec;
        this.className = className;
        this.superClass = superClass;

        this.implementsClasses = new ArrayList<>();
        this.fields = new HashMap<>();
        this.constructors = new HashMap<>();
        this.methods = new HashMap<>();
    }

    public JasminClass implement(ReferenceType classReference) {
        return implement(classReference.getRepresentation());
    }

    public JasminClass implement(String classPath) {
        implementsClasses.add(classPath);

        return this;
    }

    public JasminClass genConstructor(List<ValueType> paramTypes) {
        this.methods.put("", null);

        return this;
    }

    public String getSignature() {
        return ".class " + String.join(" ", accessSpec.stream().map(ClassAccessSpec::getValue).collect(Collectors.toSet())) + " " + className;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(getSignature()).append("\n")
                .append(".super ").append(superClass).append("\n");

        implementsClasses.forEach(cl -> builder.append(".implements ").append(cl).append("\n"));
        builder.append("\n");

        fields.forEach((key, field) -> {
            field.write(builder);
            builder.append("\n");
        });

        constructors.forEach((key, constructor) -> {
            StringBuilder methodComment = new StringBuilder();
            methodComment.append("; method: ")
                    .append(String.join(" ", constructor.accessSpec.stream().map(MethodAccessSpec::getValue).collect(Collectors.toSet()))).append(" ")
                    .append(DataTypeConversion.getJavaTypeName(constructor.returnType)).append(" ")
                    .append(constructor.methodName).append("(")
                    .append(constructor.paramTypes.stream().map(DataTypeConversion::getJavaTypeName).collect(Collectors.joining(", ")))
                    .append(");").append("\n");

            builder.append(methodComment);
            constructor.write(builder);
            builder.append("\n");
        });

        methods.forEach((key, method) -> {
            StringBuilder methodComment = new StringBuilder();
            methodComment.append("; method: ")
                    .append(String.join(" ", method.accessSpec.stream().map(MethodAccessSpec::getValue).collect(Collectors.toSet()))).append(" ")
                    .append(DataTypeConversion.getJavaTypeName(method.returnType)).append(" ")
                    .append(method.methodName).append("(")
                    .append(method.paramTypes.stream().map(DataTypeConversion::getJavaTypeName).collect(Collectors.joining(", ")))
                    .append(");").append("\n");

            builder.append(methodComment);
            method.write(builder);
            builder.append("\n");
        });
    }

    public ReferenceType getReference() {
        return new ReferenceType(className);
    }

    public JasminClass registerField(JasminField field) {
        fields.putIfAbsent(FieldSpec.makeFieldSpec(getReference(), field), field);

        return this;
    }

    public JasminClass registerConstructor(ConstructorMethod constructor) {
        constructors.putIfAbsent(MethodSpec.makeMethodSpec(getReference(), constructor), constructor);

        return this;
    }

    public JasminClass registerMethod(JasminMethod method) {
        methods.putIfAbsent(method.getDescriptor(true), method);

        return this;
    }
}
