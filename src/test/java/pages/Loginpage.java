package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import driver.DriverFactory;



public class Loginpage extends DriverFactory{

	WebDriver driver=getDriver();
			

    By myAccount = By.xpath("//span[text()='My Account']");
    By loginOption = By.linkText("Login");
    By emailField = By.id("input-email");
    By passwordField = By.id("input-password");
    By loginBtn = By.xpath("//input[@value='Login']");
    By accountHeader = By.xpath("//h2[text()='My Account']");

    public void navigateToLogin() {
    	
        driver.findElement(myAccount).click();
        driver.findElement(loginOption).click();
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isLoginSuccessful() {
        return driver.findElements(accountHeader).size() > 0;
    }
    
}
