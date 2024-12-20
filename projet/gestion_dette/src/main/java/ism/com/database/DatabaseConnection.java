package ism.com.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Charger les propriétés depuis le fichier db.properties
                Properties properties = new Properties();
                InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");
                properties.load(input);

                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                String driver = properties.getProperty("db.driver");

                // Charger le driver JDBC
                Class.forName(driver);

                // Établir la connexion
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connexion réussie à la base de données !");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur de connexion à la base de données");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
