package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Classes.JasminClass;

import java.util.ArrayList;
import java.util.List;

public class JasminFile implements Builder
{

    private String OutputPath;
    private String FileName;
    private List<JasminClass> Classes;

    public JasminFile (String OutputPath, String FileName)
    {
        this.OutputPath = OutputPath;
        this.FileName = FileName + ".j";
        this.Classes = new ArrayList<JasminClass> ();
    }

    public void Write ()
    {
        // TODO: Write to file
    }

    @Override
    public String ToOutputString ()
    {
        StringBuilder Builder = new StringBuilder ();
        for (JasminClass Class : Classes)
        {
            Builder.append (Class.ToOutputString ()).append ("\n\n");
        }

        // Remove the last newlines
        Builder.delete (Builder.length () - 3, Builder.length () - 1);

        return Builder.toString ();
    }

    // ========================
    // Getters & Setters
    // ========================

    public List<JasminClass> GetClasses ()
    {
        return Classes;
    }

    public void AddClass (JasminClass Class)
    {
        Classes.add (Class);
    }

}
