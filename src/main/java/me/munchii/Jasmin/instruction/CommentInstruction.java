/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.instruction;

public class CommentInstruction implements IJasminInstruction {
    private final String comment;

    public CommentInstruction(String comment) {
        this.comment = comment;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append("; ").append(comment);
    }

    @Override
    public int getStackChange() {
        return 0;
    }
}
