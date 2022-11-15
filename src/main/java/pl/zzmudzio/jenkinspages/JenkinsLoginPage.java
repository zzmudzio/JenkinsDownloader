package pl.zzmudzio.jenkinspages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.zzmudzio.config.WebDriversManager;

public class JenkinsLoginPage {
    private static final By jenkinsWelcomeMsgLocator = new By.ByXPath(".//h1[1]");
    private static final By userLoginFieldLocator = new By.ByXPath(".//div/input[1]");
    private static final By userPasswordFieldLocator = new By.ByXPath(".//input[@name=\"j_password\"]");
    private static final By submitBtnLocator = new By.ByXPath(".//input[@value=\"Zaloguj\"]");
    private static final By wrongDataMessageLocator = new By.ByXPath(".//div[@class='alert alert-danger' " +
            "and contains(text(), 'Niepoprawny użytkownik lub hasło')]");

    public static boolean logIntoJenkins(WebDriversManager myDriver, String... loginData) {
        return verifyPageLoadSuccess(myDriver.getDriverWait()) && enterUserLogin(loginData[0], myDriver)
                && enterUserPassword(loginData[1], myDriver) && submitLoginAndPassword(myDriver)
                && checkCredentialsCorrectness(myDriver.getDriverWait());
    }
    public static boolean verifyPageLoadSuccess(WebDriverWait myDriverWait) {
        try {
            myDriverWait.until(ExpectedConditions.visibilityOfElementLocated(jenkinsWelcomeMsgLocator));
            return true;
        }
        catch(TimeoutException te) {
            return false;
        }
    }
    public static boolean checkCredentialsCorrectness(WebDriverWait myDriverWait) {
        try {
            myDriverWait.until(ExpectedConditions.visibilityOfElementLocated(wrongDataMessageLocator));
            return false;
        }
        catch(TimeoutException te) {
            return true;  // if element located using
            // wrongDataMessageLocator won't appear, the login and password credentials were correct
        }
    }

    public static boolean enterUserLogin(String userLogin, WebDriversManager myDriver) {
        try {
            myDriver.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(userLoginFieldLocator));
            Actions enterLogin = new Actions(myDriver.getDriver());
            enterLogin
                    .moveToElement(myDriver.getDriver().findElement(userLoginFieldLocator))
                    .click()
                    .sendKeys(userLogin)
                    .build()
                    .perform();
            return true;
        }
        catch(TimeoutException te) {
            return false;
        }
    }

    public static boolean enterUserPassword(String userPassword, WebDriversManager myDriver) {
        try {
            myDriver.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(userPasswordFieldLocator));
            Actions enterPassword = new Actions(myDriver.getDriver());
            enterPassword
                    .moveToElement(myDriver.getDriver().findElement(userPasswordFieldLocator))
                    .click()
                    .sendKeys(userPassword)
                    .build()
                    .perform();
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
