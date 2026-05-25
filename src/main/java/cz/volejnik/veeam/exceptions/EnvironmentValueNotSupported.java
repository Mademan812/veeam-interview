package cz.volejnik.veeam.exceptions;

/**
 * Custom runtime exception to be thrown when the value found in "env" system property is not a supported environment value
 * as defined in {@link cz.volejnik.veeam.config.Environment}.
 */
public class EnvironmentValueNotSupported extends RuntimeException {
    public EnvironmentValueNotSupported(String environmentValue) {
        super("Environment with value '" + environmentValue + "' is not supported. Supported");
    }
}
