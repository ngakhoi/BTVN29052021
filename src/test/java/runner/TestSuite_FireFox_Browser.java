package runner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSuite_FireFox_Browser {
    FirefoxDriver firefox;
    @Before
    public void setupDriver(){
        System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");
        firefox = new FirefoxDriver();

        firefox.get("https://www.saucedemo.com/");
        firefox.manage().window().maximize();
    }
    @Test
    //Login thành công
    public void testcase1(){
        login("standard_user", "secret_sauce");
        WebElement shoppingCard = firefox.findElement(By.id("shopping_cart_container"));
        Assert.assertTrue(shoppingCard.isDisplayed());
    }
    @Test
    //Sai username
    public void testcase2()
    {
        login("standard_user1", "secret_sauce");
        WebElement err = firefox.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",err.getText());
    }
    @Test
    //Sai pass
    public void testcase3()
    {
        login("standard_user", "secret_sauce1");
        WebElement err = firefox.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",err.getText());
    }
    @Test
    //Để trống username + pass
    public void testcase4()
    {
        login("", "");
        WebElement err = firefox.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username is required",err.getText());
    }
    @Test
    //Để trống username
    public void testcase5()
    {
        login("", "secret_sauce1");
        WebElement err = firefox.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Username is required",err.getText());
    }
    @Test
    //Để trống pass
    public void testcase6()
    {
        login("standard_user", "");
        WebElement err = firefox.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: Password is required",err.getText());
    }
    @After
    public void tearDown(){
        firefox.quit();
    }

    public void login(String username, String pass){
        WebElement txtUsername = firefox.findElement(By.id("user-name"));
        WebElement txtPass = firefox.findElement(By.id("password"));
        WebElement bnbLogin = firefox.findElement(By.id("login-button"));
        txtUsername.sendKeys(username);
        txtPass.sendKeys(pass);
        bnbLogin.click();
    }
}
