package com.example.hwmaven.service;

public interface FilesService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
}
