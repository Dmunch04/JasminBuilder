package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.FieldManipulationType;

public class FieldManipulationStatement implements JasminStatement
{

    private FieldManipulationType Type;
    private String FieldSpec;
    private String Descriptor;

    public FieldManipulationStatement (FieldManipulationType Type, String FieldSpec, String Descriptor)
    {
        this.Type = Type;
        this.FieldSpec = FieldSpec;
        this.Descriptor = Descriptor;
    }

    @Override
    public String ToOutputString ()
    {
        StringBuilder Builder = new StringBuilder ();
        Builder.append (Type.GetRepresentation ()).append (" ");
        Builder.append (FieldSpec).append (" ");
        Builder.append (Descriptor);

        return Builder.toString ();
    }

}
