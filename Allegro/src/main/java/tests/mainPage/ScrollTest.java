package tests.mainPage;

import pages.MainPage;
import tests.TestBase;
import utils.Wait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScrollTest extends TestBase {

    private WebDriver driver;
    private MainPage page;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new MainPage(driver);
        Assert.assertTrue(page.verifyPageTitle(), "Home page title doesn't match");
        page.getRidOfDialogBoxIfIsDisplayed();
    }

    @Test
    public void scrollButtonTest(){
        Wait wait = new Wait(driver);
        page.scrollToBottom();
        wait.forElementToBeClickable(page.scrollToTopButton);
        page.scrollToTopButton.click();
        wait.forElementToReturnJSValue("return window.pageYOffset == 0");
        Assert.assertTrue(page.isItScrolledToTop(), "Page is not scolled to the top");
    }
}
