package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllSongsPage extends BasePage {
    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

//    private By firstSong = By.cssSelector(".all-songs tr.song-item:nth-child(1)");
//    private By playSong = By.cssSelector(".song-menu li.playback");
//    private By soundBar = By.xpath("//*[@id='mainFooter']/div[2]/div[2]/div/button[1]/div");
    @FindBy(css = ".all-songs tr.song-item:nth-child(1)")
    WebElement firstSong;
    @FindBy(css = ".song-menu li.playback")
    WebElement playSongBtn;
    @FindBy(xpath = "//*[@id='mainFooter']/div[2]/div[2]/div/button[1]/div")
    WebElement soundBar;

    protected void doubleClick(WebElement e){ actions.doubleClick(wait.until(ExpectedConditions.elementToBeClickable(e))).perform(); }
    protected void contextClick(WebElement e){
        actions.contextClick(wait.until(ExpectedConditions.elementToBeClickable(e))).perform(); }

    public AllSongsPage contextClickFirstSong() {
        contextClick(firstSong);
        return this;
    }

    public AllSongsPage choosePlayOption() {
        playSongBtn.click();
        return this;
    }

    public boolean isSongPlaying() {
        return findElement((By) soundBar).isDisplayed();
    }
}
