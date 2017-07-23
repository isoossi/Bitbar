package applicationPages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.Dimension;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Mikko on 22.7.2017.
 */
public class ProductPage extends CommonHelper {

    protected final AppiumDriver driver;

    public ProductPage(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }

    /* Method that searches specific item and if found verify details, if not found case will fail */
    public void searchItem() {
        String searchItem = "Hello Gorgeous Mug";
        waitForElementVisibility(IdentifierConstants.mainViewSearch, 5);
        driver.findElement(IdentifierConstants.mainViewSearch).click();
        waitForElementVisibility(IdentifierConstants.mainViewSearchTextField, 5);
        driver.findElement(IdentifierConstants.mainViewSearchTextField).sendKeys(searchItem);
        //Using keycodes not working, probably listener issue in app side and no UI button to "finish" search!!
        ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
        if(!isElementVisible(IdentifierConstants.mainViewSearchGotItButton, 3)){
            Dimension size = driver.manage().window().getSize();
            int starty = size.height - 10;
            int startx = size.width - 10;
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(startx, starty).release().perform();
        }
        //If doing first search need to push Got it
        if(isElementVisible(IdentifierConstants.mainViewSearchGotItButton, 5)) {
            driver.findElement(IdentifierConstants.mainViewSearchGotItButton).click();
        }
        //If item is found, click it and verify details
        if(isElementVisible(IdentifierConstants.productsListItemTitle, 5)){
            List<MobileElement> allItems = driver.findElements(IdentifierConstants.productsListItemTitle);
            allItems.get(0).click();
            verifyItemDetails();
        } else {
            System.out.println("No items found with search criteria: " + searchItem);
            fail("No items found");
        }
    }

    /* Method that verify details from found item */
    private void verifyItemDetails() {
        waitForElementVisibility(IdentifierConstants.productsItemHeaderTitle, 5);
        String locationTitleText = driver.findElement(IdentifierConstants.productsItemHeaderTitle).getText();
        String locationSubtitleText = driver.findElement(IdentifierConstants.productsItemHeaderSubtitle).getText();
        assertTrue(locationTitleText.contains("Located in"));
        assertTrue(locationSubtitleText.contains("View a map of"));
        assertTrue(driver.findElementByAccessibilityId(IdentifierConstants.productsItemImage).isDisplayed());
        assertEquals(driver.findElement(IdentifierConstants.productsListItemText).getText(),
                "Clay Art Stackable Mug 15oz Porcelain - \"HelloGorgeous\"");
        assertEquals(driver.findElement(IdentifierConstants.productsListItemPrice).getText(),
                "$5.99");
        swipeDown();
        //Getting headers from item fulfillment and asserting texts
        waitForElementVisibility(IdentifierConstants.productsItemDeliveryTitle, 5);
        List<MobileElement> itemFulfillHeader = driver.findElements(IdentifierConstants.productsItemDeliveryTitle);
        ArrayList<String> headers = new ArrayList<>();
        for(MobileElement ele : itemFulfillHeader) {
            String headerText = ele.getText();
            headers.add(headerText);
            sleep(500);
        }
        assertEquals(headers.get(0), "delivery");
        assertEquals(headers.get(1), "order pickup");
        assertEquals(headers.get(2), "store availability");

        //Getting text from item fulfillment and asserting text
        //Probably needs some smarter solution for text if items not in stock etc.
        List<MobileElement> itemFulfillStatus = driver.findElements(IdentifierConstants.productsItemDeliveryStatus);
        ArrayList<String> status = new ArrayList<>();
        for(MobileElement ele : itemFulfillStatus) {
            String statusText = ele.getText();
            status.add(statusText);
            sleep(500);
        }
        assertEquals(status.get(0), "in stock");
        String today = status.get(1);
        assertTrue(today.contains("today at"));
        String stock = status.get(2);
        assertTrue(stock.contains("in stock at"));

        //Getting item details and asserting text
        swipeDown();
        waitForElementVisibility(IdentifierConstants.productsItemAllDetailsButton, 5);
        driver.findElement(IdentifierConstants.productsItemAllDetailsButton).click();
        waitForElementVisibility(IdentifierConstants.productsItemAllDetailsText, 5);
        assertEquals(driver.findElement(IdentifierConstants.productsItemAllDetailsText).getText(),
                "Have your hot beverage in style with this stackable stoneware mug. Handcrafted and available in many colors and phrases, there is a style for every personality. Dishwasher and microwave safe.\n" +
                        "\n" +
                        "Material:\n" +
                        "Porcelain\n" +
                        "\n" +
                        "Drink Type Held:\n" +
                        "coffee\n" +
                        "\n" +
                        "Beverage Type Held:\n" +
                        "hot beverages\n" +
                        "\n" +
                        "Care and Cleaning:\n" +
                        "Microwave Safe\n" +
                        "\n" +
                        "Service for:\n" +
                        "1\n" +
                        "\n" +
                        "Dimensions:\n" +
                        "3.88 inches H x 3.52 inches W\n" +
                        "\n" +
                        "Capacity (volume):\n" +
                        "15 ounces\n" +
                        "\n" +
                        "Origin:\n" +
                        "Imported\n" +
                        "\n" +
                        "Additional Information\n" +
                        "TCIN: 51093178\n" +
                        "UPC: 657284736141\n" +
                        "Store Item Number(DPCI): 200-01-4160");
    }
}
