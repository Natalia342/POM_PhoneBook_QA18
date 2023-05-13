package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EditContactScreen extends BaseScreen{
    public EditContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    MobileElement nameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    MobileElement lastNameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement emailEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    MobileElement phoneEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    MobileElement addressEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    MobileElement descriptionEditText;
    @FindBy(id = "com.sheygam.contactapp:id/updateBtn")
    MobileElement upDate;

    public ContactlistScreen submitEditContactForm(){
        upDate.click();
        return new ContactlistScreen(driver);
    }
    public EditContactScreen submitEditContactFormNegative(){
        upDate.click();
        return this;
    }
    public EditContactScreen upDateName(String text){
        waitElement(nameEditText,3);
        type(nameEditText,text);
        return this;
    }
    public EditContactScreen upDatelastName(String text){
        waitElement(nameEditText,3);
        type(lastNameEditText,text);
        return this;
    }
    public EditContactScreen upDateEmail(String text){
        waitElement(nameEditText,3);
        type(emailEditText,text);
        return this;
    }
    public EditContactScreen upDatePhone(String text){
        waitElement(nameEditText,3);
        type(phoneEditText,text);
        return this;
    }
    public EditContactScreen upDateAddress(String text){
        waitElement(nameEditText,3);
        type(addressEditText,text);
        return this;
    }
    public EditContactScreen upDateDescription(String text){
        waitElement(nameEditText,3);
        type(descriptionEditText,text);
        return this;
    }
    public  EditContactScreen isErrorMessageText(String text) {
        Alert alert = new WebDriverWait(driver,5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();//переключаемся на сообщение об алерте
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();//закрытие алерта
        return this;
    }
    public ContactlistScreen returnToContactList(){
        pause(2000);
        driver.navigate().back();
        return new ContactlistScreen(driver);
    }
}
