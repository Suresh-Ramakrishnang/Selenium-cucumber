package driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class DriverFactory {

    // ThreadLocal for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Initialize driver
    public static void initDriver() {

        String browser = ConfigReader.getProperty("browser");
        String headless = ConfigReader.getProperty("headless");

        if (browser == null) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                if ("true".equalsIgnoreCase(headless)) {
                    chromeOptions.addArguments("--headless=new");
                }

                chromeOptions.addArguments("--start-maximized");
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "edge":

                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();

                if ("true".equalsIgnoreCase(headless)) {
                    edgeOptions.addArguments("--headless=new");
                }

                driver.set(new EdgeDriver(edgeOptions));
                driver.get().manage().window().maximize();
                break;

            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Get driver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit driver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Important for parallel cleanup
        }
    }
}