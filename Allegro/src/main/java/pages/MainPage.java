package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageBase {

    @FindBy(css="._d25db_2BHdc")
    public WebElement searchInput;

    @FindBy(css="._d25db_2hccx")
    public WebElement searchButton;

    @FindBy(css="button._8tsq7:nth-child(1)")
    private WebElement dialogButton;

    @FindBy(xpath="//a[@name ='allegro-navigation-scrolltop']/parent::div/button")
    public WebElement scrollToTopButton;

    @FindBy(className = "_d25db_JyaXr")
    public WebElement categorySelect;

    @FindBy(xpath="//nav/div/div[2]/div[3]/button")
    public WebElement mojeAllegroButton;

    @FindBy(xpath="//nav/div/div[2]/div[3]/div")
    public WebElement mojeAllegroPopUp;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void navigateToMainPageIfNecessary(String url){
        if(!driver.getCurrentUrl().equals("https://www.allegro.pl") && !driver.getCurrentUrl().equals("https://allegro.pl")){
            goBackToMainPage(url);
        }
    }

    private void goBackToMainPage(String url){
        driver.navigate().to(url);
    }

    public boolean verifyPageTitle() {
        String pageTitle = driver.getTitle();
        return pageTitle.contains("Allegro");
    }

    public void getRidOfDialogBoxIfIsDisplayed(){
        if(driver.findElements(By.cssSelector("button._8tsq7:nth-child(1)")).size()!=0){
            dialogButton.click();
        }
    }

    public void scrollToBottom(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public boolean isItScrolledToTop(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript("return window.pageYOffset;") == Long.valueOf(0);
    }

}