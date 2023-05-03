package com.stocks.features;

import com.stocks.ChromeWebDriver;
import com.stocks.pageDefinitions.HomePage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomePageTest {
    private ChromeWebDriver driver;

    @Before
    public void setup() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
    }

    @Test
    public void testTitle() {
        assertEquals("Emag", driver.getTitle());
        // Add assertions to verify successful login
    }
}