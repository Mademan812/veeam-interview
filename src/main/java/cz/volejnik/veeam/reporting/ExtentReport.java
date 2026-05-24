package cz.volejnik.veeam.reporting;

public class ExtentReport {
    public void startSuite(String name) {
        //implementation omitted for timeboxing reasons
    }

    public void endSuite(String name, int successes, int failures, int skips) {
        //implementation omitted for timeboxing reasons
    }

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
