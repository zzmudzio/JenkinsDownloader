package pl.zzmudzio;

import pl.zzmudzio.jenkinspages.JenkinsLoginPage;
import pl.zzmudzio.config.StatusColors;
import pl.zzmudzio.config.VpnConnection;
import pl.zzmudzio.config.WebDriversManager;

/*
Jenkins page address, credentials such as user login, password and desired app will be
received using application arguments.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        /*
           args[0] = login,
           args[1] = password
           args[2] = jenkins page address
           args[3] = app that user want to download
         */

        String[] _args = new String[3]; //temporary way to store credentials
        _args[0] = "";
        _args[1] = "";
        _args[2] = "";

        WebDriversManager myDriver = new WebDriversManager();
        if (VpnConnection.verifyPageResponse(_args[2], myDriver)
                || !JenkinsLoginPage.logIntoJenkins(myDriver, _args[0], _args[1])) {
            System.out.println(StatusColors.ERROR.getAnsiCode() + "Błąd: " + StatusColors.RESET.getAnsiCode() +
                    "Nie udało się połączyć z aplikacją. Sprawdź połączenie z VPN i poprawność danych " +
                    "autoryzacyjnych. Aplikacja zostanie zamknięta za 3 sekundy.");
            Thread.sleep(3000);
            myDriver.quitDriver();
            System.exit(1);
        }
    }
}
