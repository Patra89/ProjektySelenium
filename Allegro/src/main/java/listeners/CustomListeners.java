package listeners;

import org.testng.*;

import static utils.ExtentFactory.finishReport;
import static utils.ExtentFactory.startReport;

public class CustomListeners implements ITestListener, ISuiteListener, IInvokedMethodListener {

    @Override
    public void onStart(ISuite iSuite) {
    }

    @Override
    public void onFinish(ISuite iSuite) {
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //startTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //ExtentFactory.getTest().log(LogStatus.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //ExtentFactory.getTest().log(LogStatus.SKIP, ExtentFactory.getTest().getTest().getName() + "test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        startReport(iTestContext.getCurrentXmlTest().getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        finishReport();
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
    }


}
