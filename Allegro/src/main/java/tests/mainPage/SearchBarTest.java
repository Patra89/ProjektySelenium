package tests.mainPage;

import data.DataProviders;
import pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.TestBase;

public class SearchBarTest extends TestBase {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new MainPage(driver);
        page.getRidOfDialogBoxIfIsDisplayed();
    }

    @Test(dataProvider = "searchData", dataProviderClass= DataProviders.class)
    public void testSearchBar(String phrase, ITestContext context){
        page.navigateToMainPageIfNecessary(context.getCurrentXmlTest().getLocalParameters().get("appUrl"));
        Assert.assertTrue(page.searchButton.isDisplayed(), "Search Button is not displayed");
        Assert.assertTrue(page.searchInput.isDisplayed(), "Search Box is not displayed");
        page.searchInput.clear();
        page.searchInput.sendKeys(phrase);
        page.searchButton.click();
        Assert.assertTrue(driver.getTitle().contains(phrase), "Search result website does not contain searched phrase: "+phrase);
    }


    @Test(dataProvider = "searchCategories", dataProviderClass = DataProviders.class)
    public void testSearchByCategory(String category, String categoryInTitle, String categoryInUrl, ITestContext context){
        String phrase = "a";
        page.navigateToMainPageIfNecessary(context.getCurrentXmlTest().getLocalParameters().get("appUrl"));
        page.searchInput.clear();
        page.searchInput.sendKeys(phrase);
        Select select = new Select(page.categorySelect);
        select.selectByVisibleText(category);

        page.searchButton.click();

        Assert.assertTrue(driver.getCurrentUrl().contains(categoryInUrl),
                "Search result - wrong url: "+ driver.getCurrentUrl() +" does not contain phrase: " + categoryInUrl);
        Assert.assertTrue(driver.getTitle().contains(categoryInTitle),
                "Search result doesn't have the category title. Category: " + categoryInTitle + " Title: " + driver.getTitle());
    }

}
