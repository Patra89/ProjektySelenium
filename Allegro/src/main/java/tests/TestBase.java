package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentFactory;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

    private WebDriver driver;
    private ExtentTest test;

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver setDriver(String browser){
        switch (browser) {
            case "chrome":
                //System.setProperty("webdriver.chrome.driver", "C:\\Users\\patry\\Documents\\DRIVERS\\chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", "src\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                //System.setProperty("webdriver.gecko.driver", "C:\\Users\\patry\\Documents\\DRIVERS\\geckodriver.exe");
                System.setProperty("webdriver.gecko.driver", "src\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                //System.setProperty("webdriver.gecko.driver", "C:\\Users\\patry\\Documents\\DRIVERS\\geckodriver.exe");
                System.setProperty("webdriver.gecko.driver", "src\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }

        return driver;
    }

    @Parameters({"browser", "appUrl"})
    @BeforeClass(alwaysRun = true)
    public void initializeTestBaseSetup(String browser, String url) {
        try {
            driver = setDriver(browser);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Error....." + e.getStackTrace());
        }
    }

/*    @BeforeTest
    public void beforeTest(){
       // ExtentFactory.startReport();
    }

    @AfterTest
    public void afterTest(){
        //ExtentFactory.finishReport();
    }*/

    @AfterClass(alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void startOfMethod(Method method){
        test = ExtentFactory.startTest(method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws IOException {

        if(result.getStatus() == ITestResult.FAILURE) {
            long filePath = System.currentTimeMillis();
            MediaEntityModelProvider provider = MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, String.valueOf(filePath))).build();
            String message ="";
            if (result.getThrowable() != null) {
                Throwable testResultThrowable = result.getThrowable();
                message = testResultThrowable.getMessage() != null ? testResultThrowable.getMessage() : testResultThrowable.getCause().getMessage();
            }
            test.log(Status.FAIL, message, provider);
        }else if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, "Test passed");
        }else if(result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, "Test skipped");
        }
    }
}
