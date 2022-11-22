package pl.zmudzio.tests.positive.config;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.zzmudzio.config.VpnConnection;
import pl.zzmudzio.config.WebDriversManager;

public class VpnConnectionTest {
    private WebDriversManager myDriver;
    @BeforeClass
    public void initializeDriver() {
        System.out.println("Inicjalizuje drivera.");
        this.myDriver = new WebDriversManager("chrome", 5); //first argument denotes driver name and second is duration for driverWait
    }
    @Test(priority=0)
    public void checkPageResponseToNoVpnConnection() {
        System.out.println("Weryfikacja odpowiedzi serwisu w przypadku braku polaczenia z VPN.");
        Assert.assertTrue(VpnConnection.verifyPageResponse("http://jenkins.currenda.pl", myDriver));
        //above method returns true if there is no vpn connection
    }
    @Test(priority=1, enabled=false)
    public void checkPageResponseToVpnConnection() {
        System.out.println("Weryfikacja odpowiedzi serwisu w przypadku polaczenia z VPN. ");
        Assert.assertFalse(VpnConnection.verifyPageResponse("http://jenkins.currenda.pl", myDriver));
        //above method returns false if there is a vpn connection
    }
    @AfterClass
    public void closeDriver() {
        this.myDriver.quitDriver();
    }
}
