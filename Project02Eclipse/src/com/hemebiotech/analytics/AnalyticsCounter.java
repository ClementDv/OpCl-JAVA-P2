package com.hemebiotech.analytics;

import java.io.*;
import java.util.*;

public class AnalyticsCounter {

    public static void main(String[] args) throws IOException {
        GetPathFile pathFileRead = new ReadPathFromFile();
        ISymptomReader listSymptom = new ReadSymptomDataFromFile(pathFileRead.getSymptomsFile());

        Map<String, Integer> mapSymptoms = mapFromList(listSymptom.GetSymptoms());

        writeFileOut(mapSymptoms, pathFileRead.getResultFile());
    }

    public static Map<String, Integer> mapFromList(List<String> listFull) {
        Map<String, Integer> new_map = new HashMap<>();
        for (String symptom : listFull) {
            new_map.merge(symptom, 1, Integer::sum);
        }
        return new_map;
    }

    public static void writeFileOut(Map<String, Integer> symptomsMap, String outPath) {
        try (Writer wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outPath))))) {
            for (Map.Entry<String, Integer> mSymptom : symptomsMap.entrySet()) {
                wr.write(mSymptom.getKey() + "=" + mSymptom.getValue() + '\n');
            }
        } catch (Exception e) {
            System.err.println("Problème dans la création ou dans l'écriture du fichier de sortie");
        }
    }

}