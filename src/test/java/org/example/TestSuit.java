package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestSuit {

    protected static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src\\test\\java\\Drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

    }
}
