package com.stocks.features;

import com.stocks.ChromeWebDriver;
import com.stocks.pageDefinitions.HomePage;
import com.stocks.pageDefinitions.LoginPage;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest {
    private LoginPage loginPage;
    private ChromeWebDriver driver;

    @Before
    public void setup() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
    }

    @Test
    public void testLogin() {
        loginPage.setUsername("username");
        loginPage.setPassword("password");
        loginPage.clickLoginButton();
        driver.quit();
        // Add assertions to verify successful login
    }
}