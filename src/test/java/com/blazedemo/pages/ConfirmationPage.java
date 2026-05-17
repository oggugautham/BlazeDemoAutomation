package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    private final WebDriver driver;
    private final By bodyText = By.tagName("body");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfirmationText() {
        return driver.findElement(bodyText).getText();
    }
}