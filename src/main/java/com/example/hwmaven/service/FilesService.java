package com.example.hwmaven.service;

import java.io.File;

public interface FilesService {

    boolean saveToFile(String json, String dataFileName);

    String readFromFile(String dataFileName);

    boolean cleanDataFile();

    File getDataFile(String dataFileName);
}
