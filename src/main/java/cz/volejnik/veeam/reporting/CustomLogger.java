package cz.volejnik.veeam.reporting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Unified logging router that ensures that all logs are placed into their appropriate places and defines some unified
 * log message parts.
 */
public class CustomLogger {
    private final Logger logger;
    private final ElasticReporter elasticReporter;
    private final ExtentReport extentReport;

    /**
     * Sole constructor as we expect all logging layers to be mandatory.
     *
     * @param className caller class name for the Log4J logger
     * @param elasticUrl URL of ElasticSearch instance
     * @param elasticUsername valid ElasticSearch credential for logging into it
     * @param elasticPassword valid ElasticSearch password for logging into it
     */
    public CustomLogger(String className, String elasticUrl, String elasticUsername, String elasticPassword) {
        logger = LogManager.getLogger(className);
        elasticReporter = new ElasticReporter(elasticUrl,  elasticUsername, elasticPassword);
        extentReport = new ExtentReport();
    }

    /**
     * Log the start of a test suite into console output and ExtentReport.
     *
     * @param name String value
     */
    public void startSuite(String name) {
        logger.info(String.format("Starting test suite %s", name));
        extentReport.startSuite(name);
    }

    /**
     * Log the end of a test suite into console output and ExtentReport.
     *
     * @param name String value
     * @param successes int value
     * @param failures int value
     * @param skips int value
     */
    public void endSuite(String name, int successes, int failures, int skips) {
        logger.info(String.format("Ending test suite %s (%d successes, %d failures, %d skips", name,  successes, failures, skips));
        extentReport.endSuite(name, successes, failures, skips);
    }

    /**
     * Log the start of a test method into console output, ExtentReport, and ElasticSearch.
     *
     * @param name String method name
     */
    public void startTest(String name) {
        logger.info(String.format("Starting test %s", name));
        elasticReporter.startTest(name);
        extentReport.startTest(name);
    }

    /**
     * Log the success of a test method into console output, ExtentReport, and ElasticSearch.
     *
     * @param name String method name
     */
    public void success(String name) {
        logger.info(String.format("Test %s succeeded", name));
        elasticReporter.success(name);
        extentReport.success(name);
    }

    /**
     * Log the failure of a test method into console output, ExtentReport, and ElasticSearch with only String failure reason.
     *
     * @param name String method name
     * @param reason String reason for failure
     */
    public void fail(String name, String reason) {
        logger.info(String.format("Test %s failed (reason: %s)", name, reason));
        elasticReporter.failure(name, reason);
        extentReport.failure(name, reason);
    }

    /**
     * Log the failure of a test method into console output, ExtentReport, and ElasticSearch with failure reason and
     * exception stacktrace.
     *
     * @param name String method name
     * @param reason String reason for failure
     * @param stacktrace String stacktrace
     */
    public void fail(String name, String reason, String stacktrace) {
        logger.info(String.format("Test %s failed (reason: %s). /n%s", name, reason, stacktrace));
        elasticReporter.failure(name, reason, stacktrace);
        extentReport.failure(name, reason, stacktrace);
    }

    /**
     * Log the skip of a test method into console output, ExtentReport, and ElasticSearch with cause of skip.
     *
     * @param name String method name
     * @param cause String value
     */
    public void skip(String name, String cause) {
        logger.info(String.format("Test %s skipped (reason: %s)", name, cause));
        elasticReporter.skip(name, cause);
        extentReport.skip(name, cause);
    }

    /**
     * Log a debug message into console output and ElasticSearch.
     *
     * @param message String value
     */
    public void debug(String message) {
        logger.debug(message);
        elasticReporter.log(LogLevel.DEBUG, message);
    }

    /**
     * Log an info message into console output, ExtentReport, and ElasticSearch.
     *
     * @param message String value
     */
    public void info(String message) {
        logger.info(message);
        elasticReporter.log(LogLevel.INFO, message);
        extentReport.log(LogLevel.INFO, message);
    }

    /**
     * Log a warning message into console output, ExtentReport, and ElasticSearch (message only).
     *
     * @param message String value
     */
    public void warn(String message) {
        logger.warn(message);
        elasticReporter.log(LogLevel.WARNING, message);
        extentReport.log(LogLevel.WARNING, message);
    }

    /**
     * Log a warning message into console output, ExtentReport, and ElasticSearch (message and exception).
     *
     * @param message String value
     * @param throwable {@link Throwable} instance
     */
    public void warn(String message,  Throwable throwable) {
        logger.warn(message, throwable);
        elasticReporter.log(LogLevel.WARNING, message, throwable);
        extentReport.log(LogLevel.WARNING, message, throwable);
    }

    /**
     * Log an error message into console output, ExtentReport, and ElasticSearch (message only).
     *
     * @param message String value
     */
    public void error(String message) {
        logger.error(message);
        elasticReporter.log(LogLevel.ERROR, message);
        extentReport.log(LogLevel.ERROR, message);
    }

    /**
     * Log an error message into console output, ExtentReport, and ElasticSearch (message and exception).
     *
     * @param message String value
     * @param throwable {@link Throwable} instance
     */
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
        elasticReporter.log(LogLevel.ERROR, message, throwable);
        extentReport.log(LogLevel.ERROR, message, throwable);
    }
}
