import org.testng.Assert;
import org.testng.annotations.Test;


public class SongTests extends BaseTest {

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void deletePlaylist(String email, String password) {
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

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void playSong(String email, String password) throws InterruptedException {
        String deletedPlayListMsg = "Deleted playlist";
        //provide email
        enterEmail(email);
        //provide password
        enterPassword(password);
        //click submit
        clickSubmit();

        chooseAllSongList();
        contextClickFirstSong();
        choosePlayOption();
        Assert.assertTrue(isSongPlaying());
    }

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void hoverOverPlayButton(String email, String password){
        //provide email
        enterEmail(email);
        //provide password
        enterPassword(password);
        //click submit
        clickSubmit();

        chooseAllSongList();
        hoverPlay();
        Assert.assertTrue(hoverPlay().isDisplayed());
    }

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void countSongInPlayList(String email, String password){
        //provide email
        enterEmail(email);
        //provide password
        enterPassword(password);
        //click submit
        clickSubmit();

        choosePlayListByName("Test05");
        displayAllSong();
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
    }

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
