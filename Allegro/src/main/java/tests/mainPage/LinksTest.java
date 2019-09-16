package tests.mainPage;

import pages.MainPage;
import tests.TestBase;
import utils.BrokenLinksFinder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LinksTest extends TestBase {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new MainPage(driver);
        page.getRidOfDialogBoxIfIsDisplayed();
    }

    @Test
    public void findAllBrokenLinks() {
        new BrokenLinksFinder().findAllBrokenLinks(driver);
    }

}
