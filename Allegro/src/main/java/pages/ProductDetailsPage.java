package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.Wait;

import java.util.List;

public class ProductDetailsPage extends PageBase{

    @FindBy(xpath="//form/div[1]/div[2]/div[1]/input")
    public WebElement productAmountInputField;

    @FindBy(xpath="//form/div[1]/div[2]/div[1]/button[2]")
    public WebElement productAmountAddButton;

    @FindBy(xpath="//form/div[1]/div[2]/div[1]/button[1]")
    public WebElement productAmountSubtractButton;

    @FindBy(xpath="//form/div[1]/div[2]/div[2]")
    private WebElement maximalAmountAvailableElement;

    @FindBy(xpath="//form/div[1]/div[3]")
    public WebElement wrongAmountErrorTextElement;

    @FindBy(xpath="//div[@data-box-name='Container Accordion']")
    public WebElement bottomMenuContainer;

    @FindBy(xpath="//div[@data-box-name=\'Accordion\']//h2")
    public List<WebElement>bottomMenuElements;

    @FindBy(xpath="//div[@data-box-name=\'Accordion\']//h2")
    public List<WebElement>bottomMenuDialogBoxesTitles;

    @FindBy(id="add-to-cart-button")
    public WebElement addToTheCartButton;

    @FindBy(xpath="//div[@data-role= \'opbox-cart-status\']")
    public WebElement cartIconButton;

    @FindBy(xpath="//section[@data-box-name=\'cart-preview\']")
    public WebElement cartPopUp;

    @FindBy(xpath="//section[@data-box-name=\'cart-preview\']/div/div/h4")
    public WebElement cartPopUpTitle;

    @FindBy(css="._12isx > button:nth-child(1)")
    private WebElement dialogButton;

    public ProductDetailsPage(WebDriver driver){
        super(driver);
    }

    public void getRidOfDialogBoxIfIsDisplayed(){
        if(driver.findElements(By.cssSelector("._12isx > button:nth-child(1)")).size()!=0){
            dialogButton.click();
        }
    }

    public int getMaxAmountOfProductAvailable(){
        return Integer.parseInt(maximalAmountAvailableElement.getText().replaceAll("[\\D]", ""));
    }

    public void inputAmountOfProduct(int amount){
        productAmountInputField.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        productAmountInputField.sendKeys(String.valueOf(amount));
    }

    public void clickOnElementWithJavaScriptExecutor(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getElementValueAttribute(WebElement element){
        return element.getAttribute("value");
    }

    public void clickOutsideOfDialogBox(WebElement element, int offset){
        Actions action = new Actions(driver);
        action.moveToElement(element, offset, 0).click().build().perform();
    }

    public void navigateToRandomProductDetailsPage() {
        WebElement firstItem = driver.findElement(By.xpath("//section[2]/section/article[1]/div/div/div[2]"));
        firstItem.click();
        new Wait(driver).forElementToDisappear(firstItem);
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://allegro.pl/oferta/"), "Home page title doesn't match");
    }


}
