package tests.productDetailsPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ProductDetailsPage;
import tests.TestBase;

public class BottomMenuTest extends TestBase {

    private WebDriver driver;
    private ProductDetailsPage page;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new ProductDetailsPage(driver);
        page.getRidOfDialogBoxIfIsDisplayed();
        page.navigateToRandomProductDetailsPage();
    }

    @Test
    public void bottomMenuTest(){
        page.scrollToElement(page.bottomMenuContainer);
        SoftAssert softAssert = new SoftAssert();
        for(int i = 0; i< page.bottomMenuElements.size(); i++){
            page.clickOnElementWithJavaScriptExecutor(page.productAmountAddButton);
            page.bottomMenuElements.get(i).click();
            softAssert.assertTrue(page.bottomMenuDialogBoxesTitles.get(i).isDisplayed(),
                    "Dialog box is not shown");
            softAssert.assertEquals(page.bottomMenuElements.get(i).getText(),
                    page.bottomMenuDialogBoxesTitles.get(i).getText(),
                    "Dialog box is contains different title");
            page.clickOutsideOfDialogBox(page.bottomMenuDialogBoxesTitles.get(i),800);
        }
        softAssert.assertAll("Dialog box is not opening or contains different title");
    }


}
