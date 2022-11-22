package pl.zzmudzio.operations;

import pl.zzmudzio.config.StatusColors;

import java.io.IOException;

public class AppFile {

    /*
       Here I am using a 7zip to unzip a file, so 7zip has to be added into PATH variable.
       I assume as well that the download path is user.home\Downloads, i.e.
       C:\Users\<userName>\Downloads
    */

    private static final String DOWNLOAD_PATH = System.getProperty("user.home")+"\\Downloads\\";
    private static final String UNZIP_PATH = "C:\\Currenda\\";
    public static int unzipFile(String artifactName, String jenkinsJobName) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c","7z x " + DOWNLOAD_PATH + "\\" +
                artifactName + " -o"+UNZIP_PATH+jenkinsJobName+"\\ -y");
        int processResult = 0;
        try {
            Process myProcess = builder.start();
            myProcess.waitFor();
            processResult = myProcess.exitValue();
            System.out.println();
        }
        catch(IOException | InterruptedException ie) {
            System.out.println(StatusColors.ERROR.getAnsiCode() + "Błąd: " + StatusColors.RESET.getAnsiCode()
                    + " wystąpił błąd podczas próby uruchomienia procesu cmd.exe");
        }
        return processResult; // 0 = OK, != 0 not OK
    }
    public static boolean saveVersionInXml(String artifactName) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c","java -cp " +
                "C:\\Users\\m.zmuda-trzebia\\IdeaProjects\\SawaApps\\target" +
                "\\SawaApps-jar-with-dependencies.jar pl.versions.Main " + artifactName);
        int processResult = 0;
        try {
            Process myProcess = builder.start();
            myProcess.waitFor();
            processResult = myProcess.exitValue();
            return true;
        }
        catch(IOException | InterruptedException ie) {
            System.out.println(StatusColors.ERROR.getAnsiCode() + "Błąd: " + StatusColors.RESET.getAnsiCode()
                    + " wystąpił błąd podczas próby uruchomienia procesu cmd.exe");
            return false;
        }
    }
}
