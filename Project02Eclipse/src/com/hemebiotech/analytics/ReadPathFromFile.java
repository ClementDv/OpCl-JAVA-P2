package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadPathFromFile implements GetPathFile{
    public static final java.lang.String propPath = "properties.txt";

    public static String lineReadProp(int nb, String defValue) throws IOException {
        BufferedReader inputStream = null;
        String line = null;

        try {
            inputStream = new BufferedReader(new FileReader(propPath));
            for (int i = 0; i < nb; i++) line = inputStream.readLine();
            System.out.println(line);
            return line;
        } catch (FileNotFoundException flfe) {
            System.out.println("Erreur : Le fichier de propriétées n'existe pas, est un dossier ou ne peut être lu.");
        } catch (IOException ioe) {
            System.out.println("Erreur IO.");
        } finally {
            if (inputStream != null) {
                inputStream.close();
                System.out.println(1);
            }
        }
        return defValue;
    }

    @Override
    public String ReadFile() throws IOException {
        return lineReadProp(1, "symptoms.txt");
    }

    @Override
    public String OutFile() throws IOException {
        return lineReadProp(2, "result.out");
    }
}
