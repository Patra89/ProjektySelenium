package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;

public class Wait {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final int WAIT_DEFAULT_TIMEOUT = 5;

    public Wait(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WAIT_DEFAULT_TIMEOUT);
    }

    public boolean forElementToDisappear(WebElement element){
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        }catch(NoSuchElementException e){
            return true;
        }catch(TimeoutException e){
            return false;
        }
        return true;
    }

    public boolean forElementToBeClickable(WebElement element){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch(NoSuchElementException | TimeoutException e){
            return false;
        }
        return true;
    }


    public boolean forWindowsToOpen(int howManyWindows){
        try{
            wait.until(ExpectedConditions.numberOfWindowsToBe(howManyWindows));
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public void forPageUrlToNotBeNull(){
        wait.until(ExpectedConditions.urlContains("http"));
    }

}