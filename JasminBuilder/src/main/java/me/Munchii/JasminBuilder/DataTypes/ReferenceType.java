package me.Munchii.JasminBuilder.DataTypes;

import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;

public class ReferenceType extends DataType
{

    private final boolean IsInstance;

    public ReferenceType (String Class)
    {
        this (Class, false);
    }

    public ReferenceType (String Class, boolean IsInstance)
    {
        super (IsInstance ? "L" + Class + ";" : Class, ValueType.Reference);

        this.IsInstance = IsInstance;
    }

    // TODO: Hmm
    public static DataType MakeReferenceInstance (DataType Type)
    {
        if (!(Type.GetType () == ValueType.Reference))
        {
            //Logger.Warning ("Cannot make non-reference an instance");
            return Type;
        }

        if (!((ReferenceType) Type).IsInstance ())
            return new ReferenceType (Type.GetRepresentation (), true);

        return new ReferenceType (Type.GetRepresentation ());
    }

    public boolean IsInstance()
    {
        return IsInstance;
    }

}
