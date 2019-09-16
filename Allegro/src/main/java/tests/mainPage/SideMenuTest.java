package tests.mainPage;

import data.DataProviders;
import pages.MainPage;
import tests.TestBase;
import utils.Wait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SideMenuTest extends TestBase {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new MainPage(driver);
        page.getRidOfDialogBoxIfIsDisplayed();
    }

    @Test(dataProvider = "getSideMenuItems", dataProviderClass = DataProviders.class)
    public void testSideMenu(String menuPartialText, String sideBoxPartialText){
        page.moveCursorToElement(page.getElementByPartialText(menuPartialText));
        new Wait(driver).forElementToBeClickable(page.getElementByCssSelector(sideBoxPartialText));
        Assert.assertTrue(page.getElementByCssSelector(sideBoxPartialText).isDisplayed(), "The side box is not visible");
    }
}
