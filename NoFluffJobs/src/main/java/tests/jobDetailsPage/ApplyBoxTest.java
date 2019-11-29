package tests.jobDetailsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.JobDetailsPage;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.*;

public class ApplyBoxTest extends BaseTest {

    private JobDetailsPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        driver = getDriver();
        page = new JobDetailsPage(driver);
        page.navigateToJobPostingSubpage();
        assertThat(driver.getCurrentUrl()).contains("/job/");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify apply box is visible when scrolling up and down the page")
    @Story("User should be able to see apply box at all times")
    @Test(priority = 1)
    public void checkIfApplyBoxScrollsUpAndDownWithPage(){
        page.scrollDown();
        assertThat(page.isApplyBoxWithinViewport()).withFailMessage("Apply box not visible when page scrolled down").isTrue();
        page.scrollUp();
        assertThat(page.isApplyBoxWithinViewport()).withFailMessage("Apply box is not visible when page scrolled up").isTrue();
    }
}
