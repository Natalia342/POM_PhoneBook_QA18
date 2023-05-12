package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactlistScreen;

public class RemoveContactsTests extends AppiumConfig {

    @BeforeClass
    public void precondition(){
        new AuthenticationScreen(driver)
                .login(Auth.builder()
                        .email("vasiatal@gmail.com")
                        .password("Vasia$1234")
                        .build());
    }
    @Test
    public void removeOneContactPositive(){
        new ContactlistScreen(driver)
                .removeOneContact(0);//Assert внутри
              //  .isListSize()


    }
    @Test
    public void removeAllContactPositive(){
        new ContactlistScreen(driver)
                .removeAllContact();

    }
    @AfterClass
    public void postCondition(){
  //      new ContactlistScreen(driver).logout();
    }

}
