package runner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSuite_1 {
    WebDriver driver;
    @Before
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void testcase1()
    {
        login("standard_user1", "secret_sauce");
        compareLable("Epic sadface: Username and password do not match any user in this service");
    }
    @Test
    public void login_user_lock_out(){
        login("locked_out_user", "secret_sauce");
        compareLable("Epic sadface: Sorry, this user has been locked out.");
    }
    @Test
    public void TC3(){
        login("problem_user","secret_sauce");
        WebElement icon = driver.findElement(By.xpath("//div[@class='peek']"));
        Assert.assertTrue(icon.isDisplayed());
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    public void login(String username, String pass){
        WebElement txtUsername = driver.findElement(By.id("user-name"));
        WebElement txtPass = driver.findElement(By.id("password"));
        WebElement bnbLogin = driver.findElement(By.id("login-button"));
        txtUsername.sendKeys(username);
        txtPass.sendKeys(pass);
        bnbLogin.click();
    }
    public void  compareLable(String text){
        WebElement err = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals(text,err.getText());

    }
}
