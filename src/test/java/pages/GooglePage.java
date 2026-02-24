package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class GooglePage {

    WebDriver driver;

    By searchBox = By.name("q");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }
    

    public void open() {
        driver.get(ConfigReader.get("base.url"));
    }

    public void search(String text) {
        driver.findElement(searchBox).sendKeys(text);
        driver.findElement(searchBox).submit();
    }
}
