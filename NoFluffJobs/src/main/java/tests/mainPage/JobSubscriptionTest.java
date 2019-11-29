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
import utils.DataProviders;
import utils.Wait;

import static org.assertj.core.api.Assertions.assertThat;

public class JobSubscriptionTest extends BaseTest {

    private MainPage page;
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) {
        driver = getDriver();
        context.setAttribute("driver", driver);
        page = new MainPage(driver);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify subsciption is only possible with valid email address and with consent check box checked")
    @Story("User should be able to subscribe to job offers")
    @Test(priority = 1, dataProvider = "validEmails", dataProviderClass = DataProviders.class)
    public void subscribeUsingValidEmailWithConsentChecked(String emailAddress){
        page.openSubscribeDialog();
         assertThat(page.isSubscribeDialogDisplayed())
                .withFailMessage("Subscribe dialog is not displayed").isTrue();
        page.inputEmailAddress(emailAddress);
        page.checkConsentCheckBox();
        page.confirmSubscription();
        assertThat(page.isSubscriptionSuccessful())
                .withFailMessage("Subscription is not successful with valid email address: "+emailAddress).isTrue();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify subsciption is not possible with valid email and consent check box unchecked")
    @Story("User should not be able to subscribe to job offers without checking consent check box")
    @Test(priority = 2)
    public void subscribeUsingValidEmailWithConsentUnchecked(){
        page.openSubscribeDialog();
        assertThat(page.isSubscribeDialogDisplayed())
                .withFailMessage("Subscribe dialog is not displayed").isTrue();
        page.inputEmailAddress("email@exmaple.com");
        page.confirmSubscription();
        assertThat(page.isCheckboxErrorMessageDisplayed())
                .withFailMessage("Checkbox error message is not displayed").isTrue();
        assertThat(page.isSubscriptionSuccessful())
                .withFailMessage("Subscription is successful without checking permission checkbox").isFalse();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify subsciption is not possible with invalid email and consent check box checked")
    @Story("User should be able to subscribe to job offers only when using valid email address")
    @Test(priority = 3, dataProvider = "invalidEmails", dataProviderClass = DataProviders.class)
    public void subscribeUsingInvalidEmailWithConsentChecked(String emailAddress){
        page.openSubscribeDialog();
        assertThat(page.isSubscribeDialogDisplayed())
                .withFailMessage("Subscribe dialog is not displayed").isTrue();
        page.inputEmailAddress(emailAddress);
        page.checkConsentCheckBox();
        page.confirmSubscription();
        assertThat(page.isEmailErrorMessageDisplayed())
                .withFailMessage("Email error message is not displayed for email address: "+emailAddress).isTrue();
        assertThat(page.isSubscriptionSuccessful())
                .withFailMessage("Subscription is successful with invalid email address: "+emailAddress).isFalse();
    }

}
