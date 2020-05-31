package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.DataTypes.DataType;

public class JasminType {

    private final String descriptor;

    /**
     * @param type The data type
     */
    public JasminType(DataType type) {
        this(type.getRepresentation());
    }

    private JasminType(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * @param s The class spec
     * @return A new jasmin type
     */
    public static JasminType customClass(String s) {
        return new JasminType("L" + s);
    }

    /**
     * @return The type descriptor
     */
    public String getDescriptor() {
        return descriptor;
    }

}
