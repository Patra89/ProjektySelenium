package tests.jobDetailsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.JobDetailsPage;
import tests.BaseTest;
import utils.DataProviders;
import utils.Wait;

import static org.assertj.core.api.Assertions.assertThat;

public class ShareTest extends BaseTest {

    private JobDetailsPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new JobDetailsPage(driver);
        page.navigateToJobPostingSubpage();
        assertThat(driver.getCurrentUrl()).contains("/job/");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify share functionality works")
    @Story("User should be able to share job posting on social media and by email")
    @Test(dataProvider = "shareButtons", dataProviderClass = DataProviders.class, priority = 1)
    public void shareButtonsTest(By buttonPath, boolean shouldNewWindowBeOpened, Object newWindowOrDialog){
        WebElement button = driver.findElement(buttonPath);
        page.openShareOptionsPopUp();
        if(shouldNewWindowBeOpened){
            String originalHandle = driver.getWindowHandle();
            button.click();
            String urlOfNewWindow = "";
            Wait wait = new Wait(driver);
            assertThat(wait.forWindowsToOpen(2)).isTrue();

                for(String winHandle : driver.getWindowHandles()){
                    if(!winHandle.equals(originalHandle)) {
                        driver.switchTo().window(winHandle);
                        wait.forPageUrlToNotBeNull();
                        urlOfNewWindow = driver.getCurrentUrl();
                        driver.close();
                        driver.switchTo().window(originalHandle);
                        break;
                    }
                }
            assertThat(urlOfNewWindow).withFailMessage("Wrong url was opened: %s",urlOfNewWindow).contains(newWindowOrDialog.toString());
        }else{
            button.click();
            assertThat(page.isElementDisplayed(driver.findElement((By)newWindowOrDialog)))
                    .withFailMessage("Dialog box is not displayed").isTrue();
            page.closeShareByEmailDialog();
        }
    }

}
