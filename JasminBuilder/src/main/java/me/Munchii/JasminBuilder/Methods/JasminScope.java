package me.Munchii.JasminBuilder.Methods;

import me.Munchii.JasminBuilder.Statements.JasminStatement;

import java.util.ArrayList;
import java.util.List;

public class JasminScope {

    private final List<JasminStatement> statements;

    public JasminScope() {
        this.statements = new ArrayList<>();
    }

    public JasminScope addStatement(JasminStatement statement) {
        statements.add(statement);
        return this;
    }

    public JasminScope addStatements(List<JasminStatement> statements) {
        this.statements.addAll(statements);
        return this;
    }

    public List<JasminStatement> getStatements() {
        return statements;
    }

}
