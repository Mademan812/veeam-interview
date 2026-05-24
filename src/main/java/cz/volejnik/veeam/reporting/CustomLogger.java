package cz.volejnik.veeam.reporting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {
    private final Logger logger;
    private final ElasticReporter elasticReporter;
    private final ExtentReport extentReport;

    public CustomLogger(String className, String elasticUrl, String elasticUsername, String elasticPassword) {
        logger = LogManager.getLogger(className);
        elasticReporter = new ElasticReporter(elasticUrl,  elasticUsername, elasticPassword);
        extentReport = new ExtentReport();
    }

    public void startSuite(String name) {
        logger.info(String.format("Starting test suite %s", name));
        extentReport.startSuite(name);
    }

    public void endSuite(String name, int successes, int failures, int skips) {
        logger.info(String.format("Ending test suite %s (%d successes, %d failures, %d skips", name,  successes, failures, skips));
        extentReport.endSuite(name, successes, failures, skips);
    }

    public void startTest(String name) {
        logger.info(String.format("Starting test %s", name));
        elasticReporter.startTest(name);
        extentReport.startTest(name);
    }

    public void success(String name) {
        logger.info(String.format("Test %s succeeded", name));
        elasticReporter.success(name);
        extentReport.success(name);
    }

    public void fail(String name, String reason) {
        logger.info(String.format("Test %s failed (reason: %s)", name, reason));
        elasticReporter.failure(name, reason);
        extentReport.failure(name, reason);
    }

    public void fail(String name, String reason, String stacktrace) {
        logger.info(String.format("Test %s failed (reason: %s). /n%s", name, reason, stacktrace));
        elasticReporter.failure(name, reason, stacktrace);
        extentReport.failure(name, reason, stacktrace);
    }

    public void skip(String name, String cause) {
        logger.info(String.format("Test %s skipped (reason: %s)", name, cause));
        elasticReporter.skip(name, cause);
        extentReport.skip(name, cause);
    }

    public void debug(String message) {
        logger.debug(message);
        elasticReporter.log(LogLevel.DEBUG, message);
    }
    public void info(String message) {
        logger.info(message);
        elasticReporter.log(LogLevel.INFO, message);
        extentReport.log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        logger.warn(message);
        elasticReporter.log(LogLevel.WARNING, message);
        extentReport.log(LogLevel.WARNING, message);
    }

    public void warn(String message,  Throwable throwable) {
        logger.warn(message, throwable);
        elasticReporter.log(LogLevel.WARNING, message, throwable);
        extentReport.log(LogLevel.WARNING, message, throwable);
    }

    public void error(String message) {
        logger.error(message);
        elasticReporter.log(LogLevel.ERROR, message);
        extentReport.log(LogLevel.ERROR, message);
    }

    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
        elasticReporter.log(LogLevel.ERROR, message, throwable);
        extentReport.log(LogLevel.ERROR, message, throwable);
    }
}
