package me.Munchii.JasminBuilder.DataTypes;

public abstract class DataType {

    private final String representation;
    private final ValueType type;

    public DataType(String representation, ValueType type) {
        this.representation = representation;
        this.type = type;
    }

    public boolean compare(DataType type) {
        return compare(type.getType());
    }

    public boolean compare(ValueType type) {
        return this.type == type;
    }

    public String getRepresentation() {
        return representation;
    }

    public ValueType getType() {
        return type;
    }

    public boolean isReference() {
        return type == ValueType.REFERENCE;
    }

    @Override
    public String toString() {
        return type.name() + "(" + representation + ")";
    }

    // Common java std classes
    public static final DataType STRING = new ReferenceType("java/lang/String");
    public static final DataType STRING_INSTANCE = new ReferenceType(STRING.representation, true);
    public static final DataType OBJECT = new ReferenceType("java/lang/Object");
    public static final DataType OBJECT_INSTANCE = new ReferenceType(OBJECT.representation, true);
    public static final DataType SYSTEM = new ReferenceType("java/lang/System");
    public static final DataType SYSTEM_INSTANCE = new ReferenceType(SYSTEM.representation, true);
    public static final DataType MATH = new ReferenceType("java/lang/Math");
    public static final DataType MATH_INSTANCE = new ReferenceType(MATH.representation, true);

    // All types so they're easier to access
    public static final DataType BOOLEAN = new BooleanType();
    public static final DataType BYTE = new ByteType();
    public static final DataType CHAR = new CharType();
    public static final DataType DOUBLE = new DoubleType();
    public static final DataType FLOAT = new FloatType();
    public static final DataType INTEGER = new IntegerType();
    public static final DataType LONG = new LongType();
    public static final DataType SHORT = new ShortType();
    public static final DataType VOID = new VoidType();
    //! Use this with caution!!:
    public static final DataType EMPTY_REFERENCE = new ReferenceType("");

}
