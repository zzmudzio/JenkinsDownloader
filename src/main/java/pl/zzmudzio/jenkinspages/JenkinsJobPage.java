package pl.zzmudzio.jenkinspages;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.zzmudzio.config.WebDriversManager;

public class JenkinsJobPage {
    private static final By lastArtifactLocator = new By.ByXPath(".//table[@class='fileList']//td[2]/a[1]");
    public static String downloadLastArtifact(WebDriversManager myDriver, String appName) {
        if (JenkinsMainPage.goToAppPage(myDriver, appName)) {
            try {
                myDriver.getDriverWait().until(ExpectedConditions.elementToBeClickable(lastArtifactLocator));
                myDriver.getDriver().findElement(lastArtifactLocator).click();
                return myDriver.getDriver().findElement(lastArtifactLocator).getText();
            } catch (TimeoutException | NotFoundException tenfe) {
                return null;
            }
        }
        return null;
    }
}
