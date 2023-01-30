package com.example.hwmaven.service.impl;

import com.example.hwmaven.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FilesService {

    @Value("${path.to.date.file}")
    private String dataFilePath;

    @Override
    public boolean saveToFile(String json, String dataFileName) {
        try {
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public String readFromFile(String dataFileName){
        try {
            return Files.readString(Path.of(dataFilePath,dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean cleanDataFile(){
        Path path = Path.of(dataFilePath);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public File getDataFile(String dataFileName) {
        return new File(dataFilePath + "/" + dataFileName);
    }
}
