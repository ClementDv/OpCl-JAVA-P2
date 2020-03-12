package com.hemebiotech.analytics;

import java.util.*;

public class AnalyticsCounter {

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
    }

}
