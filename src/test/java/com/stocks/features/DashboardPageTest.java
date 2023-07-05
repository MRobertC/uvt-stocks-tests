package com.stocks.features;

import com.stocks.ChromeWebDriver;
import com.stocks.pageDefinitions.DashboardPage;
import com.stocks.pageDefinitions.LoginPage;
import org.junit.After;
import org.junit.Before;

public class DashboardPageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ChromeWebDriver driver;

    @Before
    public void setup() {
    }

    @After
    public void destroy() {
        driver.quit();
    }
}
