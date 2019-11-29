package tests.jobDetailsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.JobDetailsPage;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CompareFunctionTest extends BaseTest {

    private JobDetailsPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new JobDetailsPage(driver);
        page.navigateToJobPostingSubpage();
        assertThat(driver.getCurrentUrl()).contains("/job/");
        page.removeCookiesBarIfVisible();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify postings added to the compare list are shown to the user")
    @Story("User should be able to add postings to the the compare list")
    @Test(priority = 1)
    public void addPosting() {
        page.addPostingToCompareList();
        assertThat(page.isCompareListShown()).withFailMessage("Compare list is not displayed").isTrue();
        assertThat(page.howManyItemsInCompareList())
                .withFailMessage("Compare list contains %n items", page.howManyItemsInCompareList()).isEqualTo(1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify postings can be removed from the compare list")
    @Story("User should be able to remove postings from the compare list")
    @Test(priority = 2, dependsOnMethods = "addPosting")
    public void removePosting(){
        page.removePostingFromCompareList();
        assertThat(page.isCompareListShown()).withFailMessage("Compare list is not displayed").isFalse();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify no more than 3 postings can be added to the compare list")
    @Story("User should not be able to add more than 3 postings to the compare list")
    @Test(priority = 3)
    public void addMoreThan3Postings(){
        page.addMultiplePostingsToCompareList(4);
        assertThat(page.isCompareListShown()).withFailMessage("Compare list is not displayed").isTrue();
        assertThat(page.howManyItemsInCompareList())
                .withFailMessage("Compare list contains %n items", page.howManyItemsInCompareList()).isEqualTo(3);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the compare list can be cleared")
    @Story("User should not be able to clear the compare list")
    @Test(priority = 4)
    public void clearCompareList(){
        assertThat(page.isCompareListShown()).withFailMessage("Compare list is not displayed").isTrue();
        assertThat(page.howManyItemsInCompareList())
                .withFailMessage("Compare list contains %n items", page.howManyItemsInCompareList()).isGreaterThan(0);
        page.removeAllPostingsFromCompareList();
        assertThat(page.isCompareListShown()).withFailMessage("Compare list is not displayed").isFalse();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify it is possible to compare postings in a form of a table")
    @Story("User should be able to view comparision table of job postings")
    @Test(priority = 5)
    public void compareJobs(){
        int howManyPostings = 3;
        page.addMultiplePostingsToCompareList(howManyPostings);
        page.compareJobPostings();
        assertThat(page.isComparisionTableShown()).withFailMessage("Comparision table is not displayed").isTrue();
        assertThat(page.howManyColumnsInComparisionTable())
                .withFailMessage("Comparision table contains %n items instead of %n",
                        page.howManyColumnsInComparisionTable(), howManyPostings+1)
                .isEqualTo(howManyPostings+1);
    }

}
