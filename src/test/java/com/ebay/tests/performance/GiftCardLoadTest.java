package com.ebay.tests.performance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GiftCardLoadTest {

    private static final int NUMBER_OF_USERS = 50; // Number of concurrent users

    @Test
    public void testConcurrentAddToCart() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\andasov_temirlan\\Documents\\soft_etc\\chromedriver-win64\\chromedriver.exe");
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_USERS);

        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            executor.execute(() -> {
                WebDriver driver = new ChromeDriver();
                try {
                    driver.get("https://www.ebay.com/giftcards");

                    // Click on 'Start Gifting'
                    driver.findElement(By.xpath("/html/body/main/section[2]/div[1]/div/div/article/div/header/div/button")).click();
                    Thread.sleep(3000);
                    driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[4]/a")).click();
                    Thread.sleep(3000);

                    System.out.println("Test passed successfully!");

                } catch (Exception e) {
                    System.out.println("Test failed: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    driver.quit();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all threads to finish
        }
    }
}
