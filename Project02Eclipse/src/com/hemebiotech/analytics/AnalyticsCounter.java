package com.hemebiotech.analytics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class AnalyticsCounter {

    public static void writeFileOut(Map<String, Integer> symptomsMap, String outPath) {
        try {
            File file = new File(outPath);
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);

            for (Map.Entry<String, Integer> m : symptomsMap.entrySet()) {
                pw.println(m.getKey() + "=" + m.getValue());
            }
            pw.flush();
            pw.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Problème dans la création ou dans l'écriture du fichier de sortie");
        }
    }

    public static Map<String, Integer> mapFromList(List<String> listFull) {
        Map<String, Integer> new_map = new HashMap<>();
        for (String symptom : listFull) {
            new_map.merge(symptom, 1, Integer::sum);
        }
        return new_map;
    }

    public static void main(String[] args) {
        ISymptomReader listSymptom = new ReadSymptomDataFromFile("symptoms.txt");

        Map<String, Integer> mapSymptoms = mapFromList(listSymptom.GetSymptoms());

        writeFileOut(mapSymptoms, "result.out");
    }

}
