package applicationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by test on 21.7.2017.
 */
public class CommonHelper {

    protected final AppiumDriver driver;

    public CommonHelper(AppiumDriver driver){
        this.driver = driver;
    }

    /* Method that clicks allow to system location popup */
    public void assertLocation() {
        if(isElementVisible(IdentifierConstants.allowLocationButton, 5)) {
            driver.findElement(IdentifierConstants.allowLocationButton).click();
        }
    }

    /* Method that will wait if element is visible in given time frame */
    void waitForElementVisibility(By id, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(id));
    }

    /* Method that will generate random String from given chars */
    String generateRandomString(int length, String chars) {
        return RandomStringUtils.random(length, chars);
    }

    /* Method that swipes device screen down */
    public void swipeDown() {
        Dimension size = driver.manage().window().getSize();
        int starty = size.height - 10;
        int endy = size.height / 2;
        int startx = size.width / 2;
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(startx, starty).waitAction().moveTo(startx, endy).release().perform();
    }

    /* Method that checks if element by given id is visible in time frame */
    boolean isElementVisible(By id, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* Method that pauses thread for given time frame */
    void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
