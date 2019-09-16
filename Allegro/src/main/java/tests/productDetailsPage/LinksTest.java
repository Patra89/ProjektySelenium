package tests.productDetailsPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import tests.TestBase;
import utils.BrokenLinksFinder;

public class LinksTest extends TestBase {

    private ProductDetailsPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new ProductDetailsPage(driver);
        driver.navigate().to("https://allegro.pl/kategoria/telefony-i-akcesoria");
        page.getRidOfDialogBoxIfIsDisplayed();
        page.navigateToRandomProductDetailsPage();
    }

    @Test
    public void findAllBrokenLinks() {
        new BrokenLinksFinder().findAllBrokenLinks(driver);
    }

}