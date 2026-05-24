package cz.volejnik.veeam.config;

import cz.volejnik.veeam.exceptions.EnvironmentValueNotSupported;
import cz.volejnik.veeam.exceptions.EnvironmentVariableNotProvided;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public enum Environment {
    DEFAULT("default"),
    OTHER("other");

    private static final String FOLDER_PREFIX = "env-";
    private final String folder;

    public String getFolder() {
        return FOLDER_PREFIX + folder + File.separator;
    }

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
