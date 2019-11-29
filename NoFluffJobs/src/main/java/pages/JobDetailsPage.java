package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.Wait;
import java.util.HashSet;
import java.util.List;

public class JobDetailsPage extends BasePage{

    @FindBy(css = "nfj-posting-apply-box")
    private WebElement applyBox;

    @FindBy(css = "nfj-posting-apply-btn")
    private WebElement applyButton;

    @FindBy(css = "a[data-cy = 'headerNext']")
    private WebElement nextJobPostingButton;

    @FindBy(css = "a[data-cy = 'headerPrev']")
    private WebElement previousJobPostingButton;

    @FindBy(css = "div[class='apply-no-login']>button")
    private WebElement applyWithoutSignInButton;

    @FindBy(css = "nfj-posting-apply-internal-modal")
    private WebElement jobApplicationForm;

    @FindBy(css = "div.list-item:nth-of-type(1) nfj-postings-item a")
    private WebElement jobPostingOnMainPage;

    @FindBy(css = "nfj-btn-toggle-compare")
    private WebElement compareButton;

    @FindBy(css = ".bottom-bar")
    private WebElement bottomBar;

    @FindBy(css = ".cookie-information button")
    private WebElement cookiesButton;

    @FindBy(xpath = "//div[contains(@class,\'compare-bar-buttons\')]//button")
    private WebElement clearCompareListButton;

    @FindBy(xpath = "//section/nfj-compare-columns[1]")
    private WebElement comparisionTable;

    @FindBy(css = ".compare-bar-buttons a")
    private WebElement compareButtonInBottomBar;

    @FindBy(css = "button.close")
    private WebElement shareByEmailDialogCloseButton;

    @FindBy(css = "nfj-posting-share")
    private WebElement shareButton;

    @FindBy(css = ".share-panel.active")
    private WebElement sharePanel;


    public JobDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToJobPostingSubpage(){
        jobPostingOnMainPage.click();
        Wait wait = new Wait(driver);
        wait.forElementToDisappear(jobPostingOnMainPage);
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,3000)");
    }

    public void  scrollUp(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public boolean isApplyBoxVisible(){
        return applyBox.isDisplayed();
    }

    public boolean isApplyBoxWithinViewport(){
       return isElementVisibleInViewport(applyBox);
    }

    public int howManyItemsInCompareList(){
        List<WebElement> allListItems = driver.findElements(By.xpath("//div[contains(@class, 'bottom-bar ')]/div[1]/div"));
        return allListItems.size();
    }

    public boolean isCompareListShown(){
        return isElementDisplayed(bottomBar);
    }

    public void addPostingToCompareList(){
        compareButton.click();
    }

    public void addMultiplePostingsToCompareList(int howMany){
        HashSet<String> postingIds = new HashSet<>();
        while(postingIds.size()<howMany){
            nextJobPostingButton.click();
            String postingId = getPostingId(driver.getCurrentUrl());
            if(!postingIds.contains(postingId)) {
                postingIds.add(postingId);
                compareButton.click();
            }
        }
    }

    private String getPostingId(String url){
        return url.substring(url.length()-8);
    }

    public void removePostingFromCompareList(){
        WebElement closeButton = driver.findElement(By.xpath("//div[contains(@class, 'bottom-bar ')]/div[1]/div[1]//i"));
        closeButton.click();
    }

    public void removeAllPostingsFromCompareList(){
        clearCompareListButton.click();
    }

    public void removeCookiesBarIfVisible(){
        if(isElementDisplayed(cookiesButton)){
            cookiesButton.click();
        }
    }

    public void compareJobPostings(){
        compareButtonInBottomBar.click();
    }

    public boolean isComparisionTableShown(){
        return isElementDisplayed(comparisionTable);
    }

    public int howManyColumnsInComparisionTable(){
        List<WebElement> tableColumns = driver.findElements(By.xpath("//section/nfj-compare-columns[1]/div"));
        return tableColumns.size();
    }

    public void openShareOptionsPopUp(){
        if(!isElementDisplayed(sharePanel)) {
            shareButton.click();
        }
    }

    public void closeShareByEmailDialog(){
        shareByEmailDialogCloseButton.click();
    }

}
