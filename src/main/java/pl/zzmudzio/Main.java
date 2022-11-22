package pl.zzmudzio;

import pl.zzmudzio.jenkinspages.JenkinsJobPage;
import pl.zzmudzio.jenkinspages.JenkinsLoginPage;
import pl.zzmudzio.config.StatusColors;
import pl.zzmudzio.config.VpnConnection;
import pl.zzmudzio.config.WebDriversManager;
import pl.zzmudzio.jenkinspages.JenkinsMainPage;
import pl.zzmudzio.operations.AppFile;
import java.util.Scanner;


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
           args[3] = app that user want to download(jenkins job name)

           I am the only user of this app hence the below validation of args elements is not very sophisticated ;-)

         */
        while(args.length < 4) {
            System.out.println("Podaj dane logowania(login;haslo;adres;aplikacja): ");
            Scanner credentials = new Scanner(System.in);
            args = credentials.nextLine().split(";");
        }
        WebDriversManager myDriver = new WebDriversManager("chrome", 2);
        String artifactName = "unknown";
        if (VpnConnection.verifyPageResponse(args[2], myDriver)
                || !JenkinsLoginPage.logIntoJenkins(myDriver, args[0], args[1])
                || !JenkinsMainPage.goToDesiredCategory(myDriver)
                || ((artifactName = JenkinsJobPage.downloadLastArtifact(myDriver, args[3])) == null)) {
            System.out.println(StatusColors.ERROR.getAnsiCode() + "Błąd: " + StatusColors.RESET.getAnsiCode() +
                    "Nie udało się pobrać aplikacji: " + args[3] +". Sprawdź połączenie z VPN i poprawność danych " +
                    "autoryzacyjnych. Aplikacja zostanie zamknięta za 3 sekundy.");
        }
        else {
            System.out.println(StatusColors.SUCCESS.getAnsiCode() + "Sukces:" + StatusColors.RESET.getAnsiCode() +
                    " pomyślnie pobrano plik " + artifactName);

        }
        Thread.sleep(7000);
        myDriver.quitDriver();
        AppFile.unzipFile(artifactName, args[3]);
        AppFile.saveVersionInXml(artifactName);
        System.exit(0);
    }
}
