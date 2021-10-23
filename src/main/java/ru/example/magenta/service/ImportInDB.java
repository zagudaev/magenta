package ru.example.magenta.service;

import java.io.File;
/**
 *Service imports data into DB
 */
public interface ImportInDB {
    /**
     *The method takes an XML file and imports data from it into DB
     * @param file
     * @return HTTP 200
     */
    void importXLMinDB(File file);
}
