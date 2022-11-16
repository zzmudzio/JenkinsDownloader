package pl.zzmudzio.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriversManager {
    private static final String ENV_VAR_CHROMEDRIVER_PATH = System.getenv("CHROMEDRIVER_PATH");
    //  ^ this environmental variable is responsible for storing path to chromedriver
    private WebDriver driver;
    private WebDriverWait driverWait;
    public WebDriversManager(String driverType, int waitDuration) {
        if(driverType.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ENV_VAR_CHROMEDRIVER_PATH);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", "C:\\Users\\m.zmuda-trzebia\\Desktop");
            prefs.put("download.prompt_for_download", false);
            prefs.put("download.extensions_to_open", "application/xml");
            prefs.put("safebrowsing.enabled", true);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("start-maximized");
            options.addArguments("--safebrowsing-disable-download-protection");
            options.addArguments("safebrowsing-disable-extension-blacklist");
            this.driver = new ChromeDriver(options);
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
