package cz.volejnik.veeam.exceptions;

/**
 * Custom runtime exception to be thrown when no "env" system property is provided to the test.
 */
public class EnvironmentVariableNotProvided extends RuntimeException {
    public EnvironmentVariableNotProvided() {
        super("Environment variable not provided - you must provide -Denv runtime variable.");
    }
}
