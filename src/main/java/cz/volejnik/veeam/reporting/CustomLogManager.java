package cz.volejnik.veeam.reporting;

import cz.volejnik.veeam.config.ApplicationProperties;
import cz.volejnik.veeam.config.PropertiesBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomLogManager {
    public static CustomLogger getLogger(Class<?> clazz) {
        ApplicationProperties properties = PropertiesBuilder.buildRunConfig();
        return new CustomLogger(clazz.getSimpleName(), properties.getElasticUrl(), properties.getElasticUsername(), properties.getElasticPassword());
    }
}
