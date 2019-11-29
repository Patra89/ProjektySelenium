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

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageChangeTest extends BaseTest {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) {
        driver = getDriver();
        context.setAttribute("driver", driver);
        page = new MainPage(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify language switch changes website language")
    @Story("User should be able to change website language")
    @Test(priority = 1, invocationCount = 2) //invocationCount = 2 because we want to switch back to default
    public void changeLanguage(){
        String defaultTitle = page.getWebsiteTitle();
        String languageTag = page.getWebsiteLanguage();
        page.switchLanguage();
        assertThat(languageTag).withFailMessage("Website language tag did not change").isNotEqualToIgnoringCase(page.getWebsiteLanguage());
        assertThat(defaultTitle).withFailMessage("Website title did not change").isNotEqualToIgnoringCase(page.getWebsiteTitle());
    }

}
