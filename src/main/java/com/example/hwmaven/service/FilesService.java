package com.example.hwmaven.service;

public interface FilesService {

    boolean saveToFile(String json, String dataFileName);

    String readFromFile(String dataFileName);

    boolean cleanDataFile();
}
