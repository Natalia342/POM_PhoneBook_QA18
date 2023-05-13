package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;

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
    List<MobileElement> contacts;//список роав

    @FindBy(id = "com.sheygam.contactapp:id/rowContainer")
    MobileElement rov;

    @FindBy(id = "android:id/button1")
    MobileElement yesButton;

    @FindBy(id = "android:id/button2")
    MobileElement cancelButton;

    @FindBy(id="com.sheygam.contactapp:id/rowPhone")
    List<MobileElement> phoneList;

    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    List<MobileElement>nameList;

    @FindBy (id = "com.sheygam.contactapp:id/nameTxt")
    MobileElement upDateName;
    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    MobileElement text;

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
       if( isDisplayedWithException(plusButton))
            waitElement(plusButton, 5);
            plusButton.click();
     //   }
        return new AddNewContactScreen(driver);
    }
    public ContactlistScreen removeOneContact(int count){
        waitElement(plusButton,5);
        MobileElement contact = contacts.get(count);
        int beforesize= contacts.size();
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;// 1.8 shirini
        int xEnd = xStart + (rect.getWidth() * 6) / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?>touchAction = new TouchAction<>(driver);//? tipiza sama cozdali obekt
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd,y))//slayd vpravo
                .release() //otpyctit mouse
                .perform();//vipolni ves vishe nabor destvii

        waitElement(yesButton,5);
        yesButton.click();

        pause(1000);
        int aftersize = contacts.size();
        Assert.assertEquals(beforesize-aftersize,1);
        return this;

    }
    /*public ContactlistScreen isListSize(){
        int before= contacts.size();
        removeOneContact();
        int after=contacts.size();;
        //  MobileElement contact = contacts.get(0);

        Assert.assertEquals(before-after,1);
        return this;
    }*/
    public ContactlistScreen removeAllContact() {
        pause(1000);
        if( isDisplayedWithException(rov))

        while (driver.findElements(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")).size()>0){
            removeOneContact(0);

        }
        Assert.assertTrue(text.getText().equals("No Contacts. Add One more!"));
        return this;
    }


    public ContactlistScreen isContactAdded(Contact contact){
        boolean checkName = checkContainsText(nameList,
                contact.getName() + " " + contact.getLastName());
        boolean checkPhone = checkContainsText(phoneList, contact.getPhone());
        Assert.assertTrue(checkName && checkPhone);
        return this;
    }

    private boolean checkContainsText(List<MobileElement> list, String text) {
        for (MobileElement e : list){
            if (e.getText().contains(text)){
                return true;
            }
        }
        return false;
    }
    public EditContactScreen updateOneContact(){
        waitElement(plusButton, 3);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();

        int xEnd = rect.getX() + rect.getWidth() / 8;
        int xStart = xEnd + (rect.getWidth() * 6) / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();

        return new EditContactScreen(driver);
    }
    public ContactlistScreen isContactNameUpDate(Contact contact){
        rov.click();
        upDateName.click();
        boolean checkName = checkContainsText(nameList,contact.getName());

        Assert.assertTrue(checkName);
        return this;
    }

}


