package com.stocks.features;

import com.stocks.ChromeWebDriver;
import com.stocks.pageDefinitions.DashboardPage;
import com.stocks.pageDefinitions.LoginPage;
import com.stocks.pageDefinitions.RegistrationPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegistrationTest {
    private ChromeWebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void setup() {
        driver = new ChromeWebDriver();
        driver.get("http://localhost:4200/auth");
        loginPage = new LoginPage(driver);
        registrationPage = loginPage.clickRegistrationLink();
    }

    @Test
    public void testSuccessfulRegistration() { //
        registrationPage.setUsername("test1234");
        registrationPage.setEmail("robert.curetean@yahoo.com");
        registrationPage.setPassword("parola1234");
        registrationPage.setConfirmPassword("parola1234");
        registrationPage.clickRegisterButton();
        loginPage = new LoginPage(driver);
        loginPage.setEmail("robert.curetean@yahoo.com");
        loginPage.setPassword("parola1234");
        loginPage.clickLoginButton();
        dashboardPage = new DashboardPage(driver);
        Assert.assertEquals("test1234", dashboardPage.getUsername());
    }

    @Test
    public void testLogoVisibility() {
        Assert.assertTrue(registrationPage.isLogoVisible());
    }

    @Test
    public void testDisabledRegisterButton() {
        Assert.assertTrue(registrationPage.isRegisterButtonDisabled()); //button disabled because all fields are empty
        registrationPage.setUsername("robert");
        Assert.assertTrue(registrationPage.isRegisterButtonDisabled());
        registrationPage.setPassword("parola");
        Assert.assertTrue(registrationPage.isRegisterButtonDisabled());
        registrationPage.setConfirmPassword("parola");
        Assert.assertTrue(registrationPage.isRegisterButtonDisabled()); //button disabled because email empty
        registrationPage.setEmail("test");
        Assert.assertTrue(registrationPage.isRegisterButtonDisabled()); //button disabled because email format is incorrect
    }

    @Test
    public void testEmailFieldError() {
        registrationPage.setEmail("test");
        registrationPage.setPassword("test"); //shift focus to next field to trigger error
        Assert.assertEquals("Not a valid email", registrationPage.getEmailErrorMessage());
    }

    @Test
    public void testMissingValueUsernameError() {
        registrationPage.setUsername("");
        registrationPage.setEmail("test@gmail.com");
        registrationPage.setPassword("test");
        registrationPage.setConfirmPassword("test");
        Assert.assertEquals("You must enter a value", registrationPage.getMissingValueErrorMessage());
    }

    @Test
    public void testMissingValueEmailError() {
        registrationPage.setUsername("test");
        registrationPage.setEmail("");
        registrationPage.setPassword("test");
        registrationPage.setConfirmPassword("test");
        Assert.assertEquals("You must enter a value", registrationPage.getMissingValueErrorMessage());
    }

    @Test
    public void testMissingValuePasswordError() {
        registrationPage.setUsername("rcuretean");
        registrationPage.setEmail("robert.curetean@gmail.com");
        registrationPage.setPassword("");
        registrationPage.setConfirmPassword("parola");
        Assert.assertEquals("You must enter a value", registrationPage.getMissingValueErrorMessage());
    }

    @Test
    public void testMissingValueConfirmPasswordError() {
        registrationPage.setUsername("rcuretean");
        registrationPage.setEmail("robert.curetean@gmail.com");
        registrationPage.setConfirmPassword("");
        registrationPage.setPassword("parola");
        Assert.assertEquals("You must enter a value", registrationPage.getMissingValueErrorMessage());
    }

    @Test
    public void testPasswordsDoNotMatch() {
        registrationPage.setUsername("rcureteanu");
        registrationPage.setEmail("robert.curetean@gmail.com");
        registrationPage.setPassword("parola5678");
        registrationPage.setConfirmPassword("parola");
        registrationPage.clickRegisterButton();
        Assert.assertEquals("Passwords do not match!", registrationPage.getAlertMessage());
    }

    @Test
    public void testBackToLogInLink() {
        registrationPage.clickBackToLogInLink();
        Assert.assertTrue(loginPage.isLogInButtonVisible());
    }

    @After
    public void destroy() {
        driver.quit();
    }
}