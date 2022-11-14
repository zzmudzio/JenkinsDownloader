package pl.zzmudzio;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.zzmudzio.config.WebDriversManager;

public class JenkinsLoginPage {

    private static final By jenkinsWelcomeMsgLocator = new By.ByXPath(".//h1[1]");
    private static final By userLoginFieldLocator = new By.ByXPath(".//div/input[1]");
    private static final By userPasswordFieldLocator = new By.ByXPath(".//div/input[2]");
    private static final By submitBtnLocator = new By.ByXPath(".//div/input[3]");

    public static boolean verifyPageLoadSuccess(WebDriverWait myDriverWait) {
        try {
            myDriverWait.until(ExpectedConditions.visibilityOfElementLocated(jenkinsWelcomeMsgLocator));
            return true;
        }
        catch(TimeoutException te) {
            return false;
        }
    }

    public static boolean logIntoJenkins(WebDriversManager myDriver, String... loginData) {
        return verifyPageLoadSuccess(myDriver.getDriverWait()) && enterUserLogin(loginData[0], myDriver)
                && enterUserPassword(loginData[1], myDriver) && submitLoginAndPassword(myDriver);
    }

    public static boolean checkIfUserIsLoggedIn() {
        //...
        return false;
    }

    public static boolean enterUserLogin(String userLogin, WebDriversManager myDriver) {
        try {
            myDriver.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(userLoginFieldLocator));
            myDriver.getDriver().findElement(userLoginFieldLocator).sendKeys(userLogin);
            return true;
        }
        catch(TimeoutException te) {
            return false;
        }
    }

    public static boolean enterUserPassword(String userPassword, WebDriversManager myDriver) {
        try {
            myDriver.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(userPasswordFieldLocator));
            myDriver.getDriver().findElement(userPasswordFieldLocator).sendKeys(userPassword);
            return true;
        }
        catch(TimeoutException te) {
            return false;
        }
    }

    public static boolean submitLoginAndPassword(WebDriversManager myDriver) {
        try {
            myDriver.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(submitBtnLocator));
            myDriver.getDriver().findElement(submitBtnLocator).click();
            return true;
        }
        catch(TimeoutException te) {
            return false;
        }
    }

}
