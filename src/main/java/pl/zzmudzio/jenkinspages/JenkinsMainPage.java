package pl.zzmudzio.jenkinspages;

import org.openqa.selenium.By;

public class JenkinsMainPage {
    private final static By desiredAppCategoryLocator = new By.ByXPath(".//div[@id='projectstatus-tabBar']" +
            "/div/div/div[5]/a[text()='SAWA']");

}
