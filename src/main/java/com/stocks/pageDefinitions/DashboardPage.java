package com.stocks.pageDefinitions;

import com.stocks.ChromeWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage {
    private final ChromeWebDriver driver;
    private final By dashboardTitle = By.xpath("//mat-card-title");
    public DashboardPage(ChromeWebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
        WebElement title = driver.findElement(dashboardTitle);
        return title.getText();
    }
}
