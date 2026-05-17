package com.blazedemo.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static void captureScreenshot(
            WebDriver driver,
            String fileName) {

        TakesScreenshot ts =
                (TakesScreenshot) driver;

        File src =
                ts.getScreenshotAs(
                        OutputType.FILE);

        File dest =
                new File("screenshots/"
                        + fileName + ".png");

        try {

            FileUtils.copyFile(src, dest);

            System.out.println(
                    "Screenshot Captured");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}