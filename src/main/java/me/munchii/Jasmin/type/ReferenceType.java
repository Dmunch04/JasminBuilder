package me.munchii.Jasmin.type;

public class ReferenceType implements IDataType {
    private final String className;

    public ReferenceType(String className) {
        if (className.contains(".")) {
            this.className = className.replace(".", "/");
        } else {
            this.className = className;
        }
    }

    public boolean isString() {
        return className.equals(JavaStd.JAVA_STRING_REFERENCE.getRepresentation());
    }

    @Override
    public String getRepresentation() {
        return className;
    }

    @Override
    public String getIdentifier() {
        return "a";
    }

    @Override
    public boolean isReference() {
        return true;
    }
}
