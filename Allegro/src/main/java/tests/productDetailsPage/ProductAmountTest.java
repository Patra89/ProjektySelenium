package tests.productDetailsPage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import tests.TestBase;

public class ProductAmountTest extends TestBase {

    private WebDriver driver;
    private ProductDetailsPage page;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new ProductDetailsPage(driver);
        page.getRidOfDialogBoxIfIsDisplayed();
        page.navigateToRandomProductDetailsPage();
    }

    @Test
    public void inputTooMuchProduct(){
        page.inputAmountOfProduct(page.getMaxAmountOfProductAvailable()+1);
        Assert.assertTrue(page.wrongAmountErrorTextElement.isDisplayed(),
                "Warning message is not displayed when amount higher than available amount of product");
    }

    @Test
    public void inputZeroProduct(){
        page.inputAmountOfProduct(0);
        Assert.assertTrue(page.wrongAmountErrorTextElement.isDisplayed(),
                "Warning message is not displayed when amount is 0");
    }

    @Test
    public void inputBelowZeroAmountOfProduct(){
        page.inputAmountOfProduct(-1);
        Assert.assertTrue(page.wrongAmountErrorTextElement.isDisplayed(),
                "Warning message is not displayed when amount is below zero");
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void inputRightAmountOfProduct(){
        page.inputAmountOfProduct(page.getMaxAmountOfProductAvailable());
        Assert.assertFalse(page.wrongAmountErrorTextElement.isDisplayed(),
                "Warning message is displayed when amount of product is adequate");
    }

    @Test
    public void inputTooMuchProductByClickingPlusButton(){
        int startAmount = 1;
        if(page.getMaxAmountOfProductAvailable()>10) {
            startAmount = page.getMaxAmountOfProductAvailable()-1;
        }
        int maxBoundary = page.getMaxAmountOfProductAvailable()+1;

        page.inputAmountOfProduct(startAmount);
        for(int i = startAmount; i <= maxBoundary; i++){
            page.clickOnElementWithJavaScriptExecutor(page.productAmountAddButton);
        }
        Assert.assertEquals(page.getMaxAmountOfProductAvailable(),
                Integer.parseInt(page.getElementValueAttribute(page.productAmountInputField)),
                "It is possible to input above max amount of product");
    }

    @Test
    public void inputBelowZeroProductByClickingMinusButton(){
        page.inputAmountOfProduct(1);
        page.clickOnElementWithJavaScriptExecutor(page.productAmountSubtractButton);
        Assert.assertEquals(Integer.parseInt(page.getElementValueAttribute(page.productAmountInputField)),1,
                "It is possible to input below 0 amount of product");
    }

}
