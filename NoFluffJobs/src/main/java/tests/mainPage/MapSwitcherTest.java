package tests.mainPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class MapSwitcherTest extends BaseTest {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) {
        driver = getDriver();
        context.setAttribute("driver", driver);
        page = new MainPage(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify map shows on button click")
    @Story("User should be able to see map")
    @Test
    public void showMap(){
        page.switchMapSetting();
        assertThat(page.isMapVisible()).withFailMessage("Map is not visible").isTrue();
    }
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify map hides on button click")
    @Story("User should be able to hide map")
    @Test(dependsOnMethods = "showMap")
    public void hideMap(){
        page.switchMapSetting();
        assertThat(page.isMapVisible()).withFailMessage("Map is visible when is shouldn't be").isFalse();
    }
}
