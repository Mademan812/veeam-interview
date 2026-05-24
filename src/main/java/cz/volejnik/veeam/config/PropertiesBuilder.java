package cz.volejnik.veeam.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertiesBuilder {
    private static final Environment environment = Environment.load();
    private static ApplicationProperties applicationProperties;

    public static ApplicationProperties buildRunConfig() {
        if(applicationProperties == null) {
            applicationProperties = new ApplicationProperties();
            applicationProperties.build(environment);
        }
        return applicationProperties;
    }
}
