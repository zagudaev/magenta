package ru.example.Magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.Magenta.service.ImportInDBImpl;

import java.io.File;

@RestController
@AllArgsConstructor
@RequestMapping("/importInDB")
public class ImportInDBController {
    private final ImportInDBImpl importInDB;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    private ResponseEntity<Object> addXLMinDB(File file){
             return importInDB.importXLMinDB(file);
         }
}
