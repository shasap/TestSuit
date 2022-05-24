package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//import java.time.Duration;

public class TestSuit {

    protected static WebDriver driver;

    //-----------------------------------------Open Browser-----------------------------------------------
    @BeforeMethod
    public void openUrlInBrowser(){

        System.setProperty("webdriver.chrome.driver", "src/test/java/Drivers/chromedriver.exe");
        //Open Chrome
        driver = new ChromeDriver();  // import chrome web-Driver dependency (In 'POM' file)

        //Duration to be wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  //if this line 'RED' ,Click on bulb and select first option

        //Maximize the screen
        driver.manage().window().maximize();

        //Open Web page
        driver.get("https://demo.nopcommerce.com/");

    }

    //-----------------------------------User should be able to registered----------------------------------
    @Test
    public void userShouldBeAbleToRegisterSuccessfully(){

        //Click on register button
        clickOnElement(By.className("ico-register"));

        // select gender
        clickOnElement(By.id("gender-male"));

        // enter firstname
        typeText(By.xpath("//input[@name='FirstName']"),"Peter");

        //enter lastname
        typeText(By.id("LastName"),"Parker");

        // enter day of birth
        Select birthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        birthDay.selectByValue("6");

        // enter day of Month
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("February");

        // enter year
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByValue("1991");

        // enter email
        typeText(By.id("Email"),"demo"+randomDate()+"@nopcommerce.com");

        // enter password
        typeText(By.name("Password"),"Test123");

        // enter confirm password........
        typeText(By.name("ConfirmPassword"),"Test123");

        // click register
        clickOnElement(By.id("register-button"));

        // Expected message from documents/requirements received
        String expectedMessage = "Your registration completed";

        // Actual message from Web page
        String actualMessage = driver.findElement(By.className("result")).getText();

        // Assert to verify actual message text
        Assert.assertEquals(actualMessage,expectedMessage,"Registration is not successful");

    }

    //-------------------Registered user should be able to Email product to a friend------------------------
    @Test
    public void registerUserShouldBeAbleToEmailProductToFriend(){

        //Click on register button
        clickOnElement(By.className("ico-register"));

        // select gender
        clickOnElement(By.id("gender-male"));

        // enter firstname
        typeText(By.xpath("//input[@name='FirstName']"),"Peter");

        //enter lastname
        typeText(By.id("LastName"),"Parker");

        // enter day of birth
        Select birthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        birthDay.selectByValue("6");

        // enter day of Month
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("February");

        // enter year
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByValue("1991");

        // enter email
        typeText(By.id("Email"),"demo"+randomDate()+"@nopcommerce.com");

        // enter password
        typeText(By.name("Password"),"Test123");

        // enter confirm password........
        typeText(By.name("ConfirmPassword"),"Test123");

        // click register
        clickOnElement(By.id("register-button"));

        // Expected registration completion message from documents/requirements received
        String expectedMessage = "Your registration completed";

        // Actual message from Web page
        String actualMessage = driver.findElement(By.className("result")).getText();

        // Assert to verify actual message text
        Assert.assertEquals(actualMessage,expectedMessage,"Registration is not successful");

        // Continue as registered user
        clickOnElement(By.cssSelector("[class=\"button-1 register-continue-button\"]"));

        // Click on Build your own computer text to select product
        clickOnElement(By.xpath("//h2/a[@href=\"/build-your-own-computer\"]"));

        // Click on Email a friend button on selected product
        clickOnElement(By.cssSelector("[class=\"button-2 email-a-friend-button\"]"));

        // Enter friends's Email address
        typeText(By.className("friend-email"),"abc@xyz.com");

        // Enter personal message to friend
        typeText(By.id("PersonalMessage"),"This is a good customized computer.");

        // Click on send Email
        clickOnElement(By.name("send-email"));

        // Expected message to verify email has been sent
        String expectedEmailSentMessage = "Your message has been sent.";

        // Actual message to verify email has been sent
        String actualEmailSentMessage = getTextFromElement(By.className("result"));

        // Verify Actual & Expected text
        Assert.assertEquals(actualEmailSentMessage,expectedEmailSentMessage,"Your message has not been sent.");

    }

    //---------------User should be able to select, customized and add product to cart---------------------
    @Test
    public void userShouldBeAbleToSelectCustomizedAndAddProductToCart(){

        //Click on computer
        clickOnElement(By.xpath("//ul[1]/li/a[@href=\"/computers\"]"));

        //Click Desktop in computers in categories
        clickOnElement(By.xpath("//li[@class=\"inactive\"]/a[@href=\"/desktops\"]"));

        //Click on Add to cart for 'Build your own computer'
        clickOnElement(By.xpath("//div/div[1]/div/div[2]/div[3]/div[2]/button[@class=\"button-2 product-box-add-to-cart-button\"]"));

        // Select processor 2.2GHz
        Select processor = new Select(driver.findElement(By.id("product_attribute_1")));
        processor.selectByValue("1");

        //Select RAM 2GB
        Select ram = new Select(driver.findElement(By.id("product_attribute_2")));
        ram.selectByValue("3");

        //Select HDD 320GB
        clickOnElement(By.id("product_attribute_3_6"));

        // Select OS Vista premium
        clickOnElement(By.id("product_attribute_4_9"));

        // Select all software type - Microsoft office already selected
        clickOnElement(By.id("product_attribute_5_11")); //Acrobat Reader
        clickOnElement(By.id("product_attribute_5_12")); //Total Commander

        // Add 'Build your computer' product to cart
        clickOnElement(By.id("add-to-cart-button-1"));

        // Click shopping cart
        clickOnElement(By.className("cart-label"));

        // Expected Shopping cart text
        String expectedShoppingCart = "Shopping cart";

        // Actual Shopping cart text
        String  actualShoppingCart = getTextFromElement(By.xpath("//h1[.='Shopping cart']"));

        // Verify user can see shopping cart text
        Assert.assertEquals(actualShoppingCart,expectedShoppingCart,"Shopping cart not displayed");

        // Expected text of 'Build your own computer'
        String expectedBuildYourOwnComputer = "Build your own computer";

        // Actual text of 'Build your own computer'
        String actualBuildYourOwnComputer = getTextFromElement(By.className("product-name"));

        // Verify user can see selected product name in shopping cart
        Assert.assertEquals(actualBuildYourOwnComputer,expectedBuildYourOwnComputer,"Build your computer not visible");

    }

    //-----------------------User should be able to see price in selected currency-------------------------
    @Test
    public void userShouldBeAbleToSeePriceInSelectedCurrency(){

        //User should be able to see product price in US dollar as US Dollar is selected currency

        //Expected price in USD for Build your own computer
        String expectedPriceInUSD = "$1,200.00";

        //Actual price in USD for Build your own computer
        String actualPriceInUSD = getTextFromElement(By.xpath("//span[.=\"$1,200.00\"]"));

        // Verify 'Build your own computer' - price in USD
        Assert.assertEquals(actualPriceInUSD,expectedPriceInUSD,"Product price is not in US Dollar");

        // Change currency to Euro or select currency as Euro
        Select euro = new Select(driver.findElement(By.id("customerCurrency")));
        euro.selectByVisibleText("Euro");

        // Expected price in Euro for - Build your own computer
        String expectedPriceInEuro = "€1032.00";

        // Actual price in Euro for Build your own computer
        String actualPriceInEuro = getTextFromElement(By.xpath("//span[.='€1032.00']"));

        // Verify price in Euro for 'Build your own computer'
        Assert.assertEquals(actualPriceInEuro,expectedPriceInEuro,"Product price is not in Euro");



    }

    //------------------------------------------Close Browser----------------------------------------------
    @AfterMethod
    public void closeBrowser(){

        // Close browser
        driver.quit();

    }






    //-------------------------------------------Util methods------------------------------------------------

   // a.-----typeText or Send key method
    public static void typeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    // b.----- clickOnElement method
    public static void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    // c. Random date and time stamp method
    public static String randomDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        String format = formatter.format(date);
        return formatter.format(date);
    }

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    // d.-----implicit wait method
    public static void implicitWait(int second) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(second));
    }

    // e.----- get text method
    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    //f. -----explicit wait --
    // 1. to click element
    public static void driverWaitForClickable(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    // 2. Element Selected
    public static void driverWaitElementToBeSelected(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }

    // 3. Url contains
    public static void driverWaitUrlContains(int time, By by, String urlName) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait1.until(ExpectedConditions.urlContains(urlName));
    }

    // 3.1. URL tobe
    public static void driverWaitsUntilContainsUrl(int time, String url) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait1.until(ExpectedConditions.urlToBe(url));
    }

    // 3.2. Url Fraction .urlContains (String fraction)
    public static void driverWaitsUrlFraction(int time, String UrlFraction) {
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait4.until(ExpectedConditions.urlContains(UrlFraction));
    }

    // 4. Attribute contains
    public static void driverWaitAttributeContains(By by, int time, String Attribute, String Value) {
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait2.until(ExpectedConditions.attributeContains(by, Attribute, Value));
    }

    // 5. Title contains
    public static void driverWaitsTitleContains(int time, String text) {
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait3.until(ExpectedConditions.titleContains(text));
    }

    // 6. Title is
    public static void driverWaitsTitle(int time, By by, String title) {
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait5.until(ExpectedConditions.titleIs(title));
    }

    // 7. Element Invisible
    public static void driverWaitsUntilElementInvisible(int time, By by, String url) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait1.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    // 8. Explicit wait -- to check element is visible
    public static void driverWaitsVisibilityOfElement(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // 9. Explicit wait -- to check element is found on web-page
    public static void driverWaitsPresenceOfElement(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(presenceOfElementLocated(By.id("")));
    }

    // 10. Alert is present
    public static void driverWaitsAlertIsPresent(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    // 11. Checking WebElement with given locator has specific text
    public static void driverWaits(int time, By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBe(by, text));
    }

}







