package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Classes.JasminClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The `JasminFile` class represents a Jasmin file. It can hold several classes
 */
public class JasminFile implements Builder
{

    private final String OutputPath;
    private final String FileName;
    private final List<JasminClass> Classes;

    /**
     * @param OutputPath The destination directory for the file
     * @param FileName The output files name
     */
    public JasminFile (String OutputPath, String FileName)
    {
        this.OutputPath = OutputPath;
        this.FileName = FileName + ".j";
        this.Classes = new ArrayList<JasminClass> ();
    }

    /**
     * Writes all the classes into a file
     */
    public void Write ()
    {
        Path FilePath = Paths.get (OutputPath, FileName);

        try
        {
            new File (OutputPath).mkdirs ();

            File TargetFile = FilePath.toFile ();
            TargetFile.createNewFile ();

            FileWriter Writer = new FileWriter (TargetFile);
            Writer.write (ToOutputString ());
            Writer.close ();
        }

        catch (IOException Error)
        {
            System.out.println ("Failed to write to file: " + FilePath.toString ());
            Error.printStackTrace ();
        }

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

    /**
     * @return A list of all the files classes
     */
    public List<JasminClass> GetClasses ()
    {
        return Classes;
    }

    /**
     * @param Class The class to be added to the file
     */
    public void AddClass (JasminClass Class)
    {
        Classes.add (Class);
    }

}
