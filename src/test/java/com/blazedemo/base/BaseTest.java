package com.blazedemo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public WebDriver driver;

    @BeforeClass(alwaysRun = true)

    public void setup() {

        System.out.println(
                "SETUP STARTED");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(
                "https://blazedemo.com/");

        System.out.println(
                "BROWSER OPENED");
    }

    @AfterClass(alwaysRun = true)

    public void teardown() {

        if (driver != null) {

            driver.quit();
        }

        System.out.println(
                "BROWSER CLOSED");
    }
}