package com.stocks.features;

import com.stocks.ChromeWebDriver;
import com.stocks.pageDefinitions.DashboardPage;
import com.stocks.pageDefinitions.LoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DashboardTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ChromeWebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeWebDriver();
        driver.get("http://localhost:4200/auth");
        loginPage = new LoginPage(driver);
        loginPage.setEmail("robert.curetean@gmail.com");
        loginPage.setPassword("parola1234");
        loginPage.clickLoginButton();
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void testAccountInfo()
    {
        Assert.assertEquals("rcuretean", dashboardPage.getUsername());
        Assert.assertEquals("robert.curetean@gmail.com", dashboardPage.getEmail());
    }

    @Test
    public void testNumberOfStocks()
    {
        Assert.assertEquals(22, dashboardPage.getStocksCount());
    }

    @Test
    public void testLogOut()
    {
        dashboardPage.clickLogOutLink();
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLogInButtonVisible());
    }

    @After
    public void destroy() {
        driver.quit();
    }
}