package com.stocks.pageDefinitions;

import com.stocks.ChromeWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage {

    private ChromeWebDriver driver;
    private final By usernameField = By.id("register-username-input");
    private final By emailField = By.id("register-email-input");
    private final By passwordField = By.id("register-password-input");
    private final By confirmPasswordField = By.id("register-retype-password-input");
    private final By registerButton = By.id("register-button");
    private final By missingValueErrorMessage = By.xpath("//mat-error[contains(text(),'You must enter a value')]");
    private final By emailFormatErrorMessage = By.xpath("//mat-error[contains(text(),'Not a valid email')]");
    private final By disabledRegisterButton = By.xpath("//button[contains(@disabled,'true')]");
    private final By StocksApiLogo = By.xpath("//img[@src='assets/logo-image.png']");
    private final By backToLogInLink = By.id("back-to-login-button");

    public RegistrationPage(ChromeWebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        WebElement element = driver.findElement(usernameField);
        element.sendKeys(username);
    }

    public void setEmail(String email) {
        WebElement element = driver.findElement(emailField);
        element.sendKeys(email);
    }

    public void setPassword(String password) {
        WebElement element = driver.findElement(passwordField);
        element.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        WebElement element = driver.findElement(confirmPasswordField);
        element.sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        WebElement element = driver.findElement(registerButton);
        element.click();
    }

    public boolean isRegisterButtonVisible() {
        WebElement element = driver.findElement(registerButton);
        return element.isDisplayed();
    }

    public boolean isRegisterButtonDisabled() {
        WebElement element = driver.findElement(disabledRegisterButton);
        return element.isDisplayed();
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

    public boolean isLogoVisible() {
        return driver.findElement(StocksApiLogo).isDisplayed();
    }

    public LoginPage clickBackToLogInLink() {
        driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(backToLogInLink));
        driver.findElement(backToLogInLink).click();
        return new LoginPage(driver);
    }
}