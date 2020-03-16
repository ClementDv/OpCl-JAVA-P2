package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadPathFromFile implements GetPathFile {
    public static final String PROP_PATH = "properties.txt";
    public static final String DEFAULT_IN = "symptoms.txt";
    public static final String DEFAULT_OUT = "result.out";

    @Override
    public String getSymptomsFile() throws IOException {
        return lineReadProp(1, DEFAULT_IN);
    }

    @Override
    public String getResultFile() throws IOException {
        return lineReadProp(2, DEFAULT_OUT);
    }

    public static String lineReadProp(int nb, String defValue) throws IOException {
        BufferedReader inputStream = null;
        String line = null;

        try {
            inputStream = new BufferedReader(new FileReader(PROP_PATH));
            for (int i = 0; i < nb; i++) line = inputStream.readLine();
            if (line != null) {
                return line;
            }
        } catch (FileNotFoundException fnfe) {
            // Ignore it to return default value
        } catch (IOException e) {
            System.err.println("Erreur IO : " + e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return defValue;
    }

}