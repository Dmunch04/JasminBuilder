/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.CommentInstruction;
import me.munchii.Jasmin.instruction.IJasminInstruction;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InstructionGroup implements IJasminInstruction {
    private String groupName;
    private final List<IJasminInstruction> instructions;

    public InstructionGroup(List<IJasminInstruction> instructions) {
        this("", instructions);
    }

    public InstructionGroup(String groupName, List<IJasminInstruction> instructions) {
        this.groupName = groupName;
        this.instructions = instructions;
    }

    public InstructionGroup add(IJasminInstruction instruction) {
        instructions.add(instruction);

        return this;
    }

    public InstructionGroup addAll(Collection<IJasminInstruction> instructions) {
        this.instructions.addAll(instructions);

        return this;
    }

    public InstructionGroup remove(IJasminInstruction instruction) {
        instructions.remove(instruction);

        return this;
    }

    public InstructionGroup remove(int index) {
        instructions.remove(index);

        return this;
    }

    public IJasminInstruction get(int index) {
        return instructions.get(index);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public void write(StringBuilder builder) {
        if (!groupName.isEmpty()) {
            new CommentInstruction(groupName).write(builder);
        }
        instructions.forEach(inst -> inst.write(builder));
    }

    @Override
    public String getString() {
        StringBuilder builder = new StringBuilder();
        instructions.forEach(inst -> {
            inst.write(builder);
            builder.append("\n");
        });

        return builder.toString().trim();
    }

    @Override
    public int getStackChange() {
        AtomicInteger change = new AtomicInteger(0);

        instructions.forEach(inst -> change.getAndAdd(inst.getStackChange()));

        return change.get();
    }
}
