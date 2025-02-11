package com.ebay.base;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.net.MalformedURLException;
import java.net.URI;

public class SauceLabsDriver {
    public static RemoteWebDriver createDriver(String browser) throws MalformedURLException {
        String sauceURL = "https://ondemand.us-west-1.saucelabs.com:443/wd/hub";

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-daldenuteryag-c70f5");
        sauceOptions.setCapability("accessKey", "d9a4eb5f-3474-4246-8ab0-366f40233e32");
        sauceOptions.setCapability("build", "eBay-Test-Build");
        sauceOptions.setCapability("name", "eBay Test Case");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");

        MutableCapabilities capabilities = new MutableCapabilities();

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName("Windows 11");
                chromeOptions.setBrowserVersion("latest");
                chromeOptions.setCapability("sauce:options", sauceOptions);
                capabilities = chromeOptions;
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName("Windows 11");
                firefoxOptions.setBrowserVersion("latest");
                firefoxOptions.setCapability("sauce:options", sauceOptions);
                capabilities = firefoxOptions;
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPlatformName("Windows 11");
                edgeOptions.setBrowserVersion("latest");
                edgeOptions.setCapability("sauce:options", sauceOptions);
                capabilities = edgeOptions;
                break;
            
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        return new RemoteWebDriver(URI.create(sauceURL).toURL(), capabilities);
    }
}