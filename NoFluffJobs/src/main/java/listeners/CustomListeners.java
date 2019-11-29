package listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import utils.ScreenshotUtil;

import java.io.IOException;

public class CustomListeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult){
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if(driver!=null) {
            try {
                Allure.addAttachment("Screenshot", ScreenshotUtil.takeScreenshot(driver));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

}
