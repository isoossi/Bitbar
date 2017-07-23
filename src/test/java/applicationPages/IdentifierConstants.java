package applicationPages;

import org.openqa.selenium.By;

/**
 * Created by Mikko on 21.7.2017.
 * Class that holds application identifiers so that those are found in one place
 */
public class IdentifierConstants {

    public static final String apk = "com.target.ui";

    //Android common identifiers
    public static final By allowLocationButton = By.id("com.android.packageinstaller:id/permission_allow_button");

    //Mainmenu UI identifiers
    public static final String mainMenuButton = "Close navigation drawer";
    public static final By signInButton = By.id("com.target.ui:id/navigation_header_text");
    public static final By mainMenuShopLabel = By.id("com.target.ui:id/shop_label");

    //Account UI identifiers
    public static final By signInHeaderText = By.id("com.target.ui:id/account_login_header_text");
    public static final String createAccountTap = "create account, tab 2 of 2";
    public static final By emailTextField = By.id("com.target.ui:id/login_account");
    public static final By nameTextField = By.id("com.target.ui:id/name_first_input");
    public static final By lastNameTextField = By.id("com.target.ui:id/name_last_input");
    public static final By passwordTextField = By.id("com.target.ui:id/login_password");
    public static final By createAccountButton = By.id("com.target.ui:id/account_create_button");
    public static final By fingerPrintStatus = By.id("com.target.ui:id/fingerprint_request_cancel");

    //Main view identifiers
    public static final By mainViewSearch = By.id("com.target.ui:id/action_search");
    public static final By mainViewSearchTextField = By.id("com.target.ui:id/search_field");
    public static final By mainViewSearchGotItButton = By.id("com.target.ui:id/ok_button");

    //Products view identifiers
    public static final By productsListItemTitle = By.id("com.target.ui:id/plp_v2_title");
    public static final By productsItemHeaderTitle = By.id("com.target.ui:id/pdp_v2_map_header_title");
    public static final By productsItemHeaderSubtitle = By.id("com.target.ui:id/pdp_v2_map_header_subtitle");
    public static final String productsItemImage = "Product image 1 of 1";
    public static final By productsListItemText = By.id("com.target.ui:id/pdp_v2_title_text");
    public static final By productsListItemPrice = By.id("com.target.ui:id/pdp_v2_current_price");

    public static final By productsItemDeliveryTitle = By.id("com.target.ui:id/pdp_v2_fulfillment_display_text");
    public static final By productsItemDeliveryStatus = By.id("com.target.ui:id/pdp_v2_fulfillment_inventory_status");
    public static final By productsItemAllDetailsButton = By.id("com.target.ui:id/pdp_v2_product_description_read_all_details_button");
    public static final By productsItemAllDetailsText = By.id("com.target.ui:id/pdp_v2_product_description_details_text");
}
