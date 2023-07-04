package com.stocks.pageDefinitions;

import com.stocks.ChromeWebDriver;

public class HomePage {

    protected ChromeWebDriver driver;

    public HomePage(ChromeWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get("http://localhost:4200");
    }
}
