import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import java.time.Duration;
import java.util.UUID;


public class BaseTest {

    public static WebDriver driver = null;
    public static String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void launchBrowser() {
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    protected static void clickSubmit() {
        WebElement submitLogin = driver.findElement(By.cssSelector("button[Type='submit']"));
        submitLogin.click();
    }

    protected static void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.cssSelector("[type='password']"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    protected static void enterEmail(String email) {
        WebElement emailInput = driver.findElement(By.cssSelector("[type='email']"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public static void openLoginUrl() {
        String url = "https://qa.koel.app/";
        driver.get(url);
    }

    protected static void clickLogout() {
        WebElement logoutBtn = driver.findElement(By.cssSelector(".logout.control"));
        logoutBtn.click();
    }
    //Profile Tests Helper Functions
    protected static void clickAvatarIcon() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
        avatarIcon.click();
    }

    protected static String generateRadomName(){
        return UUID.randomUUID().toString().replace("-","");
    }
    protected static void providePassword(String password) {
        WebElement currentPassword = driver.findElement(By.cssSelector("[name='current_password']"));
        currentPassword.click();
        currentPassword.clear();
        currentPassword.sendKeys(password);
    }
    protected static void provideProfileName(String name) {
        WebElement currentName = driver.findElement(By.cssSelector("input[name='name']"));
        currentName.clear();
        currentName.sendKeys(name);
    }
    protected static void clickSaveButton(){
        WebElement saveButton =  driver.findElement(By.cssSelector(("button.btn-submit")));
        saveButton.click();
    }
    protected static void searchSong(String songName){
        WebElement searchField = driver.findElement(By.cssSelector("input[type='search']"));
        searchField.clear();
        searchField.sendKeys(songName);
    }

    protected static void clickViewAll(){
        WebElement viewAllButton = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        viewAllButton.click();
    }
    protected static void clickFirstSong(){
        WebElement firstSong = driver.findElement(By.cssSelector("section#songResultsWrapper>div>div>div>table>tr:nth-child(1)"));
        firstSong.click();
    }

    protected static void clickAddButton(){
        WebElement addButton = driver.findElement(By.cssSelector("button.btn-add-to"));
        addButton.click();
    }
    protected static void selecteFirstplaylist(){
        WebElement playlistAdd = driver.findElement(By.cssSelector("section#songResultsWrapper>header>div>div[data-test='add-to-menu']"));
        playlistAdd.isDisplayed();
        WebElement testPlaylist = driver.findElement(By.cssSelector("section#songResultsWrapper>header>div>div[data-test='add-to-menu']>section.existing-playlists>ul>li.playlist"));
        testPlaylist.click();
    }

}