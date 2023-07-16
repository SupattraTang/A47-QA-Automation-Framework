package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) { super(givenDriver); }

    private By allSongs = By.cssSelector("i a.songs");

    @FindBy(css = "img.avatar")
    WebElement userAvatarIcon;
    public WebElement getUserAvatar() {
        return userAvatarIcon;
    }

    public HomePage chooseAvatarIcon () {
        waitForOverlayToGoAway();
        userAvatarIcon.isEnabled();
        userAvatarIcon.isDisplayed();
        userAvatarIcon.click();
        return this;
    }

    // Play Song methods
    public void chooseAllSongsList() {
        waitForOverlayToGoAway();
        findElement(allSongs).click();
    }


    By playBtn = By.cssSelector("[data-testid='play-btn']");
//    @FindBy (css = "[data-testid='play-btn']")
//    WebElement playBtn;

    public WebElement hoverPlay(){
        hoverAction(playBtn);
        return findElement(playBtn);
    }
}