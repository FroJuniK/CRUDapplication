package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperties(String filename) {
        Properties properties = new Properties();
        try (InputStream in = PropertyReader.class.getClassLoader().getResourceAsStream(filename)) {
            properties.load(in);
        } catch (IOException e) {
            System.err.println("Error!: FileNotFound!");
        }
        return properties;
    }
}
