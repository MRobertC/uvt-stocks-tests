package com.stocks.features;

import com.stocks.ChromeWebDriver;
import com.stocks.pageDefinitions.DashboardPage;
import com.stocks.pageDefinitions.LoginPage;
import com.stocks.pageDefinitions.RegistrationPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {
    private ChromeWebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    @Before
    public void setup() {
        driver = new ChromeWebDriver();
        driver.get("http://localhost:4200/auth");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessLogin() {
        loginPage.setEmail("robert.curetean@gmail.com");
        loginPage.setPassword("parola1234");
        loginPage.clickLoginButton();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertEquals("rcuretean", dashboardPage.getUsername());
    }

    @Test
    public void testFailedLogin() {
        loginPage.setEmail("robert.curetean@gmail.com");
        loginPage.setPassword("parola5678");
        loginPage.clickLoginButton();
        Assert.assertEquals("Bad credentials!", loginPage.getAlertMessage());
    }

    @Test
    public void testLogoVisibility() {
        Assert.assertTrue(loginPage.isLogoVisible());
    }

    @Test
    public void testRefreshEmptyData() {
        loginPage.setEmail("robert.curetean@gmail.com");
        loginPage.setPassword("parola5678");
        driver.navigate().refresh();
        Assert.assertEquals("", loginPage.getEmail());
        Assert.assertEquals("", loginPage.getPassword());
    }

    @Test
    public void testDisabledLogInButton() {
        Assert.assertTrue(loginPage.isLogInButtonDisabled()); //button disabled when both fields empty
        loginPage.setPassword("robert");
        Assert.assertTrue(loginPage.isLogInButtonDisabled()); //button disabled when email field empty
        loginPage.setEmail("robert");
        Assert.assertTrue(loginPage.isLogInButtonDisabled()); //button disabled when email format is incorrect
    }

    @Test
    public void testEmailFieldError() {
        loginPage.setEmail("test");
        loginPage.setPassword("test"); //shift focus to next field to trigger error
        Assert.assertEquals("Not a valid email", loginPage.getEmailErrorMessage());
    }

    @Test
    public void testMissingValueEmailError() {
        loginPage.setEmail("");
        loginPage.setPassword("test"); //shift focus to next field to trigger error
        Assert.assertEquals("You must enter a value", loginPage.getMissingValueErrorMessage());
    }

    @Test
    public void testMissingValuePasswordError() {
        loginPage.setPassword(""); //shift focus to next field to trigger error
        loginPage.setEmail("robert.curetean@gmail.com");
        Assert.assertEquals("You must enter a value", loginPage.getMissingValueErrorMessage());
    }

    @Test
    public void testRegistrationLink() {
        registrationPage = loginPage.clickRegistrationLink();
        Assert.assertTrue(registrationPage.isRegisterButtonVisible());
    }

    @After
    public void destroy() {
        driver.quit();
    }
}