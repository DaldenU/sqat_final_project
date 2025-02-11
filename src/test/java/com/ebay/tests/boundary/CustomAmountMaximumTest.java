package com.ebay.tests.boundary;

import com.ebay.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CustomAmountMaximumTest extends BaseTest {

    @Test
    public void testAddToCart() throws InterruptedException {
        try {
            driver.get("https://www.ebay.com/giftcards");

            // Click on 'Start Gifting'
            driver.findElement(By.xpath("/html/body/main/section[2]/div[1]/div/div/article/div/header/div/button")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[4]/a")).click();
            Thread.sleep(3000);

            // Click on 'Custom' field
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div[2]/div/span/input")).sendKeys("201");

            // Click on "Recipient name" field
            driver.findElement(By.xpath("//*[@id=\"recipient_name\"]")).click();
            Thread.sleep(3000);

            WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div[2]/div/div[1]"));
            Assert.assertTrue(element.isDisplayed(), "Element is NOT visible!");

            
            
            // Mark test as passed in Sauce Labs
            markTestStatus("passed", "Test passed successfully!", driver);
        } catch (Exception e) {
            // Mark test as failed in Sauce Labs
            markTestStatus("failed", "Test failed: " + e.getMessage(), driver);
            throw e;
        }
    }

    // Helper function to mark test status in Sauce Labs
    public static void markTestStatus(String status, String reason, WebDriver driver) {
        if (driver instanceof RemoteWebDriver) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Map<String, Object> scriptArgs = new HashMap<>();
            scriptArgs.put("status", status);
            scriptArgs.put("reason", reason);
            js.executeScript("sauce:job-result=" + status);
        }
    }
}