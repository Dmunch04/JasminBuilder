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
public class JasminFile implements Builder {

    private final String outputPath;
    private final String fileName;
    private final List<JasminClass> classes;

    /**
     * @param outputPath The destination directory for the file
     * @param fileName   The output files name
     */
    public JasminFile(String outputPath, String fileName) {
        this.outputPath = outputPath;
        this.fileName = fileName + ".j";
        this.classes = new ArrayList<>();
    }

    /**
     * Writes all the classes into a file
     */
    public void write() {
        Path filePath = Paths.get(outputPath, fileName);

        try {
            new File(outputPath).mkdirs();

            File file = filePath.toFile();
            file.createNewFile();

            FileWriter writer = new FileWriter(file);
            writer.write(toOutputString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write to file: " + filePath.toString());
            e.printStackTrace();
        }

    }

    @Override
    public String toOutputString() {
        StringBuilder builder = new StringBuilder();
        for (JasminClass jasminClass : classes) {
            builder.append(jasminClass.toOutputString()).append("\n\n");
        }

        // Remove the last newlines
        builder.delete(builder.length() - 3, builder.length() - 1);

        return builder.toString();
    }

    // ========================
    // Getters & Setters
    // ========================

    /**
     * @return A list of all the files classes
     */
    public List<JasminClass> getClasses() {
        return classes;
    }

    /**
     * @param jasminClass The class to be added to the file
     */
    public void addClass(JasminClass jasminClass) {
        classes.add(jasminClass);
    }

}
