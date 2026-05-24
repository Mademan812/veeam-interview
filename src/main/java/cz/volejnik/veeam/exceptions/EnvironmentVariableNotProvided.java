package cz.volejnik.veeam.exceptions;

public class EnvironmentVariableNotProvided extends RuntimeException {
    public EnvironmentVariableNotProvided() {
        super("Environment variable not provided - you must provide -Denv runtime variable.");
    }
}
