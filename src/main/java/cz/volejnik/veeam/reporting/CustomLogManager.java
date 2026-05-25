package cz.volejnik.veeam.reporting;

import cz.volejnik.veeam.config.ApplicationProperties;
import cz.volejnik.veeam.config.PropertiesBuilder;
import lombok.experimental.UtilityClass;

/**
 * Utility class that constructs a {@link CustomLogger} instance based solely on the provided class name, fetching all
 * other necessary values from {@link ApplicationProperties}.
 */
@UtilityClass
public class CustomLogManager {
    /**
     * Construct and return an instance of {@link CustomLogger}.
     *
     * @param clazz {@link Class} class
     * @return {@link CustomLogger} instance
     */
    public static CustomLogger getLogger(Class<?> clazz) {
        ApplicationProperties properties = PropertiesBuilder.buildRunConfig();
        return new CustomLogger(clazz.getSimpleName(), properties.getElasticUrl(), properties.getElasticUsername(), properties.getElasticPassword());
    }
}
