package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;

public class JasminSizedIndex
{

    private int[] Indexes;

    public JasminSizedIndex (int... Indexes)
    {
        if (Indexes.length <= 0)
        {
            Logger.Error ("You must specify at least 1 size!");
            throw new AbortException ();
        }

        this.Indexes = Indexes;
    }

    public int GetLength ()
    {
        return Indexes.length;
    }

    public int[] GetIndexes ()
    {
        return Indexes;
    }

    public int GetIndex (int Index)
    {
        return Indexes[Index];
    }

}
