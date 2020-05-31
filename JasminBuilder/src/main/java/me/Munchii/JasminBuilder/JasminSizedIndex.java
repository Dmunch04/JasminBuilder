package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;

public class JasminSizedIndex {

    private final int[] indexes;

    public JasminSizedIndex(int... indexes) {
        if (indexes.length <= 0) {
            Logger.error("You must specify at least 1 size!");
            throw new AbortException();
        }

        this.indexes = indexes;
    }

    public int getLength() {
        return indexes.length;
    }

    public int[] getIndexes() {
        return indexes;
    }

    public int getIndex(int index) {
        return indexes[index];
    }

}
