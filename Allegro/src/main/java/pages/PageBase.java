package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

class PageBase {

    WebDriver driver;

    PageBase(WebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }

    public WebElement getElementByPartialText(String text){
        return driver.findElement(By.partialLinkText(text));
    }

    public void moveCursorToElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public WebElement getElementByCssSelector(String selector){
        return driver.findElement(By.cssSelector(selector));
    }

}
