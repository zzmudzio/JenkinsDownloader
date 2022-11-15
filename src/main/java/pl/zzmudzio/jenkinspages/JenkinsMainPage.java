package pl.zzmudzio.jenkinspages;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.zzmudzio.config.StatusColors;
import pl.zzmudzio.config.WebDriversManager;

public class JenkinsMainPage {
    private final static By desiredAppCategoryLocator = new By.ByXPath(".//div[@id='projectstatus-tabBar']" +
            "/div/div/div[5]/a[text()='SAWA']");
    // user's choice will be appended to @id='job_.."

    public static boolean goToDesiredCategory(WebDriversManager myDriver) {
        try {
            myDriver.getDriverWait().until(ExpectedConditions.elementToBeClickable(desiredAppCategoryLocator));
            myDriver.getDriver().findElement(desiredAppCategoryLocator).click();
            return true;
        }
        catch(TimeoutException | NotFoundException tenfe) {
            return false;
        }
    }

    public static WebElement findAppByName(WebDriversManager myDriver, String appName) {
        try {
            //cannot be final because the value will change depending on the user choice;
            By applicationLocator = new By.ByXPath(".//table[@id='projectstatus']" +
                    "/tbody/tr[@id='job_" + appName + "']/td[3]/a");
            myDriver.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(applicationLocator));
            return myDriver.getDriver().findElement(applicationLocator);
        }
        catch(TimeoutException | NotFoundException tenfe) {
            return null;
        }
    }

    public static boolean goToAppPage(WebDriversManager myDriver, String appName) {
        WebElement app;
        if((app = findAppByName(myDriver, appName)) == null)  {
            System.out.println(StatusColors.ERROR.getAnsiCode() + "Błąd: " + StatusColors.RESET.getAnsiCode() +
                    "Nie udało się odnaleźć podanej aplikacji: " + appName);
            return false;
        }
        app.click();
        return true;
    }
}
