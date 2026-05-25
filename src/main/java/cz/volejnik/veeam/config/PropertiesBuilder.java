package cz.volejnik.veeam.config;

import lombok.experimental.UtilityClass;

/**
 * Utility class that serves as a controlled access point to {@link ApplicationProperties} instances.
 * It automatically handles environment based on "env" system property through {@link Environment#load()}
 */
@UtilityClass
public class PropertiesBuilder {
    private static final Environment environment = Environment.load();
    private static ApplicationProperties applicationProperties;

    /**
     * Ensures only 1 instance of {@link ApplicationProperties} gets created during runtime for efficiency's sake.
     * If no instance exists yet, it creates a new instance of {@link ApplicationProperties} and ensures it is loaded
     * with values.
     *
     * @return {@link ApplicationProperties} instance loaded with values.
     */
    public static ApplicationProperties buildRunConfig() {
        if(applicationProperties == null) {
            applicationProperties = new ApplicationProperties();
            applicationProperties.load(environment);
        }
        return applicationProperties;
    }
}
