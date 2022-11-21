package pl.zzmudzio;

import pl.zzmudzio.jenkinspages.JenkinsJobPage;
import pl.zzmudzio.jenkinspages.JenkinsLoginPage;
import pl.zzmudzio.config.StatusColors;
import pl.zzmudzio.config.VpnConnection;
import pl.zzmudzio.config.WebDriversManager;
import pl.zzmudzio.jenkinspages.JenkinsMainPage;
import pl.zzmudzio.operations.AppFile;


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

        String[] _args = new String[]{"","","", ""}; //temporary way to store credentials
        WebDriversManager myDriver = new WebDriversManager("chrome", 2);
        String artifactName = "unknown";
        if (VpnConnection.verifyPageResponse(_args[2], myDriver)
                || !JenkinsLoginPage.logIntoJenkins(myDriver, _args[0], _args[1])
                || !JenkinsMainPage.goToDesiredCategory(myDriver)
                || ((artifactName = JenkinsJobPage.downloadLastArtifact(myDriver, _args[3])) == null)) {
            System.out.println(StatusColors.ERROR.getAnsiCode() + "Błąd: " + StatusColors.RESET.getAnsiCode() +
                    "Nie udało się pobrać aplikacji: " + _args[3] +". Sprawdź połączenie z VPN i poprawność danych " +
                    "autoryzacyjnych. Aplikacja zostanie zamknięta za 3 sekundy.");
        }
        else {
            System.out.println(StatusColors.SUCCESS.getAnsiCode() + "Sukces:" + StatusColors.RESET.getAnsiCode() +
                    " pomyślnie pobrano plik " + artifactName);

        }
        Thread.sleep(7000);
        myDriver.quitDriver();
        //AppFile.unzipFile(artifactName);
        System.exit(0);
    }
}
