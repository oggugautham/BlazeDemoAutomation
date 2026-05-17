package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReservePage {

    private final WebDriver driver;

    private final By chooseFlightButton =
            By.cssSelector("input[value='Choose This Flight']");

    public ReservePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isReservePageDisplayed() {
        return driver.getCurrentUrl().contains("reserve.php")
                || driver.findElements(chooseFlightButton).size() > 0;
    }

    public void chooseFlight() {
        driver.findElements(chooseFlightButton).get(0).click();
    }
}