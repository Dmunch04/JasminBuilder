package me.Munchii.JasminBuilder.Types;

public enum LoadConstantType
{

    // Push item from run-time constant pool
    LoadConstant ("ldc"),

    // Push item from run-time constant pool (wide index)
    LoadConstantW ("ldc_w"); // For `long` and `double`

    private String Representation;

    private LoadConstantType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
