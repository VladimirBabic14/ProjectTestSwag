import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwagLabTest extends BaseTest
{
    LoginPage loginPage;

    @BeforeMethod
    public void setUp()
    {
        driver = openBrowser();
        loginPage = new LoginPage(driver);

    }
    @Test
    public void LoginOnPage()
    {
        loginPage.LoginOnPage("standard_user","secret_sauce");

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @AfterMethod
    public void close()
    {
    driver.close();
    }

}
