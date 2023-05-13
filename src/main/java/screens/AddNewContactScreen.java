package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddNewContactScreen extends BaseScreen{
    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
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
    @FindBy(id = "com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    public AddNewContactScreen fillContactForm(Contact contact){
        waitElement(nameEditText,5);
        type(nameEditText, contact.getName());
        type(lastNameEditText, contact.getLastName());
        type(emailEditText, contact.email);
        type(phoneEditText, contact.getPhone());
        type(addressEditText, contact.getAddress());
        type(descriptionEditText, contact.getDescription());
        return this;
    }
    public ContactlistScreen submitContactForm(){
        createButton.click();
        return new ContactlistScreen(driver);
    }
    public AddNewContactScreen cleanline(){
        nameEditText.clear();
        lastNameEditText.clear();
        emailEditText.clear();
        phoneEditText.clear();
        addressEditText.clear();
        descriptionEditText.clear();
        return this;
    }

    public AddNewContactScreen submitContactFormNegative() {
        createButton.click();
        return this;
    }
    public AddNewContactScreen isErrorMessageAddedContact(String text) {
        Alert alert = new WebDriverWait(driver,5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();//переключаемся на сообщение об алерте
        Assert.assertTrue(alert.getText().contains(text));
        pause(5);
        // clickAlert.click();
        alert.accept();//закрытие алерта
        pause(5);
        driver.navigate().back();
        return this;
    }

}

