import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllSongsPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void LoginValidEmailPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //loginPage.login();
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io").providePassword("te$t$tudent1").clickSubmit();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
}