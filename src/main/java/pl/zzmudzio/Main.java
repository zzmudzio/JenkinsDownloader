package pl.zzmudzio;

import pl.zzmudzio.config.VpnConnection;
import pl.zzmudzio.config.WebDriversManager;

public class Main {
    public static void main(String[] args) {
        WebDriversManager myDriver = new WebDriversManager();
        if(VpnConnection.verifyPageContents("http://jenkins.currenda.pl", myDriver)) {
            System.out.println("Nie jesteś połączony z siecią VPN.");
            System.exit(1);
        }
        else {
            System.out.println("Jesteś połączony z VPN. ");
            JenkinsLoginPage.enterUserLogin("test", myDriver);
        }
        //myDriver.quitDriver();
    }
}
