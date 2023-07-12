package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver = null;
    protected WebDriverWait wait = null;
    protected Actions actions = null;
    public static String url = null;

    public BasePage (WebDriver givenDriver) {
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver,this);
    }

//    private By overlayLocator = By.cssSelector(".overlay.loading");
    @FindBy (css = ".overlay.loading")
    WebElement overlayLocator;

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public BasePage waitForOverlayToGoAway(){
        wait.until(ExpectedConditions.invisibilityOf(overlayLocator));
        return this;
    }

    public void contextClick(By locator) {
        WebElement contextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        actions.contextClick(contextElement).perform();
    }

    public void hoverAction(By locator){
        WebElement play = driver.findElement(locator);
        actions.moveToElement(play).perform();
    }
}