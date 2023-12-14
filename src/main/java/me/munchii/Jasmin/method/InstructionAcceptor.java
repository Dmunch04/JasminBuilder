package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.statement.Statement;

import java.util.List;

public interface InstructionAcceptor<T> {
    T addInstruction(IJasminInstruction instruction);
    T addInstructions(List<IJasminInstruction> instructions);

    T removeInstruction(IJasminInstruction instruction);
    List<IJasminInstruction> getInstructions();

    T addStatement(Statement statement);
}
