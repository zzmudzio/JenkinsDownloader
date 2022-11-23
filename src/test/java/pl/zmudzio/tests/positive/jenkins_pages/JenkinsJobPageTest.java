package pl.zmudzio.tests.positive.jenkins_pages;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.zzmudzio.config.VpnConnection;
import pl.zzmudzio.config.WebDriversManager;
import pl.zzmudzio.jenkinspages.JenkinsJobPage;
import pl.zzmudzio.jenkinspages.JenkinsLoginPage;
import pl.zzmudzio.jenkinspages.JenkinsMainPage;

public class JenkinsJobPageTest {
    private static WebDriversManager myDriver;

    @BeforeClass
    public static void initializeDriver() {
        myDriver = new WebDriversManager("chrome", 5);
    }

    @Test(priority=0)
    @Parameters({"login", "password", "pageAddress"})
    public static void verifyArtifactLocator(String login, String password, String pageAddress) {
        VpnConnection.verifyPageResponse(pageAddress, myDriver);
        JenkinsLoginPage.logIntoJenkins(myDriver, login, password, pageAddress);
        JenkinsMainPage.goToDesiredCategory(myDriver);
        JenkinsMainPage.findAppByName(myDriver, "Wpen");
        System.out.println("Weryfikacja czy nazwa namierzonego artefaktu zawiera wymagane słowo.");
        Assert.assertTrue((JenkinsJobPage.downloadLastArtifact(myDriver, "Wpen")).contains("Wpen"));
    }

    @AfterClass
    public static void quitDriver() {
        myDriver.quitDriver();
    }
}
