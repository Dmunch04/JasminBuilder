package me.Munchii.JasminBuilder.DataTypes;

public class ReferenceType extends DataType {

    private final boolean isInstance;

    public ReferenceType(String className) {
        this(className, false);
    }

    public ReferenceType(String className, boolean isInstance) {
        super(isInstance ? "L" + className + ";" : className, ValueType.REFERENCE);

        this.isInstance = isInstance;
    }

    // TODO: Hmm
    public static DataType makeReferenceInstance(DataType type) {
        if (!(type.getType() == ValueType.REFERENCE)) {
            //Logger.Warning ("Cannot make non-reference an instance");
            return type;
        }

        if (!((ReferenceType) type).isInstance())
            return new ReferenceType(type.getRepresentation(), true);

        return new ReferenceType(type.getRepresentation());
    }

    public boolean isInstance() {
        return isInstance;
    }

}
