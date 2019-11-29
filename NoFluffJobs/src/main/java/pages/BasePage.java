package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePage {

    WebDriver driver;

    BasePage(WebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
        PageFactory.initElements(factory, this);
    }

    public boolean isElementDisplayed(WebElement element){
        try{
            return element.isDisplayed();
        }catch(NoSuchElementException e){
            return false;
        }
    }

    //if the elem object in javascript code is null(meaning it's not in viewport) the method will return false
    boolean isElementVisibleInViewport(WebElement element) {
        return (boolean)((JavascriptExecutor)driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }

}


