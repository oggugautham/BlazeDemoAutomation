package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {

    private final WebDriver driver;

    private final By name = By.id("inputName");
    private final By address = By.id("address");
    private final By city = By.id("city");
    private final By state = By.id("state");
    private final By zipCode = By.id("zipCode");
    private final By cardType = By.id("cardType");
    private final By creditCardNumber = By.id("creditCardNumber");
    private final By creditCardMonth = By.id("creditCardMonth");
    private final By creditCardYear = By.id("creditCardYear");
    private final By nameOnCard = By.id("nameOnCard");
    private final By purchaseButton = By.cssSelector("input[value='Purchase Flight']");

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterBookingDetails(
            String passengerName,
            String streetAddress,
            String cardNumber) {

        driver.findElement(name).sendKeys(passengerName);
        driver.findElement(address).sendKeys(streetAddress);
        driver.findElement(city).sendKeys("Hyderabad");
        driver.findElement(state).sendKeys("Telangana");
        driver.findElement(zipCode).sendKeys("500001");

        new Select(driver.findElement(cardType)).selectByVisibleText("Visa");

        driver.findElement(creditCardNumber).sendKeys(cardNumber);
        driver.findElement(creditCardMonth).sendKeys("11");
        driver.findElement(creditCardYear).sendKeys("2027");
        driver.findElement(nameOnCard).sendKeys(passengerName);
    }

    public void clickPurchaseFlight() {
        driver.findElement(purchaseButton).click();
    }
}