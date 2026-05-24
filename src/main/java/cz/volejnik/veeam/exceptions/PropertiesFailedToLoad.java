package cz.volejnik.veeam.exceptions;

public class PropertiesFailedToLoad extends RuntimeException {
    public PropertiesFailedToLoad(String propertiesPath, String message) {
        super("Failed to load properties file: " + propertiesPath + ": " + message);
    }

    public PropertiesFailedToLoad(String propertiesPath, Throwable cause) {
        super("Failed to load properties file: " + propertiesPath, cause);
    }
}
