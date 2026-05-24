package cz.volejnik.veeam.exceptions;

public class EnvironmentValueNotSupported extends RuntimeException {
    public EnvironmentValueNotSupported(String environmentValue) {
        super("Environment with value '" + environmentValue + "' is not supported. Supported");
    }
}
