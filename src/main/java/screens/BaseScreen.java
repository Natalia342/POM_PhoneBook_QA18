package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseScreen {

    AppiumDriver<MobileElement>driver;

    public BaseScreen (AppiumDriver<MobileElement>driver){
        this.driver = driver;//constryctor
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    public void type(MobileElement element,String text){
        if (text == null) return;
        element.click();
        waitElement(element, 5);////////////падал логин тестс
        element.clear();
        waitElement(element, 5);////////////падал логин тестс
        element.sendKeys(text);
        driver.hideKeyboard();
    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void waitElement(MobileElement element, int time){
        new WebDriverWait(driver, time).until(
                ExpectedConditions.visibilityOf(element)
        );
}
    public boolean shouldHave(MobileElement element, String text, int time){
        return new WebDriverWait(driver, time)
                .until(ExpectedConditions.textToBePresentInElement(element, text));

    }

public boolean isDisplayedWithException(MobileElement element){
        try{
        waitElement(element, 5);
        return element.isDisplayed();
} catch (Exception e){
            return false;
        }

    }



 /*   public boolean isErrorMessageText(){
        Alert alert = new WebDriverWait(driver,10)
                .until(ExpectedConditions.alertIsPresent());
        return alert.getText().contains("Error");
    }*/

}
