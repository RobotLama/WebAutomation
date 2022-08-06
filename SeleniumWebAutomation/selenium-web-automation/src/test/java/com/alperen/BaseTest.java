package com.alperen;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {

    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    
    public WebDriver driver;

    // Set property for WebDriver
    @BeforeAll
    public void setProperty()
    {
        System.setProperty("webdriver.chrome.driver", "/Users/alperen/Desktop/SeleniumWebAutomation/chromedriver");
        driver = new ChromeDriver();
    }

    // Go Homepage
    @Test
    @Order(1)
    public void navSite()
    {
        driver.get("https://www.kitapyurdu.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Check Homepage with Title
    @Test
    @Order(2)
    public void checkHomepage()
    {
        String siteTitle = driver.getTitle();
        Assertions.assertEquals(siteTitle , "Kitapyurdu, Kitapla buluşmanın en kolay yolu");
    }

    // Search "roman" keyword (read from data.csv) in Searchbox and Enter
    @Test
    @Order(3)
    public void checkSearch()
    {
        homePage = new HomePage(driver);
        homePage.search();
    }

    // Select one of the books and add to the cart
    @Test
    @Order(4)
    public void addCart()
    {
        productPage = new ProductPage(driver);
        productPage.addCart();
    }

    // Check the value of cart icon and go to the cart
    @Test
    @Order(5)
    public void checkCart()
    {
        String count = productPage.checkCart();
        Assertions.assertEquals(count , "1");
        productPage.navCartPage();
    }

    // Increase the quantity of the item
    @Test
    @Order(6)
    public void increaseQuantity()
    {
        cartPage = new CartPage(driver);
        cartPage.increaseQuantity();
    }

    // Check the "Sepetiniz güncelleniyor!" pop-up
    @Test
    @Order(7)
    public void checkRefresh()
    {
        WebElement refreshPopup = driver.findElement(By.id("swal2-title"));
        WebElement refreshPopupIsActive = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(refreshPopup));

        Assertions.assertEquals(refreshPopupIsActive.getText() , "Sepetiniz güncelleniyor!");
    }

    // Click the remove the item button
    @Test
    @Order(8)
    public void removeProduct()
    {
        WebElement removeProduct = driver.findElement(By.className("remove"));
        removeProduct.click();
    }
    

    // Check the cart is empty
    @Test
    @Order(9)
    public void checkEmpty()
    {
        WebElement removeProduct = driver.findElement(By.id("cart-items"));
        Boolean removeProductIsActive = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElement(removeProduct, "0"));

        removeProduct.getText();
        Assertions.assertTrue(removeProductIsActive);
    }

    @AfterAll
    public void quitDriver()
    {
        driver.close();
    }

    

}
