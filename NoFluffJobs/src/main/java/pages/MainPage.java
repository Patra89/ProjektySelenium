package pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.Wait;
import static org.assertj.core.api.Assertions.*;

public class MainPage extends BasePage{

    @FindBy(css = "div.filters>*:nth-child(1)")
    private WebElement technologyButton;

    @FindBy(css = "div.filters>*:nth-child(2)")
    private WebElement locationButton;

    @FindBy(css = "div.filters>*:nth-child(3)")
    private WebElement salaryButton;

    @FindBy(css = "div.filters>*:nth-child(4)")
    private WebElement categoryButton;

    @FindBy(css = "div.filters>*:nth-child(5)")
    private WebElement moreButton;

    @FindBy(css = "nfj-map-switcher")
    private WebElement mapSwitcher;

    @FindBy(css = ".widget-launcher")
    private WebElement chatButton;

    @FindBy(css = ".search-input")
    private WebElement searchBar;

    @FindBy(css = ".btn.btn-search")
    private WebElement searchButton;

    @FindBy(css = ".filters~button")
    private WebElement resetOptionsButton;

    @FindBy(css = ".mt-5.d-block.category.category--backend")
    private WebElement firstList;

    @FindBy(css = ".list-title>a:nth-child(1)")
    private WebElement listTitle;

    @FindBy(css = ".map")
    private WebElement map;

    @FindBy(css = ".popover-body")
    private WebElement searchLimitersDialogWindow;

    @FindBy(css = "nfj-search-results")
    private WebElement searchResultsList;

    @FindBy(css = "nfj-main-loader>div.wrapper")
    private WebElement loadingImage;

    @FindBy(xpath = "//section[1]/nfj-filter-control[1]/button")
    private WebElement firstButtonInJobOptionsDialog;

    @FindBy(xpath = "//nfj-filters-wrapper/div/div[3]/div[1]/button[1]")
    private WebElement resetAllDialogButton;

    @FindBy(xpath = "//nfj-filters-wrapper/div/div[3]/div[1]/button[2]")
    private WebElement applyDialogButton;

    @FindBy(xpath = "//div[@class='filters-more-section']/section[1]/div/div[1]")
    private WebElement firstCheckBoxInMoreDialog;

    @FindBy(xpath = "//ng5-slider/span[9]")
    private WebElement minimumSalaryTextElement;

    @FindBy(xpath = "//ng5-slider/span[5]")
    private WebElement minimumValueSliderElement;

    @FindBy(xpath = "//ng5-slider/span[6]")
    private WebElement maximumValueSliderElement;

    @FindBy(xpath = "html")
    private WebElement websiteHtmlTag;

    @FindBy(css = ".region-picker__region-selected")
    private WebElement languageSwitcher;

    @FindBy(xpath ="//span[contains(text(),\'Poland (PL)\')]")
    private WebElement polishLanguage;

    @FindBy(xpath ="//span[contains(text(),\'Hungary (HU)\')]")
    private WebElement hungarianLanguage;

    @FindBy(css = "nfj-subscriptions-header:nth-of-type(1)")
    private WebElement subscribeButton;

    @FindBy(css = "nfj-subscriptions-add-modal")
    private WebElement subscribeDialog;

    @FindBy(css = "#consentForSendingEmails")
    private WebElement subscribeConsentCheckBox;

    @FindBy(css = "#subscribeEmail")
    private WebElement subscribeEmailInputField;

    @FindBy(css = "button[type='submit']")
    private WebElement subscribeSubmitButton;

    @FindBy(css = "nfj-subscriptions-confirm-modal")
    private WebElement subscriptionSuccessfulDialog;

    @FindBy(css =".consent-agree~span")
    private WebElement subscribeCheckBoxErrorMessage;

    @FindBy(css =".w-100.d-flex~span")
    private WebElement subscribeEmailErrorMessage;

    @FindBy(css = ".close")
    private WebElement closeDialogButton;

    @FindBy(xpath = "//nfj-messages/div")
    private WebElement emailAlreadyExistsMessage;

    @FindBy(xpath = "//div/h2")
    private WebElement elementToGetRidOfDialog;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void switchMapSetting(){
        mapSwitcher.click();
    }

    public boolean isMapVisible(){
        return map.isDisplayed();
    }

    public void searchPhrase(String phrase){
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(phrase);
        searchButton.click();
    }

   public boolean areSearchResultsVisible(){
       try {
           return searchResultsList.isDisplayed();
       }catch(NoSuchElementException e){
           return false;
       }
   }

    public boolean loadingImageIsNotVisible(){
        Wait wait = new Wait(driver);
        try {
            if (loadingImage.isDisplayed()) {
                return wait.forElementToDisappear(loadingImage);
            } else {
                return true;
            }
        }catch(Exception e){
            return true;
        }
    }

    public void goBackToMainPage(String url){
        driver.get(url);
    }

    private boolean isDialogWindowVisible(){
        return searchLimitersDialogWindow.isDisplayed();
    }

    private String chooseSearchLimiter(int type){
        String text = "";
        if(type == 1) {
            text = firstButtonInJobOptionsDialog.getText();
            firstButtonInJobOptionsDialog.click();
        }else if(type == 2){
            text = firstCheckBoxInMoreDialog.getText();
            firstCheckBoxInMoreDialog.click();
        }else{
            minimumValueSlideRight();
            maximumValueSlideLeft();
            text = minimumSalaryTextElement.getText();
        }
        return text;
    }

    private boolean didButtonNameChangeToSelectedLimiter(String newButtonText, WebElement limiterButton){
        String limiterButtonText = limiterButton.getText();
        if(limiterButtonText.endsWith("...")) {
            return limiterButtonText.substring(0, limiterButtonText.length() - 3).equalsIgnoreCase(newButtonText);
        }else{
            return limiterButtonText.equalsIgnoreCase(newButtonText);
        }
    }

    private boolean didButtonNameChangeToDefaultValue(String currentButtonText, WebElement limiterButton){
        return !(currentButtonText.equalsIgnoreCase(limiterButton.getText()) || limiterButton.getText().endsWith("..."));
    }

    private void applyChoice(){
        applyDialogButton.click();
    }

    private void resetChoice(){
        resetAllDialogButton.click();
    }

    public WebElement getTechnologyButton(){
        return technologyButton;
    }

    public WebElement getLocationButton(){
        return locationButton;
    }

    public WebElement getCategoryButton(){
        return categoryButton;
    }

    public WebElement getMoreButton(){
        return moreButton;
    }

    public WebElement getSalaryButton(){
        return salaryButton;
    }

    //Limiter tyes: 1 = button, 2 = checkbox, 3 = slider
    public void limitSearchResults(WebElement limiterButton, int type){
        limiterButton.click();
        assertThat(isDialogWindowVisible()).isTrue();
        String searchLimiterText = chooseSearchLimiter(type);
        applyChoice();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(didButtonNameChangeToSelectedLimiter(searchLimiterText, limiterButton)).isTrue();
        softly.assertThat(areSearchResultsVisible()).isTrue();
        softly.assertThat(loadingImageIsNotVisible()).isTrue();
        softly.assertAll();
    }

    public void resetSetting(WebElement limiterButton){
        String currentButtonText = limiterButton.getText();
        limiterButton.click();
        assertThat(isDialogWindowVisible()).isTrue();
        resetChoice();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(didButtonNameChangeToDefaultValue(currentButtonText, limiterButton)).isTrue();
        softly.assertThat(areSearchResultsVisible()).isFalse();
        softly.assertThat(loadingImageIsNotVisible()).isTrue();
        softly.assertAll();
    }

    private void minimumValueSlideRight(){
        Actions action = new Actions(driver);
        action.clickAndHold(minimumValueSliderElement);
        action.moveByOffset(50,0);
        action.release();
        action.build().perform();
    }

    private void maximumValueSlideLeft(){
        Actions action = new Actions(driver);
        action.clickAndHold(maximumValueSliderElement);
        action.moveByOffset(-50,0);
        action.release();
        action.build().perform();
    }

    public String getWebsiteLanguage(){
        return websiteHtmlTag.getAttribute("lang");
    }

    public String getWebsiteTitle(){
        return driver.getTitle();
    }

    public void switchLanguage(){
        languageSwitcher.click();
        if(languageSwitcher.getText().equalsIgnoreCase("PL")){
            hungarianLanguage.click();
        }else{
            polishLanguage.click();
        }
    }

    public void openSubscribeDialog(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", subscribeButton);
    }
    public boolean isSubscribeDialogDisplayed(){
        return isElementDisplayed(subscribeDialog);
    }

    public void checkConsentCheckBox(){
        subscribeConsentCheckBox.click();
    }

    public void inputEmailAddress(String address){
        subscribeEmailInputField.sendKeys(address);
    }

    public void confirmSubscription(){
        subscribeSubmitButton.click();
    }

    public boolean isSubscriptionSuccessful(){
        boolean isSuccessful = !isElementDisplayed(closeDialogButton);
        if(!isSuccessful) {
            closeSubscriptionDialog();
        }
        return isSuccessful;
    }

    public boolean isCheckboxErrorMessageDisplayed(){
       return isElementDisplayed(subscribeCheckBoxErrorMessage);
    }

    public boolean isEmailErrorMessageDisplayed(){
        return isElementDisplayed(subscribeEmailErrorMessage);
    }

    private void closeSubscriptionDialog(){
        closeDialogButton.click();
    }

}
