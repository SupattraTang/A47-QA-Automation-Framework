import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void LoginInvalidEmailPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail("test.test@test.test")
                 .providePassword("te$t$tudent")
                 .clickSubmit();
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }

    @Test
    public void LoginValidEmailPasswordTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(driver);
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io")
                 .providePassword("te$t$tudent1")
                 .clickSubmit();
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
        Assert.assertTrue(homePage.isAvatarDisplay());
    }
}