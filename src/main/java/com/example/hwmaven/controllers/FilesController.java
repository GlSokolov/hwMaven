package com.example.hwmaven.controllers;

import com.example.hwmaven.service.FilesService;
import com.example.hwmaven.service.IngredientService;
import com.example.hwmaven.service.impl.IngredientServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final FilesService filesService;

    @Value("${name.of.ingredients.date.file}")
    private String ingredientFileName;

    @Value("${name.of.recipes.date.file}")
    private String recipeFileName;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/export/recipes")
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = filesService.getDataFile(recipeFileName);

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_OCTET_STREAM).
                    contentLength(file.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/export/ingredients")
    public ResponseEntity<InputStreamResource> downloadIngredientsFile() throws FileNotFoundException {
        File file = filesService.getDataFile(ingredientFileName);

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_OCTET_STREAM).
                    contentLength(file.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ingredients.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        filesService.cleanDataFile();
        File recipeFile = filesService.getDataFile(recipeFileName);

//        Как делается вручную без библиотеки:

//        try ( BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
//                FileOutputStream fos = new FileOutputStream(recipeFile);
//                BufferedOutputStream bos = new BufferedOutputStream(fos);){
//            byte[] buffer = new byte[1024];
//            while (bis.read(buffer) > 0) {
//                bos.write(buffer);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try (FileOutputStream fos = new FileOutputStream(recipeFile);) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        filesService.cleanDataFile();
        File ingredientFile = filesService.getDataFile(ingredientFileName);

        try (FileOutputStream fos = new FileOutputStream(ingredientFile);) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}