package com.ebay.tests.regression;

import com.ebay.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RemovalFromCartTest extends BaseTest {

    @Test
    public void testAddToCart() throws InterruptedException {
        try {
            driver.get("https://www.ebay.com");

            // Search for 'Laptop'
            driver.findElement(By.xpath("//*[@id='gh-ac']")).sendKeys("Laptop");
            driver.findElement(By.id("gh-search-btn")).click();
            Thread.sleep(3000);

            // Click first item
            driver.findElement(By.xpath("/html/body/div[5]/div[4]/div[3]/div[1]/div[3]/ul/li[1]/div/div[2]/a")).click();
            Thread.sleep(3000);

            // Switch to new tab
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(3000);
            // Click "Add to Cart"
            driver.findElement(By.xpath("//*[text()='Add to cart']")).click();
            Thread.sleep(3000);

            // Click outside the pop-up to close it
            Actions actions = new Actions(driver);
            actions.moveByOffset(10, 10).click().perform();

            // Click "See in cart"
            driver.findElement(By.xpath("//*[text()='See in cart']")).click();
            Thread.sleep(3000);

            WebElement removeButton = driver.findElement(By.cssSelector("[data-test-id='cart-remove-item']"));
            removeButton.click();

            Thread.sleep(3000);

            WebElement emptyText = driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div/div/div/div[1]/span/span/span"));
            Assert.assertTrue(emptyText.isDisplayed(), "Text is not displayed, test failed!");

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
