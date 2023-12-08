package common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Common {
    public static void cleanDirectory(String directoryPath) {
        try {
            File directory = new File(directoryPath);
            if (directory.exists()) {
                FileUtils.cleanDirectory(directory); // using Apache Commons IO
                System.out.println("directory cleaned successfully.");
            } else {
                System.out.println("Directory does not exist, no need to clean.");
            }
        } catch (IOException e) {
            System.err.println("Error cleaning the screenshot directory: " + e.getMessage());
        }
    }
}
