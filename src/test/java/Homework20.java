import org.testng.Assert;
import org.testng.annotations.Test;


public class Homework20 extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void deletePlaylist(String email, String password) throws InterruptedException {
        String deletedPlayListMsg = "Deleted playlist";
        //provide email
        enterEmail(email);
        //provide password
        enterPassword(password);
        //click submit
        clickSubmit();

        //Start play song by click play
        openPlayList();
        clickDeletePlayListBtn();
        ConfirmDelete();
        Assert.assertTrue(getDeletedPlayListMsg().contains(deletedPlayListMsg));
        }
    }
