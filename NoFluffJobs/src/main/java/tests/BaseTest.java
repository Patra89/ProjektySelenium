package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver setDriver(String browser){
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.gecko.driver", "src\\drivers\\geckodriver.exe");
                FirefoxOptions opt = new FirefoxOptions();
                opt.setHeadless(true);
                driver = new FirefoxDriver(opt);
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

    @AfterClass(alwaysRun = true)
    public void closeDriver(){
        if(driver!=null) {
            driver.quit();
        }
    }

}
