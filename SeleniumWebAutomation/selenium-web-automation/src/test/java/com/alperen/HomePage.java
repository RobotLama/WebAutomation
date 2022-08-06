package com.alperen;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

    By searchBoxLocator = By.id("search-input");

    String path = "/Users/alperen/Desktop/SeleniumWebAutomation/selenium-web-automation/src/test/java/com/alperen/data.csv";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getSearchKey()
    {
        Path key = Paths.get(path);
        try {
            BufferedReader bReader = Files.newBufferedReader(key);
            String keyValue = bReader.readLine();
            return keyValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void search()
    {
        String keyValue = getSearchKey();
        WebElement searchBox = driver.findElement(searchBoxLocator);
        searchBox.click();
        searchBox.sendKeys(keyValue);
        new Actions(driver).sendKeys(Keys.ENTER).perform();;
    }

}
