package pl.zzmudzio.config;

/*
I am allowed to connect to jenkins only if I'm connected to certain VPN network,
this class helps to check whether the connection is established before trying to perform any actions.
It doesn't verify any network settings, just the message that is being received when trying to connect
to jenkins.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VpnConnection {
    private static final By pageNotFoundMessageLocator =
            new By.ByXPath(".//div//h1[contains(text(), '404')]");

    public static boolean verifyPageResponse(String pageUrl, WebDriversManager myDriver) {
        myDriver.getDriver().get(pageUrl);
        myDriver.getDriver().manage().window().maximize();
        try {
            myDriver.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(pageNotFoundMessageLocator));
            return true;
        }
        catch(TimeoutException te) {
            return false;
        }
    }
}
