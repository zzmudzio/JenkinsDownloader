package pl.zzmudzio.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebDriversManager {
    private static final String ENV_VAR_CHROMEDRIVER_PATH = System.getenv("CHROMEDRIVER_PATH");
    //  ^ this environmental variable is responsible for storing path to chromedriver
    private WebDriver driver;
    private WebDriverWait driverWait;
    public WebDriversManager(String driverType, int waitDuration) {
        if(driverType.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ENV_VAR_CHROMEDRIVER_PATH);
            this.driver = new ChromeDriver();
            this.driverWait = new WebDriverWait(this.driver, Duration.ofSeconds(waitDuration));
        }
        else {
            System.out.println(StatusColors.ERROR.getAnsiCode() +"Błąd: " + StatusColors.RESET.getAnsiCode() +
                    "podano nieprawidłową nazwę sterownika.");
        }
    }

    public WebDriversManager() { //default data
        this("chrome", 5);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriverWait getDriverWait() {
        return this.driverWait;
    }

    public void quitDriver() {
        this.driver.quit();
    }
}
