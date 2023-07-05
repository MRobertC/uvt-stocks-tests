package com.stocks.pageDefinitions;

import com.stocks.ChromeWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DashboardPage {
    private final ChromeWebDriver driver;
    private final By dashboardTitle = By.xpath("//mat-card-title");
    private final By dashboardSubtitle = By.xpath("//mat-card-subtitle");
    private final By logOutLink = By.xpath("//button/span[contains(text(), 'Logout')]");
    private final By stocks = By.xpath("//app-stock-info");

    public DashboardPage(ChromeWebDriver driver) {
        this.driver = driver;
    }

    public String getUsername() {
        driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
        WebElement title = driver.findElement(dashboardTitle);
        return title.getText();
    }

    public String getEmail() {
        driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(dashboardSubtitle));
        WebElement title = driver.findElement(dashboardSubtitle);
        return title.getText();
    }

    public void clickLogOutLink()
    {   driver.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(logOutLink));
        WebElement element = driver.findElement(logOutLink);
        element.click();
    }

    public int getStocksCount()
    {
        driver.getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(stocks));
        List<WebElement> elements = driver.findElements(stocks);
        return elements.size();
    }
}
