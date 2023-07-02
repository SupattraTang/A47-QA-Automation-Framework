import org.testng.annotations.Test;


public class LoginTests extends BaseTest {
    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void LoginEmptyEmailPasswordTest(String email, String password) {;
        //provide email
        enterEmail(email);
        //provide password
        enterPassword(password);
        //click submit
        clickSubmit();
    }
}


