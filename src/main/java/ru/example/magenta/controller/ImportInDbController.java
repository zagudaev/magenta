package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.example.magenta.service.ImportService;

import java.io.File;

@RestController
@AllArgsConstructor
@RequestMapping("/import")
public class ImportInDbController {
    private final ImportService importService;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addXLMinDB(File file) {
        importService.importFile(file);
    }
}
