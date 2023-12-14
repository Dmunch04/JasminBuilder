package me.munchii.Jasmin.statement;

import me.munchii.Jasmin.method.InstructionAcceptor;
import me.munchii.Jasmin.method.JasminMethod;

public interface Statement {
    <T> void write(InstructionAcceptor<T> acceptor);
}
