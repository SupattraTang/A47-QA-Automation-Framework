import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework21 extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String email, String password){
        //provide email
        enterEmail(email);
        //provide password
        enterPassword(password);
        //click submit
        clickSubmit();

        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertTrue(doesPlaylistExist());
    }
}
