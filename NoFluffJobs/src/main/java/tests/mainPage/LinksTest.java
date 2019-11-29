package tests.mainPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import tests.BaseTest;
import utils.BrokenLinksFinder;
import utils.DataProviders;

public class LinksTest extends BaseTest {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new MainPage(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify all links work")
    @Story("All links on the website should lead to existing website")
    @Test
    public void findAllBrokenLinks() {
        new BrokenLinksFinder().findAllBrokenLinks(driver);
    }

}