package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactlistScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthenticationScreen(driver)
                .login(Auth.builder()
                        .email("vasiatal@gmail.com")
                        .password("Vasia$1234")
                        .build());
    }
    @Test
    public void addNewContactPositive(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Add_"+ i)
                .lastName("Positive")
                .email("add_"+ i + "@gmail.com")
                .phone("18549879"+i)
                .address("Haifa")
                .description("New Contact_" + i)
                .build();


        new ContactlistScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAdded(contact)
                ;
    }
    @Test(invocationCount = 2)
    public void addNewContactMustPositive(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Must_"+ i)
                .lastName("Positive")
                .email("Must_"+ i + "@gmail.com")
                .phone("18855799"+i)
                .address("Tel Aviv")

                .build();


        new ContactlistScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                ;
    }
    @Test
    public void addNewContactNegativeEmptyAddress(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("EmptyAddress"+ i)
                .lastName("Positive")
                .email("Must_"+ i + "@gmail.com")
                .phone("18855799"+i)

                .description("friends")
                .build();

        new ContactlistScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageAddedContact("Error");
        //        .cleanline();

    }
    @Test
    public void addNewContactNegativeEmptyName(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()

                .lastName("EmptyName")
                .email("EmptyName_"+ i + "@gmail.com")
                .phone("18855799"+i)
                .address("Kiev")
                .description("friends")
                .build();

        new ContactlistScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageAddedContact("Error");
         //       .cleanline();


    }
    @AfterClass
    public void postCondition() {
        new ContactlistScreen(driver).logout();
        new SplashScreen(driver);
    }
}
