package me.Munchii.JasminBuilder.Types;

public enum MethodInvocationType
{

    // ??
    InvokeNonVirtual ("invokenonvirtual"),

    // Invoke a class (static) method
    InvokeStatic ("invokestatic"),

    // Invoke instance method; dispatch based on class
    InvokeVirtual ("invokevirtual"),

    // ??
    InvokeSpecial ("invokespecial");

    private String Representation;

    private MethodInvocationType(String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
