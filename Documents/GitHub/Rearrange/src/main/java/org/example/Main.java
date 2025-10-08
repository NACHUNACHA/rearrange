package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.models.InputFile;
import org.example.models.OutputResult;
import org.example.utils.CardUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path inputDir = Paths.get("Input");
        Path outputDir = Paths.get("Output");

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        File[] files = inputDir.toFile().listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null || files.length == 0) {
            System.out.println("⚠️ No JSON files found in Input directory. Exiting...");
            return;
        }

        for (File file : files) {
            try {
                InputFile inputFile = mapper.readValue(file, InputFile.class);

                OutputResult result = CardUtil.process(inputFile.cards);

                mapper.writeValue(outputDir.resolve(file.getName()).toFile(), result);
                System.out.println("Done: " + file.getName());
            } catch (Exception e) {
                System.out.println("Error processing file: " + file.getName());
                e.printStackTrace();
            }
        }

        System.out.println("All files processed successfully!");
    }
}
