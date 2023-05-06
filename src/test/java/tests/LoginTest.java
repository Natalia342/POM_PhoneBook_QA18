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
                .isContactListActivityPresent();//method s boolean znacheni b ctp 15 poetomy peremenay boolean
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
    @Test
    public void LoginWrongEmail(){
                new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .loginWrong(Auth.builder()
                        .email("vasiatalgmail.com")
                        .password("Vasia$1234")
                        .build())
                        .isErrorMessageText("Error")
                        .clean();
     //          .isContactListActivityPresent();


    }
    @Test
    public void LoginWrongPassword(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .loginWrong(Auth.builder()
                        .email("vasiatal@gmail.com")
                        .password("Vasi1234")
                        .build())
                .isErrorMessageText("Error")
                .clean();
        //          .isContactListActivityPresent();


    }

    @AfterMethod
    public void postCondition(){
        new ContactlistScreen(driver).logout();
        new SplashScreen(driver);
    }
}
