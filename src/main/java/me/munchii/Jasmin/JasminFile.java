package me.munchii.Jasmin;

import me.munchii.Jasmin.classes.JasminClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JasminFile implements IWritable {
    private final Path outputPath;

    private final List<JasminClass> classes;

    public JasminFile(String outputPath, String fileName) {
        this(outputPath, fileName, new ArrayList<>());
    }

    public JasminFile(String outputPath, String fileName, List<JasminClass> classes) {
        this.outputPath = Paths.get(outputPath, fileName + ".j");
        this.classes = classes;
    }

    public boolean saveToFile() {
        try {
            if (!new File(outputPath.getParent().toUri()).mkdirs()) {
                return false;
            }

            File file = outputPath.toFile();
            file.createNewFile();

            StringBuilder contents = new StringBuilder();
            write(contents);

            FileWriter writer = new FileWriter(file);
            writer.write(contents.toString());
            writer.close();
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    @Override
    public void write(StringBuilder builder) {
        for (JasminClass jasminClass : classes) {
            jasminClass.write(builder);
            builder.append('\n');
        }
    }

    public Path getOutputPath() {
        return outputPath;
    }

    public List<JasminClass> getClasses() {
        return classes;
    }

    public void addClass(JasminClass... jasminClasses) {
        classes.addAll(Arrays.asList(jasminClasses));
    }

    public void removeClass(JasminClass jasminClass) {
        classes.remove(jasminClass);
    }
}
