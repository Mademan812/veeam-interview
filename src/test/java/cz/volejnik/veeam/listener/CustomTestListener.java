package cz.volejnik.veeam.listener;

import cz.volejnik.veeam.reporting.CustomLogManager;
import cz.volejnik.veeam.reporting.CustomLogger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class CustomTestListener implements ITestListener {
    private static final CustomLogger LOGGER = CustomLogManager.getLogger(CustomTestListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOGGER.startSuite(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.endSuite(context.getName(),  context.getFailedTests().size(), context.getFailedTests().size(), context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.startTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.success(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if(result.getThrowable() instanceof AssertionError) {
            LOGGER.fail(result.getMethod().getMethodName(), result.getThrowable().getLocalizedMessage());
        } else {
            LOGGER.fail(result.getMethod().getMethodName(), result.getThrowable().getLocalizedMessage(), Arrays.toString(result.getThrowable().getStackTrace()));
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.skip(result.getMethod().getMethodName(), Arrays.toString(result.getSkipCausedBy().toArray()));
    }
}