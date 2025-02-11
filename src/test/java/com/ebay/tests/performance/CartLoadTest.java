package com.ebay.tests.performance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartLoadTest {

    private static final int NUMBER_OF_USERS = 50;

    @Test
    public void testConcurrentAddToCart() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\andasov_temirlan\\Documents\\soft_etc\\chromedriver-win64\\chromedriver.exe");
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_USERS);

        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            executor.execute(() -> {
                WebDriver driver = new ChromeDriver();
                try {
                    driver.get("https://www.ebay.com");

                    // Click on Cart
                    Thread.sleep(3000);
                    driver.findElement(By.cssSelector(".gh-cart > div:nth-child(1) > a:nth-child(1)")).click();

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
