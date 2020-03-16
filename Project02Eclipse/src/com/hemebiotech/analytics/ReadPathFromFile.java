package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadPathFromFile implements GetPathFile {
    // Default value
    public static final String PROP_PATH = "properties.txt";
    public static final String DEFAULT_IN = "symptoms.txt";
    public static final String DEFAULT_OUT = "results.out";

    @Override
    public String getSymptomsFile() {
        return lineReadProp(1, DEFAULT_IN);
    }

    @Override
    public String getResultFile() {
        return lineReadProp(2, DEFAULT_OUT);
    }

    /**
     * Check if properties file exist and verifies validity of it.
     * If it is valid it returns line asked.
     * Sends a default value, which is return if nothing found.
     **/
    public static String lineReadProp(int nb, String defValue) {

        String line = null;
        try (BufferedReader inputStream = new BufferedReader(new FileReader(PROP_PATH))) {
            for (int i = 0; i < nb; i++) line = inputStream.readLine();
            if (line != null) {
                return line;
            }
        } catch (FileNotFoundException fnfe) {
            // Ignore it to return default value
        } catch (IOException e) {
            System.err.println("Error IO : " + e.getMessage());
        }
        return defValue;
    }

}