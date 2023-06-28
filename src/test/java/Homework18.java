import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest {

    @Test
    public void playSong() throws InterruptedException {

        //Navigate to "https://qa.koel.app/".
        openLoginUrl();
        //Log in with your credentials.
        enterEmail("supattra.tangsombutpaiboon@testpro.io");
        enterPassword("te$t$tudent1");
        clickSubmit();

        //Check if the avatar user is display (success login check)
        WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
        Assert.assertTrue(avatar.isDisplayed());

        //Start play song by click play
        Thread.sleep(2000);
        clickPlaySong();

        //Click play next song
        Thread.sleep(5000);
        clickPlayNextSong();
        Thread.sleep(5000);

        //Validate song playing by verify element display pause
        verifyPauseButton();
    }
}
