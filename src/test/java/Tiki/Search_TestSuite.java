package Tiki;

import org.junit.After;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Search_TestSuite {
    WebDriver driver;
    @Before
    public void setupDiver(){
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://tiki.vn/");
        driver.manage().window().maximize();
    }
    @Test
    // Xem chi tiết sản phẩm tìm kiếm
    public void Detail(){
        Search("ASUS");
        // Xem chi tiết sản phẩm
        WebElement detail = driver.findElement(By.xpath("//div//a[@href = '/laptop-asus-vivobook-s14-s433ea-am439t-core-i5-1135g7-8gb-ddr4-3200mhz-512gb-ssd-m-2-pcie-g3x2-14-fhd-ips-win10-hang-chinh-hang-p81299891.html?src=ss-organic']"));
//        WebElement detail = driver.findElement(By.xpath("//div//a[@data-view-index = 2]"));

        detail.click();
        // Tên sản phẩm
        WebElement name = driver.findElement(By.xpath("//div//h1[@class = 'title']"));
        Assert.assertTrue(name.isDisplayed());
        // Gía sản phẩm
        WebElement price = driver.findElement(By.xpath("//div//span[@class = 'product-price__current-price']"));
        Assert.assertTrue(price.isDisplayed());
        // Hình ảnh sản phẩm
        WebElement img = driver.findElement(By.xpath("//div//img[@src = 'https://salt.tikicdn.com/cache/w444/ts/product/39/cd/24/981a83d9343fa55f98a6f5bac1d2d312.jpg']"));
        Assert.assertTrue(img.isDisplayed());
        // Thời gian bảo hành
        WebElement warranty = driver.findElement(By.xpath("//div//span[text()='Thời gian bảo hành']"));
        Assert.assertTrue(warranty.isDisplayed());
        // Hình thức bảo hành
        WebElement warranty1 = driver.findElement(By.xpath("//div//span[text()='Hình thức bảo hành']"));
        Assert.assertTrue(warranty1.isDisplayed());
        WebElement btn_add_to_cart = driver.findElement(By.xpath("//div//button[@class = 'btn btn-add-to-cart']"));
        Assert.assertTrue(btn_add_to_cart.isDisplayed());

    }
    // Hàm tìm kiếm
    public void Search(String key){
        WebElement input = driver.findElement(By.xpath("//div[@class = 'FormSearch__Form-sc-1fwg3wo-1 gUJHDL']//input"));
        WebElement bnbSearch = driver.findElement(By.xpath("//div//button[@class='FormSearch__Button-sc-1fwg3wo-3 knOqgr']"));
        input.sendKeys(key);
        bnbSearch.click();
        // Độ trễ
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Bấm button Bỏ qua
        WebElement bnbCancel = driver.findElement(By.xpath("//div//button[@class='align-right secondary slidedown-button']"));
        bnbCancel.click();
    }

    @After
    public void tearDown(){driver.quit();}
}
