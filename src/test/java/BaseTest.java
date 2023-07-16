import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class BaseTest {

    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static Actions actions = null;
    public static String url = "https://qa.koel.app/";

    public static final  ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();

    @BeforeSuite
    static void setupClass() {
        //WebDriverManager.chromedriver().setup();
    }

    public static WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("114.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "supattra.tangsombutpaiboon");
        ltOptions.put("accessKey", "3ghIN1WhfomOeZJu3slfSVnpPJfv0Iu8EIPQsPpArTVZtEcK5H");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }

    public static WebDriver pickBrowser(String browserName) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.28:4444"; //repleace with your grid url

        switch(browserName){
            //gradle clean testSmoke -DbrowserName==firefox
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            //gradle clean testSmoke -DbrowserName==MSEdge
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            //gradle clean testSmoke -DbrowserName==grid-edge
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            //gradle clean testSmoke -DbrowserName==grid-firefox
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            //gradle clean testSmoke -DbrowserName==grid-chrome
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            //gradle clean testSmoke -DbrowserName==chrome (or whatever)
            case "cloud":
                return  lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL) throws MalformedURLException {
        // Added ChromeOptions argument below to fix websocket error
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(options);

//        driver = pickBrowser(System.getProperty("browser"));

        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        url = BaseURL;
//        driver.get(url);
        navigateToPage();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        actions = new Actions(getDriver());

    }

    @AfterMethod
    public void closeBrowser() {
        getDriver().quit();
        threadDriver.remove();
    }

    public WebDriver getDriver(){
        System.out.println("Driver is accessed");
        return threadDriver.get();
    }


    public void navigateToPage() { getDriver().get(url); }

    @DataProvider(name = "IncorrectLoginProviders")
    public static Object[][] getDataFromDataProviders() {
        return new Object[][]{
                {"notExisting@email.com", "NotExistingPassword"},
                {"demo@class.com", ""},
                {"", ""}
        };
    }

    @DataProvider(name = "CorrectLoginProviders")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {"supattra.tangsombutpaiboon@testpro.io", "te$t$tudent1"}
        };
    }


    //Profile Tests Helper Functions

    protected static String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

//    protected static void providePassword(String password) {
//        WebElement currentPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='current_password']")));
//        currentPassword.click();
//        currentPassword.clear();
//        currentPassword.sendKeys(password);
//    }

    protected static void provideProfileName(String name) {
        WebElement currentName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='name']")));
        currentName.clear();
        currentName.sendKeys(name);
    }

//    protected static void clickSaveButton() {
//        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(("button.btn-submit"))));
//        saveButton.click();
//    }

//    protected static void searchSong(String songName) {
//        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='search']")));
//        searchField.clear();
//        searchField.sendKeys(songName);
//    }

    protected static void clickViewAll() {
        WebElement viewAllButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-test='view-all-songs-btn']")));
        viewAllButton.click();
    }

    protected static void clickFirstSong() {
        WebElement firstSong = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper>div>div>div>table>tr:nth-child(1)")));
        firstSong.click();
    }

    protected static void clickAddButton() {
        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-add-to")));
        addButton.click();
    }

    protected static void selectFirstPlaylist() {
        WebElement playlistAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper>header>div>div[data-test='add-to-menu']")));
        playlistAdd.isDisplayed();
        WebElement testPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper>header>div>div[data-test='add-to-menu']>section.existing-playlists>ul>li.playlist")));
        testPlaylist.click();
    }

    protected void clickPreviousSong() {
        WebElement PreviousSongButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@data-testid='play-prev-btn']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(PreviousSongButton).click().build().perform();
    }

    protected void clickPlaySong() {
        WebElement playSongButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/i[@class='fa fa-play']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(playSongButton).click().build().perform();
    }

    protected void clickPlayNextSong() {
        WebElement nextSongButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@data-testid='play-next-btn']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(nextSongButton).click().build().perform();
    }

    protected void verifyPauseButton() {
        WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.pause")));
        pauseButton.isDisplayed();
    }

    //help function for delete playlist
    public void openPlayList() {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        playlist.click();
    }

    public void clickDeletePlayListBtn() {
        WebElement delecteplaylistBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".del.btn-delete-playlist")));
        delecteplaylistBtn.click();
        //If there are songs on the playlist
        //If yes need to click confirm
        //If no song on the playlist (continue)
    }

    public void ConfirmDelete() {
        WebElement confirmBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ok")));
        confirmBtn.click();
    }

    public String getDeletedPlayListMsg() {
        WebElement notificationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMsg.getText();
    }

    public Boolean isSongPlaying() {
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainFooter']/div[2]/div[2]/div/button[1]/div")));
        return soundBar.isDisplayed();
    }

    //Double Click Method
    public void doubleClickChoosePlaylist() {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }

    //Play song helper functions
    public void chooseAllSongList() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay.loading")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li a.songs"))).click();
    }

    public void contextClickFirstSong() {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        actions.contextClick(firstSongElement).perform();
    }

    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".song-menu li.playback"))).click();
    }

    //Hover helper function
    public WebElement hoverPlay() {
        WebElement play = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(play).perform();
        return driver.findElement(By.cssSelector("[data-testid='play-btn']"));
    }

    //Count Songs Helper Functions
    public void choosePlayListByName(String playlistName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + playlistName + "')]"))).click();
    }

    public int countSongs(){
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }
    public String getPlaylistDetails(){
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }

    public void displayAllSong(){
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs Found:" + countSongs());
            for (WebElement e: songList){
                System.out.println(e.getText());
            }
    }

    //Helper function for Rename
    String newPlaylistName = "new name";
    public void doubleClickPlaylist(){
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }

    public void enterNewPlaylistName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public  boolean doesPlaylistExist(){
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + newPlaylistName + "']")));
        return playlistElement.isDisplayed();
    }


}
