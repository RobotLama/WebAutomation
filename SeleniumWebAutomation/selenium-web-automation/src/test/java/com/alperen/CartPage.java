package com.alperen;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    By quantityButton = By.xpath("//a[@title='Around The World In Eighty Days (İngilizce Roman)']");


    public CartPage(WebDriver driver) 
    {
        super(driver);
    }

    public void increaseQuantity()
    {
        WebElement quantityButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='quantity']")));
        quantityButton.click();
        quantityButton.clear();

        quantityButton.sendKeys("2");
        new Actions(driver).sendKeys(Keys.ENTER).perform();;
    }


}
