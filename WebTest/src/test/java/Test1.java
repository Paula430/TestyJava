import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\paula\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        String url = "https://automationexercise.com/";

        driver.get(url);
        j.executeScript("return document.readyState")
                .toString().equals("complete");

        String s = driver.getCurrentUrl();

        if (s.equals(url)) {
            System.out.println("Page Loaded");

        } else {
            System.out.println("Page did not load");
        }

        Thread.sleep(1000);

        WebElement buttonSignUp = driver.findElement(By.cssSelector("ul>:nth-child(4) a"));
        buttonSignUp.click();

        Thread.sleep(2000);

        String textNewUser = driver.findElement(By.cssSelector(".signup-form h2")).getText();

        Assert.assertEquals("New User Signup!", textNewUser);

        WebElement nameInput = driver.findElement(By.cssSelector("input[data-qa='signup-name']"));
        nameInput.sendKeys("test1");

        WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
        String randomEmail=getSaltString()+"@gmail.com";
        emailInput.sendKeys(randomEmail);

        WebElement buttonSignup = driver.findElement(By.cssSelector("button[data-qa='signup-button']"));
        buttonSignup.click();

        String textEnterAccount= driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals("ENTER ACCOUNT INFORMATION", textEnterAccount);

        String gender="label[for='id_gender"+getIntTitle()+"']>div>span>input";
        WebElement usersTitle = driver.findElement(By.cssSelector(gender));
        usersTitle.click();

        WebElement passwordInput = driver.findElement(By.cssSelector("input[data-qa='password']"));
        passwordInput.sendKeys( getSaltString());


        WebElement daysSelect = driver.findElement(By.cssSelector("select[data-qa='days']"));
        Select selectDays= new Select(daysSelect);
        selectDays.selectByValue("15");

        WebElement monthSelect = driver.findElement(By.cssSelector("select[data-qa='months']"));
        Select selectMonth=new Select(monthSelect);
        selectMonth.selectByValue("10");

        WebElement yearSelect = driver.findElement(By.cssSelector("select[data-qa='years']"));
        Select selectYear=new Select(yearSelect);
        selectYear.selectByValue("1970");

        WebElement firstNameInput = driver.findElement(By.cssSelector("input[data-qa='first_name']"));
        firstNameInput.sendKeys(getOnlyString());

        WebElement lastNameInput = driver.findElement(By.cssSelector("input[data-qa='last_name']"));
        lastNameInput.sendKeys(getOnlyString());

        WebElement companyInput = driver.findElement(By.cssSelector("input[data-qa='company']"));
        companyInput.sendKeys(getOnlyString());

        WebElement adresInput = driver.findElement(By.cssSelector("input[data-qa='address']"));
        adresInput.sendKeys("Bielsko-Biała");

        WebElement countrySelect = driver.findElement(By.cssSelector("select[data-qa='country']"));
        Select selectCountry=new Select(countrySelect);
        selectCountry.selectByValue("Canada");

        WebElement stateInput = driver.findElement(By.cssSelector("input[data-qa='state']"));
        stateInput.sendKeys(getOnlyString());

        WebElement cityInput = driver.findElement(By.cssSelector("input[data-qa='city']"));
        cityInput.sendKeys("Bielsko-Biała");

        WebElement zipcodeInput = driver.findElement(By.cssSelector("input[data-qa='zipcode']"));
        zipcodeInput.sendKeys("32-300");

        WebElement phoneInput = driver.findElement(By.cssSelector("input[data-qa='mobile_number']"));
        phoneInput.sendKeys(getPhoneNumber());

        WebElement submitForm = driver.findElement(By.cssSelector("button[data-qa='create-account']"));
        submitForm.click();

        String textCreated = driver.findElement(By.cssSelector(".text-center")).getText();
        Assert.assertEquals("ACCOUNT CREATED!", textCreated);

        WebElement continueButton = driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
        continueButton.click();

        Thread.sleep(2000);
        String loggedName=driver.findElement(By.cssSelector(".fa-user + b")).getText();
        Assert.assertEquals("test1", loggedName);

        WebElement deleteAccountButton = driver.findElement(By.cssSelector("a[href='/delete_account']"));
        deleteAccountButton.click();

        Thread.sleep(2000);
        WebElement confirmDeleteButton = driver.findElement(By.cssSelector("[class='modal-footer'] form"));
        confirmDeleteButton.click();

        Thread.sleep(1000);
        WebElement deletedText=driver.findElement(By.xpath("//*[text()=' ACCOUNT DELETED!' ]"));
        Assert.assertTrue(deletedText.isDisplayed());

        WebElement continueBtn=driver.findElement(By.xpath("//*[text()='Continue']"));
        continueBtn.click();

        driver.quit();

    }

    public static String getPhoneNumber() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 9) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString().toLowerCase();
        return saltStr;

    }

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 6) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString().toLowerCase();
        return saltStr;

    }

    public static String getOnlyString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString().toLowerCase();
        return saltStr;

    }

    public static int getIntTitle(){
        int[] table={1,2};
        int rnd= new Random().nextInt(table.length);
        return table[rnd];

    }







}


//    static void waitForPageLoad(WebDriver wdriver) {
//
//        WebDriverWait wait = new WebDriverWait(wdriver, 60);
//
//        WebDriver pageLoaded = new WebDriver() {
//
//            public boolean apply(WebDriver input) {
//                return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
//            }
//
//        };
//        wait.until(pageLoaded);





// driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.get(url);





//        WebDriverWait wait = new WebDriverWait(driver, 10);  // you can reuse this one
//
//        WebElement elem = driver.findElement(By.id("myInvisibleElement"));
//        elem.click();
//        wait.until(ExpectedConditions.visibilityOf(elem));



