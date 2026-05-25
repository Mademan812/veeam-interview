package cz.volejnik.veeam.exceptions;

/**
 * Custom runtime exception to be thrown in case the process of loading of {@link cz.volejnik.veeam.config.ApplicationProperties}
 * fails for whatever reason.
 */
public class PropertiesFailedToLoad extends RuntimeException {
    public PropertiesFailedToLoad(String propertiesPath, String message) {
        super("Failed to load properties file: " + propertiesPath + ": " + message);
    }

    public PropertiesFailedToLoad(String propertiesPath, Throwable cause) {
        super("Failed to load properties file: " + propertiesPath, cause);
    }
}
