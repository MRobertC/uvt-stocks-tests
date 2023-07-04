package com.stocks.pageDefinitions;

import com.stocks.ChromeWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private final ChromeWebDriver driver;
    private final By loginButton = By.id("login-button");
    private final By registrationLink = By.id("create-account-button");
    private final By emailField = By.id("login-email-input");
    private final By passwordField = By.id("login-password-input");
    private final By missingValueErrorMessage = By.xpath("//mat-error[contains(text(),'You must enter a value')]");
    private final By emailFormatErrorMessage = By.xpath("//mat-error[contains(text(),'Not a valid email')]");
    private final By disabledLogInButton = By.xpath("//button[contains(@disabled,'true')]");
    private final By StocksApiLogo = By.xpath("//img[@src='assets/logo-image.png']");

    public LoginPage(ChromeWebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        WebElement element = driver.findElement(emailField);
        element.clear();
        element.click();
        element.sendKeys(email);
    }

    public String getEmail() {
        WebElement element = driver.findElement(emailField);
        return element.getText();
    }

    public void setPassword(String password) {
        WebElement element = driver.findElement(passwordField);
        element.clear();
        element.click();
        element.sendKeys(password);
    }

    public String getPassword() {
        WebElement element = driver.findElement(passwordField);
        return element.getText();
    }

    public void clickLoginButton() {
        WebElement element = driver.findElement(loginButton);
        element.click();
    }

    public void clickRegistrationLink() {
        WebElement element = driver.findElement(registrationLink);
        element.click();
    }

    public String getAlertMessage() {
        driver.getWebDriverWait().until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public String getEmailErrorMessage() {
        driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(emailFormatErrorMessage));
        return driver.findElement(emailFormatErrorMessage).getText();
    }

    public String getMissingValueErrorMessage() {
        driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(missingValueErrorMessage));
        return driver.findElement(missingValueErrorMessage).getText();
    }

    public boolean isLogInButtonDisabled() {
        return driver.findElement(disabledLogInButton).isDisplayed();
    }

    public boolean isLogoVisible() {
        return driver.findElement(StocksApiLogo).isDisplayed();
    }
}