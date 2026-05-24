package cz.volejnik.veeam.reporting;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ElasticReporter {
    private final String elasticUrl;
    private final String elasticUsername;
    private final String elasticPassword;

    public void startTest(String name) {
        //implementation omitted for timeboxing reasons
    }

    public void success(String name) {
        //implementation omitted for timeboxing reasons
    }

    public void failure(String name, String reason) {
        //implementation omitted for timeboxing reasons
    }

    public void failure(String name, String reason, String stacktrace) {
        //implementation omitted for timeboxing reasons
    }

    public void skip(String name, String cause) {
        //implementation omitted for timeboxing reasons
    }

    public void log(LogLevel logLevel, String message) {
        //implementation omitted for timeboxing reasons
    }

    public void log(LogLevel logLevel, String message, Throwable throwable) {
        //implementation omitted for timeboxing reasons
    }
}
