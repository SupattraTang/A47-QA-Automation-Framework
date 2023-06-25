import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class homework17 extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {

        //Open the URL for the web page on the chrome browser
        openLoginUrl();

        //Put the email field inside the web page
        enterEmail("supattra.tangsombutpaiboon@testpro.io");

        //Put the password field inside the web page
        enterPassword("te$t$tudent1");

        //Click on the submit button
        clickSubmit();

        //Check if the avatar user is display (success login check)
        WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
        Assert.assertTrue(avatar.isDisplayed());

        //Search song "Dark Day Grav"
        searchSong("Dark Day Grav");
        Thread.sleep(2000);
        //Click View All
        clickViewAll();
        Thread.sleep(2000);
        clickFirstSong();
        clickAddButton();
        selecteFirstplaylist();






    }

}
