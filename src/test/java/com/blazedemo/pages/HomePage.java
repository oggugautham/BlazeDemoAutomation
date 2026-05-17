package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    private final WebDriver driver;

    private final By fromPort = By.name("fromPort");
    private final By toPort = By.name("toPort");
    private final By findFlightsButton = By.cssSelector("input[type='submit']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean areDropdownsVisible() {
        return driver.findElement(fromPort).isDisplayed()
                && driver.findElement(toPort).isDisplayed();
    }

    public void searchFlights(String fromCity, String toCity) {
        new Select(driver.findElement(fromPort)).selectByVisibleText(fromCity);
        new Select(driver.findElement(toPort)).selectByVisibleText(toCity);
        driver.findElement(findFlightsButton).click();
    }
}