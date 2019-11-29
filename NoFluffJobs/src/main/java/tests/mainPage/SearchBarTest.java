package tests.mainPage;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import tests.BaseTest;
import utils.DataProviders;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchBarTest extends BaseTest {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) {
        driver = getDriver();
        context.setAttribute("driver", driver);
        page = new MainPage(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search function works with complex phrases")
    @Story("User should be able to search through job postings using complex phrases")
    @Test(dataProvider = "searchData", dataProviderClass = DataProviders.class)
    public void searchPhrases(String phrase){
        String url = Reporter.getCurrentTestResult()
                .getTestContext().getCurrentXmlTest().getParameter("appUrl");
        page.goBackToMainPage(url);
        page.searchPhrase(phrase);
        assertThat(page.areSearchResultsVisible()).withFailMessage("Search results are not visible").isTrue();
        assertThat(page.loadingImageIsNotVisible()).withFailMessage("Loading image is still visible").isTrue();
    }

}
