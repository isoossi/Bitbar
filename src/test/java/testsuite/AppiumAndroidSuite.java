package testsuite;

import applicationPages.AccountPage;
import applicationPages.CommonHelper;
import applicationPages.MainMenu;
import applicationPages.ProductPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mikko on 21.7.2017.
 * Add more related test in this suite, suite will be executed in testng.xml will so if more suites done make sure to
 * create new .xml file
 */
public class AppiumAndroidSuite{
    AppiumDriverLocalService service;
    AppiumDriver<MobileElement> driver;

    public AppiumAndroidSuite() throws Exception {
    }

    /* Method that will install package in device. If more devices are needed add only in testng.xml with correct parameters */
    @BeforeClass
    @Parameters({"UDID_"})
    public void setup(String UDID_) throws MalformedURLException {
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingAnyFreePort());
        service.start();
        String appiumServiceUrl = service.getUrl().toString();
        DesiredCapabilities capa = new DesiredCapabilities();
        capa.setCapability("platformName", "Android");
        capa.setCapability("deviceName", "Android");
        capa.setCapability("udid", UDID_);
        //Application apk will have to point where Jenkins builder makes .apk after commits
        capa.setCapability("app", System.getProperty("user.home") + "//Downloads//com.target.ui.apk");
        capa.setCapability("newCommandTimeout", "0");
        //Need to refactor this package and activity waits because think it will not work with older platform versions
        capa.setCapability("appWaitPackage", "com.google.android.packageinstaller");
        capa.setCapability("appWaitActivity", "com.android.packageinstaller.permission.ui.GrantPermissionsActivity");
        capa.setCapability("anyFreePort", 0);
        driver = new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), capa);
        CommonHelper commonHelper = new CommonHelper(driver);
        commonHelper.assertLocation();
    }

    @Test
    public void createAccount() throws InterruptedException, IOException {
        MainMenu mainpage = new MainMenu(driver);
        mainpage.openMainMenu();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.createAccount();
    }

    @Test
    public void searchItem() throws InterruptedException, IOException {
        MainMenu mainpage = new MainMenu(driver);
        mainpage.openMainMenu();
        mainpage.goMainView();
        ProductPage productPage = new ProductPage(driver);
        productPage.searchItem();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        service.stop();
    }

}
