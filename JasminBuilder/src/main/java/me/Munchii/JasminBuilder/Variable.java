package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Statements.JasminStatement;

import java.util.List;

public interface Variable extends JasminPassable {

    JasminStatement store();

    List<JasminStatement> declare();

    List<JasminStatement> pushToStack();

    String getName();

    JasminPassable getValue();

    int getIndex();

    void setIndex(int index);

}
