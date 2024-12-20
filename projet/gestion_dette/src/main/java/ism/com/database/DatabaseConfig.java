package ism.com.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible de charger le fichier db.properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
