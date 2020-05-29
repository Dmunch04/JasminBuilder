package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.JasminPassable;

public class ReturnStatement implements JasminStatement
{

    private JasminPassable Value;

    public ReturnStatement ()
    {
        this (null);
    }

    public ReturnStatement (JasminPassable Value)
    {
        this.Value = Value;
    }

    @Override
    public String ToOutputString ()
    {
        if (Value == null)
            return "return";

        StringBuilder Builder = new StringBuilder ();
        for (JasminStatement Statement : Value.PushToStack ())
        {
            //System.out.println(Statement.ToOutputString());
            Builder.append (Statement.ToOutputString ()).append ("\n\t");
        }
        Builder.append ("\n").append ("\t");

        switch (Value.GetType ().GetType ())
        {
            case Boolean:
            case Byte:
            case Char:
            case Short:
            case Integer: Builder.append ("ireturn"); break;

            case Float: Builder.append ("freturn"); break;

            case Double: Builder.append ("dreturn"); break;

            case Long: Builder.append ("lreturn"); break;

            case Array:
            case Reference: Builder.append ("areturn"); break;

            case Void: Builder.append ("return"); break;
        }

        return Builder.toString ();
    }

}
