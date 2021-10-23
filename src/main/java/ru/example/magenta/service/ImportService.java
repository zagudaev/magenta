package ru.example.magenta.service;

import java.io.File;
/**
 *Service imports data into DB
 */
public interface ImportService {
    /**
     *The method takes an XML file and imports data from it into DB
     * @param file
     */
    void importFile(File file);
}
