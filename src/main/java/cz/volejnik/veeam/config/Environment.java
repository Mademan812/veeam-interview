package cz.volejnik.veeam.config;

import cz.volejnik.veeam.exceptions.EnvironmentValueNotSupported;
import cz.volejnik.veeam.exceptions.EnvironmentVariableNotProvided;
import lombok.AllArgsConstructor;

import java.io.File;

/**
 * Represents the specific environment. It exists to enforce a set of supported property variables.
 */
@AllArgsConstructor
public enum Environment {
    DEFAULT("default"),
    OTHER("other");

    private static final String FOLDER_PREFIX = "env-";
    private final String folder;

    /**
     * Enhanced getter method.
     *
     * @return String of "env-default/" or "env-other/" based on the exact enum value
     */
    public String getFolder() {
        return FOLDER_PREFIX + folder + File.separator;
    }

    /**
     * Reads the system property of "env" and translates it into Environment enum value.
     *
     * @return {@link Environment} enum value
     */
    static Environment load() {
        String env = System.getProperty("env", null);
        if(env == null) {
            throw new EnvironmentVariableNotProvided();
        }
        for(Environment e : Environment.values()) {
            if (e.name().equalsIgnoreCase(env)) {
                return e;
            }
        }
        throw new EnvironmentValueNotSupported(env);
    }
}
