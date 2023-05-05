package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.ContactlistScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {
    @Test
    public void loginSuccess(){

       boolean res = new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("vasiatal@gmail.com")
                .fillPassword("Vasia$1234")
                .submitLogin()
                .isContactListActivityPresent();
        Assert.assertTrue(res);
    }
    @Test
    public void loginSuccessModel(){
        boolean res = new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .login(Auth.builder()
                        .email("vasiatal@gmail.com")
                        .password("Vasia$1234")
                        .build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);
    }

    @AfterMethod
    public void postCondition(){
        new ContactlistScreen(driver).logout();
        new SplashScreen(driver);
    }
}
