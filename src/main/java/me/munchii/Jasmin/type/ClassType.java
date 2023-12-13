package me.munchii.Jasmin.type;

public class ClassType implements ReturnableType, ValueType {
    // TODO: is this value type?
    private final String className;

    public ClassType(String className) {
        if (className.contains(".")) {
            this.className = className.replace(".", "/");
        } else {
            this.className = className;
        }
    }

    public boolean isString() {
        return className.equals(JavaStd.JAVA_STRING_INSTANCE.getRepresentation());
    }

    @Override
    public String getRepresentation() {
        return "L" + className + ";";
    }

    @Override
    public String getIdentifier() {
        // TODO: is this correct?
        return "a";
    }

    @Override
    public boolean isReference() {
        return true;
    }
}
