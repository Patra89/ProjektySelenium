package tests.mainPage;

import pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.TestBase;

public class MojeAllegroTest extends TestBase {

    private WebDriver driver;
    private MainPage page;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new MainPage(driver);
        page.getRidOfDialogBoxIfIsDisplayed();
    }

    @Test
    public void testMojeAllegroOptionsPopUp(){
        page.mojeAllegroButton.click();
        Assert.assertTrue(page.mojeAllegroPopUp.isDisplayed(), "Moje Allegro pop up is not visible when it should be");
        page.mojeAllegroButton.click();
        Assert.assertFalse(page.mojeAllegroPopUp.isDisplayed(),"Moje Allegro pop up is visible when it shouldn't be");
    }

}
