package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactlistScreen extends BaseScreen{
    public ContactlistScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityViewText;

    @FindBy(xpath = "//*[@content-desc = 'More options']")
    MobileElement moreOptions;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/title']")
    MobileElement logoutButton;

    @FindBy(xpath =  "//*[@resource-id = 'com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement plusButton;

    public boolean isContactListActivityPresent(){

        return shouldHave(activityViewText, "Contact list",10);
    }
    public AuthenticationScreen logout(){
        if(activityViewText.getText().equals("Contact list")) {
        moreOptions.click();//написано в BaseScreen shouldHawe можно пременить если не успевает отрисоваться
        logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }
}

