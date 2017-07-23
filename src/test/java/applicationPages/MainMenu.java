package applicationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

/**
 * Created by Mikko on 21.7.2017.
 */
public class MainMenu extends CommonHelper {

    protected final AppiumDriver driver;

    public MainMenu(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }

    /* Method that opens main menu in sidebar */
    public void openMainMenu() {
        //No idea why this would not work, need to investigate is it related to image button
        //driver.findElementByAccessibilityId(IdentifierConstants.mainMenuButton).click();
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(90, 90).release().perform();
        waitForElementVisibility(IdentifierConstants.signInButton, 5);
    }

    /* Method that clicks Shop label in main menu */
    public void goMainView() {
        driver.findElement(IdentifierConstants.mainMenuShopLabel).click();
    }
}
