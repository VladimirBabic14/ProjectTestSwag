import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwagLabTest extends BaseTest
{
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;


    @BeforeMethod
    public void setUp()
    {
        driver = openBrowser();
        loginPage = new LoginPage(driver);
        inventoryPage =new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);

    }
    @Test
    public void LoginOnPage()
    {
        loginPage.LoginOnPage("standard_user","secret_sauce");

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }
    @Test
    public void buyProduct()
    {
        loginPage.LoginOnPage("standard_user","secret_sauce");
        inventoryPage.addBackpack();
        inventoryPage.clickOnCart();
        Assert.assertEquals(cartPage.getInfoPrice(),"$29.99");
        Assert.assertEquals(cartPage.getProductName(),"Sauce Labs Backpack");

        cartPage.clickCheckout();
        checkoutStepOnePage.inputPersonalInfo("Marko","Naumovic","11000");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
        checkoutStepOnePage.clickFinish();

        Assert.assertEquals(checkoutStepOnePage.getInfoMessage(),"Thank you for your order!");

    }

    @AfterMethod
    public void after()
    {
        driver.quit();
    }




}
