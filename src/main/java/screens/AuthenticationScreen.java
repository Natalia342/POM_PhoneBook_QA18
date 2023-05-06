package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Auth;
import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen{

    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id ='com.sheygam.contactapp:id/inputEmail']")
    MobileElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPassword")
    MobileElement passwordEditText;

    @FindBy(xpath = "//*[@text='LOGIN']")
    MobileElement loginButton;
    @FindBy (id="com.sheygam.contactapp:id/regBtn")
    MobileElement registrationButton;

    public AuthenticationScreen fillEmail(String email){
         waitElement(emailEditText, 5);
         type(emailEditText,email);
         return this;
    }
    public AuthenticationScreen fillPassword(String password){
        type(passwordEditText,password);
        return this;
    }
    public ContactlistScreen submitLogin(){
        loginButton.click();
        return new ContactlistScreen(driver);
    }
    public ContactlistScreen login(Auth auth){
        waitElement(emailEditText,5);
        type(emailEditText, auth.getEmail());
        type(passwordEditText,auth.getPassword());
        loginButton.click();
        return new ContactlistScreen(driver);
    }
    public ContactlistScreen registration(Auth auth){
        waitElement(emailEditText,5);
        type(emailEditText, auth.getEmail());
        type(passwordEditText,auth.getPassword());
        registrationButton.click();
        return new ContactlistScreen(driver);
    }
    public AuthenticationScreen clean(){
        emailEditText.clear();
        passwordEditText.clear();
        return new AuthenticationScreen(driver);
    }

}
