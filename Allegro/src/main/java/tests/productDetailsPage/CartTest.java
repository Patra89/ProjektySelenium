package tests.productDetailsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import tests.TestBase;
import utils.Wait;

public class CartTest extends TestBase {

    private WebDriver driver;
    private ProductDetailsPage page;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = getDriver();
        page = new ProductDetailsPage(driver);
        page.getRidOfDialogBoxIfIsDisplayed();
        page.navigateToRandomProductDetailsPage();
    }

    @Test(priority = 1)
    public void emptyCartPopUpTest(){
        page.moveCursorToElement(page.cartIconButton);
        new Wait(driver).forElementToBeVisible(page.cartPopUp);
        Assert.assertTrue(page.cartPopUp.isDisplayed(),
                "Cart pop up is not visible when cursor on cart icon button");
        Assert.assertTrue(page.cartPopUpTitle.getText()
                .equalsIgnoreCase("Twój koszyk jest pusty"), "Cart is not empty");
    }
    @Test(priority = 2)
    public void cartPopUpWithItemTest(){
        Wait wait = new Wait(driver);
        page.addToTheCartButton.click();
        //wait.forElementToBeVisible(productDetailsPage.itemAddedToCartDialogBox);
        //Assert.assertTrue(productDetailsPage.itemAddedToCartDialogBox.isDisplayed(), "Dialog box after buying an item is not shown");
        page.clickOutsideOfDialogBox(page.cartIconButton,300);
        page.moveCursorToElement(page.cartIconButton);
        wait.forElementToBeVisible(page.cartPopUp);
        Assert.assertTrue(page.cartPopUp.isDisplayed(),"Cart pop up is not visible when cursor on cart icon button");
        Assert.assertTrue(page.cartPopUp.findElements(By.xpath("//*[text()=\'"+driver.getTitle()+"\']")).size()>0);
    }
}
