package common;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;
import runners.TestRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public static void checkYamlFileForUniqueness(String filePath) {
        try (InputStream inputStream = TestRunner.class.getClassLoader().getResourceAsStream(filePath)) {
            Yaml yaml = new Yaml();
            Map<String, Map<String, String>> data = yaml.load(inputStream);

            Set<String> uniqueElements = new HashSet<>();
            for (Map.Entry<String, Map<String, String>> entry : data.entrySet()) {
                for (String key : entry.getValue().keySet()) {
                    if (!uniqueElements.add(key)) {
                        throw new RuntimeException("Duplicate element name found: " + key);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking uniqueness in YAML file: " + e.getMessage(), e);
        }
    }
}
