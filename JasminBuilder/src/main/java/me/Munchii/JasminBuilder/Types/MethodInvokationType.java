package me.Munchii.JasminBuilder.Types;

public enum MethodInvokationType
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

    private MethodInvokationType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
