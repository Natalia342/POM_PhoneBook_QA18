package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen{
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    MobileElement versionTextView;//pole b kotoroe bydet pomechena ccilka na element esli on bydet naden

    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public String getVersion(){
        return versionTextView.getText();
    }

    public AuthenticationScreen gotoAuthenticationScreen(){
        return new AuthenticationScreen(driver);
    }
}
