package com.hemebiotech.analytics;

import java.io.*;
import java.util.*;

public class AnalyticsCounter {

    public static void main(String[] args) {
        // Get path from properties file.
        IGetPathFile pathFileRead = new ReadPathFromFile();
        // Setup a list from the symptoms file.
        ISymptomReader listSymptom = new ReadSymptomDataFromFile(pathFileRead.getSymptomsFile());
        // Create a map with the number of symptoms sorted in alphabetic order.
        Map<String, Integer> mapSymptoms = sortMap(mapFromList(listSymptom.GetSymptoms()));
        // Write in the result file.
        writeFileOut(mapSymptoms, pathFileRead.getResultFile());
    }

    /**
     * Count the number of element in a list and return a HashMap
     **/
    public static Map<String, Integer> mapFromList(List<String> listFull) {
        Map<String, Integer> new_map = new HashMap<>();
        for (String symptom : listFull) {
            new_map.merge(symptom, 1, Integer::sum);
        }
        return new_map;
    }

    /**
     * Write the key of a list and associated value in a file from a given path
     **/
    public static void writeFileOut(Map<String, Integer> symptomsMap, String outPath) {
        try (Writer wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outPath))))) {
            for (Map.Entry<String, Integer> mSymptom : symptomsMap.entrySet()) {
                wr.write(mSymptom.getKey() + "=" + mSymptom.getValue() + '\n');
            }
        } catch (Exception e) {
            System.err.println("Problem in creating or writing the output file.");
        }
    }

    public static Map<String, Integer> sortMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<>() {
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getKey().compareTo(b.getKey());
            }
        });

        Map<String, Integer> mapSorted = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            mapSorted.put(entry.getKey(), entry.getValue());
        }
        return mapSorted;
    }
}