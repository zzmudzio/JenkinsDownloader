package pl.zzmudzio.operations;

import java.io.IOException;

public class AppFile {

    /*
       Here I am using a 7zip to unzip a file, so 7zip has to be added into PATH variable.
       I assume as well that the download path is user.home\Downloads, i.e.
       C:\Users\<userName>\Downloads
    */

    private static final String DOWNLOAD_PATH = System.getProperty("user.home")+"\\Downloads\\";
    public static int unzipFile(String fileName, String unzipPath) {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c","7z x " + DOWNLOAD_PATH + "\\" +
                fileName + " -o"+DOWNLOAD_PATH+"\\test\\ -y");
        int processResult = 0;
        try {
            Process myProcess = builder.start();
            myProcess.waitFor();
            processResult = myProcess.exitValue();
        }
        catch(IOException | InterruptedException ie) {
            System.out.println("Błąd: wystąpił błąd podczas próby uruchomienia procesu cmd.exe");
        }
        return processResult; // 0 = OK, != 0 not OK
    }
}
