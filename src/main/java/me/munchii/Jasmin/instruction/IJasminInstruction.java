package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.IWritable;

public interface IJasminInstruction extends IWritable {
    int getStackChange();

    default String getString() {
        StringBuilder builder = new StringBuilder();
        write(builder);
        return builder.toString();
    }
}
