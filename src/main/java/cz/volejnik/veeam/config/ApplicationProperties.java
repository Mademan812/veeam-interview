package cz.volejnik.veeam.config;

import cz.volejnik.veeam.exceptions.PropertiesFailedToLoad;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to hold environment-specific variables.
 */
@Getter
public class ApplicationProperties {
    private static final String FILE = "application.properties";
    private static final Properties properties = new Properties();

    private static final String SUT_URL_PATH = "sut.url";
    private static final String ELASTIC_URL_PATH = "elastic.url";
    private static final String ELASTIC_USERNAME_PATH = "elastic.username";
    private static final String ELASTIC_PASSWORD_PATH = "elastic.password";

    private String sutUrl;
    private String elasticUrl;
    private String elasticUsername;
    private String elasticPassword;

    /**
     * Loads values into this instance based on the provided environment.
     *
     * @param environment {@link Environment}
     */
    void load(Environment environment) {
        String path = environment.getFolder() + FILE;

        try (InputStream inputStream = ApplicationProperties.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new PropertiesFailedToLoad(path, "File not found.");
            }
            properties.load(inputStream);
            loadValues();
        } catch (IOException e) {
            throw new PropertiesFailedToLoad(path, e);
        }
    }

    private void loadValues() {
        sutUrl = properties.getProperty(SUT_URL_PATH);
        elasticUrl = properties.getProperty(ELASTIC_URL_PATH);
        elasticUsername = properties.getProperty(ELASTIC_USERNAME_PATH);
        elasticPassword = properties.getProperty(ELASTIC_PASSWORD_PATH);
    }
}