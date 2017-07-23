package applicationPages;

import io.appium.java_client.AppiumDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Mikko on 22.7.2017.
 */
public class AccountPage extends CommonHelper {

    protected final AppiumDriver driver;
    //Account details for later use if needed
    protected String userEmail = null;
    protected String firstName = null;
    protected String lastName = null;
    protected String userPassword = null;

    public AccountPage(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }

    /* Method creates account */
    public void createAccount() {
        firstName = generateRandomString(5,"abcdefghijklmnopqrstuvwxyz");
        lastName = generateRandomString(5,"abcdefghijklmnopqrstuvwxyz");
        userEmail = firstName + "." + lastName + "@mailinator.com";
        userPassword = generateRandomString(10,"abcdefgh1234567890");

        waitForElementVisibility(IdentifierConstants.signInButton, 5);
        driver.findElement(IdentifierConstants.signInButton).click();
        waitForElementVisibility(IdentifierConstants.signInHeaderText, 5);
        assertTrue(driver.findElementByAccessibilityId(IdentifierConstants.createAccountTap).isDisplayed());
        driver.findElementByAccessibilityId(IdentifierConstants.createAccountTap).click();

        //Verify email fields and create email address
        waitForElementVisibility(IdentifierConstants.emailTextField, 5);
        assertEquals(driver.findElement(IdentifierConstants.emailTextField).getText(), "email address");
        driver.findElement(IdentifierConstants.emailTextField).click();
        driver.findElement(IdentifierConstants.emailTextField).sendKeys(userEmail);
        driver.hideKeyboard();

        //Verify name field and create name
        waitForElementVisibility(IdentifierConstants.nameTextField, 5);
        assertEquals(driver.findElement(IdentifierConstants.nameTextField).getText(), "name");
        driver.findElement(IdentifierConstants.nameTextField).click();
        driver.findElement(IdentifierConstants.nameTextField).sendKeys(firstName);
        waitForElementVisibility(IdentifierConstants.lastNameTextField, 5);
        assertEquals(driver.findElement(IdentifierConstants.lastNameTextField).getText(), "last name");
        driver.findElement(IdentifierConstants.lastNameTextField).click();
        driver.findElement(IdentifierConstants.lastNameTextField).sendKeys(lastName);
        driver.hideKeyboard();

        //Verify password field and create password
        waitForElementVisibility(IdentifierConstants.passwordTextField, 5);
        assertEquals(driver.findElement(IdentifierConstants.passwordTextField).getText(), "password");
        driver.findElement(IdentifierConstants.passwordTextField).click();
        driver.findElement(IdentifierConstants.passwordTextField).sendKeys(userPassword);
        driver.hideKeyboard();

        //Press create account button
        swipeDown();
        waitForElementVisibility(IdentifierConstants.createAccountButton, 5);
        driver.findElement(IdentifierConstants.createAccountButton).click();
        //If some devices uses fingerprint so it won't fail here
        if(isElementVisible(IdentifierConstants.fingerPrintStatus, 5)) {
            driver.findElement(IdentifierConstants.fingerPrintStatus).click();
        }
    }
}
