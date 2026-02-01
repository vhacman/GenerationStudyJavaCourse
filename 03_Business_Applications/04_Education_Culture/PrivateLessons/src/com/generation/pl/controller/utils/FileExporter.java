package com.generation.pl.controller.utils;

import java.io.File;
import com.generation.library.FileWriter;

/**
 * Simple utility for saving reports to organized folders.
 * Maintains the print/{format}/{entity}/ structure.
 */
public class FileExporter 
{
    private static final String BASE_DIR = "print";
    
    /**
     * Creates directory if it doesn't exist.
     */
    private static void ensureDirectoryExists(String dirPath)
    {
        File dir = new File(dirPath);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
    }
    
    /**
     * Saves content to file with automatic directory structure.
     * 
     * @param content Content to write
     * @param format "txt" or "html"
     * @param entity "teachers", "students", or "lessons"
     * @param filename Complete filename (e.g., "earnings_report_123.txt")
     */
    public static void save(String content, String format, String entity, String filename)
    {
        String dirPath = BASE_DIR + "/" + format + "/" + entity;
        ensureDirectoryExists(dirPath);        
        String fullPath = dirPath + "/" + filename;
        FileWriter.writeTo(fullPath, content);
    }
}
