import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllSongsPage;
import pages.HomePage;
import pages.LoginPage;


public class SongTests extends BaseTest {

    @Test
    public void playSong(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongs = new AllSongsPage(driver);

        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io")
                 .providePassword("te$t$tudent1")
                 .clickSubmit();
        Assert.assertEquals(getDriver().getCurrentUrl(), url);

        homePage.chooseAllSongsList();
        allSongs.contextClickFirstSong();
        allSongs.choosePlayOption();

        Assert.assertTrue(allSongs.isSongPlaying());
    }


    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void deletePlaylist(String email, String password) {
        String deletedPlayListMsg = "Deleted playlist";
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io")
                .providePassword("te$t$tudent1")
                .clickSubmit();
        Assert.assertEquals(getDriver().getCurrentUrl(), url);

        //Start play song by click play
        openPlayList();
        clickDeletePlayListBtn();
        ConfirmDelete();
        Assert.assertTrue(getDeletedPlayListMsg().contains(deletedPlayListMsg));
    }

    @Test
    public void hoverOverPlayButton(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongs = new AllSongsPage(driver);

        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io")
                .providePassword("te$t$tudent1")
                .clickSubmit();
        Assert.assertEquals(getDriver().getCurrentUrl(), url);

        homePage.chooseAllSongsList();

        homePage.hoverPlay();
        Assert.assertTrue(hoverPlay().isDisplayed());
    }

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void countSongInPlayList(String email, String password){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io")
                .providePassword("te$t$tudent1")
                .clickSubmit();
        Assert.assertEquals(getDriver().getCurrentUrl(), url);

        choosePlayListByName("Test05");
        displayAllSong();
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
    }

    @Test (dataProvider = "CorrectLoginProviders", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String email, String password){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail("supattra.tangsombutpaiboon@testpro.io")
                .providePassword("te$t$tudent1")
                .clickSubmit();
        Assert.assertEquals(getDriver().getCurrentUrl(), url);

        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertTrue(doesPlaylistExist());
    }
}
