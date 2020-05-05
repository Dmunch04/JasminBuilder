package me.Munchii.JasminBuilder.Types;

public enum LoadConstantType
{

    // Push item from run-time constant pool
    LoadConstant ("ldc"),

    // Push item from run-time constant pool (wide index)
    LoadConstantWide("ldc_w"), // For `long` and `double`

    LoadConstant2 ("ldc2"),

    LoadConstant2Wide ("ldc2_w");

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
