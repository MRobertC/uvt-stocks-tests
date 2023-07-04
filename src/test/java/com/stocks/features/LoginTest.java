package com.stocks.features;

import com.stocks.ChromeWebDriver;
import com.stocks.pageDefinitions.DashboardPage;
import com.stocks.pageDefinitions.HomePage;
import com.stocks.pageDefinitions.LoginPage;
import com.stocks.pageDefinitions.RegistrationPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {
    private LoginPage loginPage;
    private ChromeWebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeWebDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
    }

    @Test
    public void testSuccessLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("robert.curetean@gmail.com");
        loginPage.setPassword("parola1234");
        loginPage.clickLoginButton();
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertEquals("rcuretean", dashboardPage.getTitle());
    }

    @Test
    public void testFailedLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("robert.curetean@gmail.com");
        loginPage.setPassword("parola5678");
        loginPage.clickLoginButton();
        Assert.assertEquals("Bad credentials!", loginPage.getAlertMessage());
    }

    @Test
    public void testLogoVisibility() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLogoVisible());
    }

    @Test
    public void testRefreshEmptyData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("robert.curetean@gmail.com");
        loginPage.setPassword("parola5678");
        driver.navigate().refresh();
        Assert.assertEquals("", loginPage.getEmail());
        Assert.assertEquals("", loginPage.getPassword());
    }

    @Test
    public void testDisabledLogInButton() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLogInButtonDisabled()); //button disabled when both fields empty
        loginPage.setPassword("robert");
        Assert.assertTrue(loginPage.isLogInButtonDisabled()); //button disabled when email field empty
        loginPage.setEmail("robert");
        Assert.assertTrue(loginPage.isLogInButtonDisabled()); //button disabled when email format is incorrect
    }

    @Test
    public void testEmailFieldError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("test");
        loginPage.setPassword("test"); //shift focus to next field to trigger error
        Assert.assertEquals("Not a valid email", loginPage.getEmailErrorMessage());
    }

    @Test
    public void testMissingValueEmailError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("");
        loginPage.setPassword("test"); //shift focus to next field to trigger error
        Assert.assertEquals("You must enter a value", loginPage.getMissingValueErrorMessage());
    }

    @Test
    public void testMissingValuePasswordError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setPassword(""); //shift focus to next field to trigger error
        loginPage.setEmail("robert.curetean@gmail.com");
        Assert.assertEquals("You must enter a value", loginPage.getMissingValueErrorMessage());
    }

    @Test
    public void testRegistrationLink() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assert.assertTrue(registrationPage.isRegisterButtonVisible());
    }

    @After
    public void destroy() {
        driver.quit();
    }
}