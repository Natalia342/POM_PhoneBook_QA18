package tests;

import config.AppiumConfig;
import models.Auth;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactlistScreen;

public class UpDateContactTests extends AppiumConfig {
    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .login(Auth.builder()
                        .email("natanaym@mail.ru")
                        .password("$$Nn2412")
                        .build());
    }
    @Test
    public void updateOneContactName(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDateName("Update" + i)
                .submitEditContactForm();//no Assert
     //            .isContactNameUpDate(contact) ;

    }
    @Test
    public void updateLastName(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDatelastName("Valera" + i)
                .submitEditContactForm();//no Assert

    }
    @Test
    public void updateEmail(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDateEmail("Valera" + i +"@gmail.com")
                .submitEditContactForm();//no Assert

    }
    @Test
    public void updateEmailNegative(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDateEmail("Masha" + i +"gmail.com")
                .submitEditContactFormNegative()
                .isErrorMessageText("Error")
                .returnToContactList();

    }
    @Test
    public void updatePhone(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDatePhone("85964327" + i)
                .submitEditContactForm();//no Assert

    }
    @Test
    public void updatePhoneNegative(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDatePhone("8596" + i)
                .submitEditContactFormNegative()
                .isErrorMessageText("Error")
                .returnToContactList();

    }
    @Test
    public void updateAddress(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDateAddress("Toronto" + i)
                .submitEditContactForm();//no Assert

    }
    @Test
    public void updateDescription(){

        new ContactlistScreen(driver)
                .updateOneContact()
                .upDateDescription("Friend")
                .submitEditContactForm();//no Assert

    }
    @AfterClass
    public void postCondition(){
             new ContactlistScreen(driver).logout();
    }
}
