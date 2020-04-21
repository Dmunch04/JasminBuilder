package me.Munchii.JasminBuilder.Types;

public enum LocalVariableType
{

    // Return from subroutine
    Return ("ret"),

    // Load reference from local variable
    LoadReference ("aload"),

    // Store reference into local variable
    StoreReference ("astore"),

    // Load double from local variable
    LoadDouble ("dload"),

    // Store double into local variable
    StoreDouble ("dstore"),

    // Load float from local variable
    LoadFloat ("fload"),

    // Store float into local variable
    StoreFloat ("fstore"),

    // Load int from local variable
    LoadInteger ("iload"),

    // Store int into local variable
    StoreInteger ("istore"),

    // Load long from local variable
    LoadLong ("lload"),

    // Store long into local variable
    StoreLong ("lstore");


    private String Representation;

    private LocalVariableType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
