import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllSongsPage;
import pages.HomePage;
import pages.LoginPage;


public class SongTests extends BaseTest {

    @Test
    public void playSong(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongs = new AllSongsPage(driver);

        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io").providePassword("te$t$tudent1").clickSubmit();

        homePage.chooseAllSongsList();
        allSongs.contextClickFirstSong();
        allSongs.choosePlayOption();

        Assert.assertTrue(allSongs.isSongPlaying());
    }


    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void deletePlaylist(String email, String password) {

        String deletedPlayListMsg = "Deleted playlist";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io").providePassword("te$t$tudent1").clickSubmit();

        //Start play song by click play
        openPlayList();
        clickDeletePlayListBtn();
        ConfirmDelete();
        Assert.assertTrue(getDeletedPlayListMsg().contains(deletedPlayListMsg));
    }

    @Test
    public void hoverOverPlayButton(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongs = new AllSongsPage(driver);

        loginPage.login();
        homePage.chooseAllSongsList();

        homePage.hoverPlay();
        Assert.assertTrue(hoverPlay().isDisplayed());
    }

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void countSongInPlayList(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io").providePassword("te$t$tudent1").clickSubmit();

        choosePlayListByName("Test05");
        displayAllSong();
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
    }

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String email, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io").providePassword("te$t$tudent1").clickSubmit();

        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertTrue(doesPlaylistExist());
    }
}
