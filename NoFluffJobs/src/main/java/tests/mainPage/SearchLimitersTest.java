package tests.mainPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import tests.BaseTest;


public class SearchLimitersTest extends BaseTest {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) {
        driver = getDriver();
        context.setAttribute("driver", driver);
        page = new MainPage(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search limiters cause list to reload results")
    @Story("User should be able to limit search results by technology")
    @Test(priority = 1)
    public void limitResultsByTechnology(){
        page.limitSearchResults(page.getTechnologyButton(),1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify resetting limiters cause list to reload results")
    @Story("User should be able to reset technology limiters")
    @Test(priority = 2)
    public void resetTechnologyLimiter(){
        page.resetSetting(page.getTechnologyButton());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search limiters cause list to reload results")
    @Story("User should be able to limit search results to by location")
    @Test(priority = 3)
    public void limitResultsByLocationy(){
        page.limitSearchResults(page.getLocationButton(),1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify resetting limiters cause list to reload results")
    @Story("User should be able to reset location limiters")
    @Test(priority = 4)
    public void resetLocationLimiter(){
        page.resetSetting(page.getLocationButton());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search limiters cause list to reload results")
    @Story("User should be able to limit search results to by category")
    @Test(priority = 5)
    public void limitResultsByCategory(){
        page.limitSearchResults(page.getCategoryButton(),1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify choosing resetting limiters cause list to reload results")
    @Story("User should be able to reset category limiters")
    @Test(priority = 6)
    public void resetResultsLimiter(){
        page.resetSetting(page.getCategoryButton());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify choosing search limiters cause list to reload results")
    @Story("User should be able to limit search results to by additional details")
    @Test(priority = 7)
    public void limitResultsByMoreDetails(){
        page.limitSearchResults(page.getMoreButton(),2);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify resetting limiters cause list to reload results")
    @Story("User should be able to reset "+"more"+" limiters")
    @Test(priority = 8)
    public void resetMoreSectionLimiter(){
        page.resetSetting(page.getMoreButton());
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify choosing search limiters cause list to reload results")
    @Story("User should be able to limit search results by salary")
    @Test(priority = 9)
    public void limitResultsBySalary() {
        page.limitSearchResults(page.getSalaryButton(),3);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify resetting limiters cause list to reload results")
    @Story("User should be able to reset salary limiter")
    @Test(priority = 10)
    public void resetSalaryLimiter(){
        page.resetSetting(page.getSalaryButton());
    }



}
