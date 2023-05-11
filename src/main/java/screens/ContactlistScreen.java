package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.BreakIterator;
import java.util.List;

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

    @FindBy(id = "com.sheygam.contactapp:id/rowContainer")
    List<MobileElement> contacts;

    @FindBy(id = "android:id/button1")
    MobileElement yesButton;

    @FindBy(id = "android:id/button2")
    MobileElement cancelButton;

    @FindBy(id="com.sheygam.contactapp:id/rowPhone")
    List<MobileElement> phoneList;

    public boolean isContactListActivityPresent(){

        return shouldHave(activityViewText, "Contact list",10);
    }
    public AuthenticationScreen logout(){
        if(isDisplayedWithException(moreOptions))
        //if(activityViewText.getText().equals("Contact list"))
        {
        moreOptions.click();//написано в BaseScreen shouldHawe можно пременить если не успевает отрисоваться
        logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }
    public ContactlistScreen assertContactListActivityPresent(){
        Assert.assertTrue(isContactListActivityPresent());
        return this;
    }

    public AddNewContactScreen openContactForm() {
     //   if(activityViewText.getText().equals("Contact list")) {
            waitElement(plusButton, 5);
            plusButton.click();
     //   }
        return new AddNewContactScreen(driver);
    }
    public ContactlistScreen removeOneContact(){
        waitElement(plusButton,5);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;// 1.8 shirini
        int xEnd = xStart + (rect.getWidth() * 6) / 8;
        int y = rect.getY() + rect.getHeight() / 2;
        return this;

    }


    }


