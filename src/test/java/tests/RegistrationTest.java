package tests;

import config.AppiumConfig;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactlistScreen;
import screens.SplashScreen;

public class RegistrationTest extends AppiumConfig {

    @Test
    public void RegistrationSuccess() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        boolean res = new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .registration(Auth.builder()
                        .email("vasiatal" + i + "@gmail.com")
                        .password("Vasia$" + i + "34")
                        .build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);
    }

    @Test
    public void WrongEmailRegistration() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .registrationWrong(Auth.builder()
                        .email("vasiatal" + i + "gmail.com")
                        .password("Vasia$" + i + "34")
                        .build())
                .isErrorMessageText("Error")
                .clean();
    }

    @Test
    public void WrongPasswordRegistration() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .registrationWrong(Auth.builder()
                        .email("vasiatal" + i + "@gmail.com")
                        .password("Vasia" + i + "34")
                        .build())
                .isErrorMessageText("Error")
                .clean();
    }

    @AfterMethod
    public void postCondition() {
        new ContactlistScreen(driver).logout();
        new SplashScreen(driver);
    }
}
