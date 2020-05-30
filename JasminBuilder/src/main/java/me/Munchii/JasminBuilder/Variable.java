package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Statements.JasminStatement;

import java.util.List;

public interface Variable extends JasminPassable
{

    JasminStatement Store ();
    List<JasminStatement> Declare ();
    List<JasminStatement> PushToStack ();

    String GetName ();
    JasminPassable GetValue ();
    int GetIndex ();
    void SetIndex (int Index);

}
