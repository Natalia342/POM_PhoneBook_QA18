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
                .submitEditContactForm();

    }
    @AfterClass
    public void postCondition(){
        //      new ContactlistScreen(driver).logout();
    }
}
