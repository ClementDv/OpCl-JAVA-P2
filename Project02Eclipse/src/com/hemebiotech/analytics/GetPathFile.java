package com.hemebiotech.analytics;

import java.io.IOException;

public interface GetPathFile {
    /**
     * Get the path of Symptoms file and Result file, if properties doesn't exist it will attribute a default value
     **/
    String getSymptomsFile();

    String getResultFile();
}
