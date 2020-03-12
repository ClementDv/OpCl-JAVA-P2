package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class AnalyticsCounter {

    public static void main(String[] args) {
        ISymptomReader listSymptom = new ReadSymptomDataFromFile("symptoms.txt");
        List<String> Symptom = listSymptom.GetSymptoms();

        System.out.println(Symptom);
    }

}
