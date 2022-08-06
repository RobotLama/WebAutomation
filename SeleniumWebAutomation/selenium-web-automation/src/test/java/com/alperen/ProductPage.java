package com.alperen;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    By selectProduct = By.xpath("//a[@title='1984 (Grafik Roman) (Karton Kapak)']");
    By addCart = By.id("button-cart");
    By cart = By.id("cart-items");
    By navCart = By.id("js-cart");


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addCart()
    {
        WebElement selectProduct = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='1984 (Grafik Roman) (Karton Kapak)']")));

        //WebElement selectProduct = driver.findElement(By.xpath("//a[@title='Around The World In Eighty Days (İngilizce Roman)']")       );
        selectProduct.click();
        WebElement add = driver.findElement(addCart);
        add.click();
    }

    public String checkCart()
    {
        Boolean checkCount = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.textToBe(cart, "1"));
        if (checkCount)
        {
        WebElement check = driver.findElement(cart);
        return check.getText();

        }
        return null;
    }

    public void navCartPage()
    {
        WebElement cartButton = driver.findElement(cart);
        cartButton.click();
        WebElement secondCartButton = driver.findElement(navCart);

        WebElement cartButtonIsActive = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(secondCartButton));
        
        cartButtonIsActive.getText();
        cartButtonIsActive.click();
    }
    
}
