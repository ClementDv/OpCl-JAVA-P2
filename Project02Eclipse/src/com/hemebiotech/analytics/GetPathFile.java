package com.hemebiotech.analytics;

public interface GetPathFile {
    /**
     * Get path of Symptoms file and Result file, if properties doesn't exist it will attribute a default value
     **/
    String getSymptomsFile();

    String getResultFile();
}
