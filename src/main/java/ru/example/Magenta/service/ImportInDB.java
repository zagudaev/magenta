package ru.example.Magenta.service;

import org.springframework.http.ResponseEntity;

import java.io.File;

public interface ImportInDB {
    ResponseEntity<Object> importXLMinDB(File file);
}
