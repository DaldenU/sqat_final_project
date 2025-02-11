package com.ebay.tests.usability;

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

public class IncorrectAddressErrorVisibility extends BaseTest {

    @Test
    public void testAddToCart() throws InterruptedException {
        try {
            driver.get("https://www.ebay.com/giftcards");

            // Click on 'Start Gifting'
            driver.findElement(By.xpath("/html/body/main/section[2]/div[1]/div/div/article/div/header/div/button")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[4]/a")).click();
            Thread.sleep(3000);

            // Click on eBay logo
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/div/div[1]/div[2]/div/div[2]/div/nav/button[1]")).click();
            Thread.sleep(3000);

            //Click on "Mail to someone"
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"recipient_name\"]")).sendKeys("ww");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"gift_message\"]")).sendKeys("ww");
            Thread.sleep(3000);

            // type in incorrect address
            driver.findElement(By.xpath("//*[@id=\"recipient_address_line_1\"]")).sendKeys("ww");
            Thread.sleep(3000);
            
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div/div/form/div[6]/div[1]/select")).click();
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div/div/form/div[6]/div[1]/select/option[2]")).click();


            driver.findElement(By.xpath("//*[@id=\"recipient_address_line_2\"]")).sendKeys("ww");
            
            driver.findElement(By.xpath("//*[@id=\"recipient_city\"]")).sendKeys("ww");
            
            driver.findElement(By.xpath("//*[@id=\"recipient_postal_code\"]")).sendKeys("ww");
            
            driver.findElement(By.xpath("//*[@id=\"sender_name\"]")).sendKeys("ww");
            
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div/div/div[2]/div[2]/div/div/form/div[7]/div[4]/button")).click();

            // Verify that the message is visibile
            WebElement element = driver.findElement(By.xpath("//*[text()='We were unable to verify the address you supplied.']"));
            Assert.assertTrue(element.isDisplayed(), "The message is NOT visible!");

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