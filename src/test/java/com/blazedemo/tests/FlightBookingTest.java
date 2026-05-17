package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.ScreenshotUtil;

public class FlightBookingTest extends BaseTest {

    private HomePage homePage;
    private ReservePage reservePage;
    private PurchasePage purchasePage;
    private ConfirmationPage confirmationPage;

    @Test(priority = 1, groups = "smoke")
    public void TC01_verifyHomepageLoadsAndDropdownsVisible() {
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.areDropdownsVisible());
        System.out.println("TC01 PASSED");
        ScreenshotUtil.captureScreenshot(driver, "TC01_Homepage");
    }

    @Test(priority = 2, groups = "functional")
    public void TC02_searchFlightsWithValidCities() {
        driver.get("https://blazedemo.com/");
        homePage = new HomePage(driver);
        homePage.searchFlights("Paris", "Buenos Aires");

        reservePage = new ReservePage(driver);
        Assert.assertTrue(reservePage.isReservePageDisplayed());
        System.out.println("TC02 PASSED");
    }

    @Test(priority = 3, groups = "functional")
    public void TC03_completeFlightBooking() {
        driver.get("https://blazedemo.com/");
        homePage = new HomePage(driver);
        homePage.searchFlights("Paris", "Buenos Aires");

        reservePage = new ReservePage(driver);
        reservePage.chooseFlight();

        purchasePage = new PurchasePage(driver);
        purchasePage.enterBookingDetails("Max", "Toronto", "1234567890");
        purchasePage.clickPurchaseFlight();

        confirmationPage = new ConfirmationPage(driver);
        Assert.assertTrue(confirmationPage.getConfirmationText().contains("Thank you"));
        ScreenshotUtil.captureScreenshot(driver, "TC03_Booking");
        System.out.println("TC03 PASSED");
    }

    @DataProvider(name = "bookingData")
    public Object[][] bookingData() {
        return new Object[][]{
                {"Paris", "Buenos Aires", "Max"},
                {"Philadelphia", "Rome", "John"}
        };
    }

    @Test(priority = 4, groups = "functional", dataProvider = "bookingData")
    public void TC04_multipleBookingsWithDifferentDataSets(
            String fromCity, String toCity, String name) {

        driver.get("https://blazedemo.com/");
        homePage = new HomePage(driver);
        homePage.searchFlights(fromCity, toCity);

        reservePage = new ReservePage(driver);
        reservePage.chooseFlight();

        purchasePage = new PurchasePage(driver);
        purchasePage.enterBookingDetails(name, "Toronto", "9876543210");
        purchasePage.clickPurchaseFlight();

        confirmationPage = new ConfirmationPage(driver);
        Assert.assertTrue(confirmationPage.getConfirmationText().contains("Thank you"));
        System.out.println("Booking completed for: " + name);
    }

    @Test(priority = 5, groups = "negative", enabled = false)
    public void TC05_blankCreditCardNegative() {
        driver.get("https://blazedemo.com/");
        homePage = new HomePage(driver);
        homePage.searchFlights("Paris", "Buenos Aires");

        reservePage = new ReservePage(driver);
        reservePage.chooseFlight();

        purchasePage = new PurchasePage(driver);
        purchasePage.enterBookingDetails("Max", "Toronto", "");
        purchasePage.clickPurchaseFlight();

        System.out.println("TC05 EXECUTED");
    }

    @Test(priority = 6, groups = "negative", enabled = false)
    public void TC06_invalidCreditCardCharacters() {
        driver.get("https://blazedemo.com/");
        homePage = new HomePage(driver);
        homePage.searchFlights("Paris", "Buenos Aires");

        reservePage = new ReservePage(driver);
        reservePage.chooseFlight();

        purchasePage = new PurchasePage(driver);
        purchasePage.enterBookingDetails("Max", "Toronto", "@@@###");
        purchasePage.clickPurchaseFlight();

        System.out.println("TC06 EXECUTED");
    }

    @Test(priority = 7, groups = "negative", enabled = false)
    public void TC07_sameDepartureAndDestinationCityNegative() {
        driver.get("https://blazedemo.com/");
        homePage = new HomePage(driver);
        homePage.searchFlights("Paris", "Paris");
        System.out.println("TC07 EXECUTED");
    }
}