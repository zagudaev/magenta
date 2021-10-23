package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.magenta.service.ImportInDB;

import java.io.File;

@RestController
@AllArgsConstructor
@RequestMapping("/importInDB")
public class ImportInDBController {
    private final ImportInDB importInDB;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addXLMinDB(File file){
        importInDB.importXLMinDB(file);
    }
}
