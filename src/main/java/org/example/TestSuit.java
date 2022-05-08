package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

//import java.time.Duration;

public class TestSuit {
    protected static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src\\test\\java\\Drivers\\chromedriver.exe");
        // Open chrome browser
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        // click on register button
        driver.findElement(By.className("ico-register")).click();

        // enter firstname
        driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Autoamtion");

        //enter lastname
        driver.findElement(By.id("LastName")).sendKeys("LastNameTest");

        // enter email
        driver.findElement(By.id("Email")).sendKeys("demo@nopcommerce.com");

        // enter password

        driver.findElement(By.name("Password")).sendKeys("Test123");

        // enter confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("Test123");

    }
}
