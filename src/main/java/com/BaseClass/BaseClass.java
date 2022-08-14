package com.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseClass {
    public static WebDriver driver;

    public static void main(String[] args) throws NotFoundException {}

    public static void FirefoxLaunch(){WebDriverManager.firefoxdriver().setup();driver = new FirefoxDriver();}
    public static void FirefoxQuit(){driver.quit();}
    //--------------------------------------------------------------------------------------------------------//
    public static void SmallWait() throws InterruptedException {Thread.sleep(2000);}
    public static void LongWait() throws InterruptedException {Thread.sleep(4000);}
    //--------------------------------------------------------------------------------------------------------//
    public static void OpenWebsite(String Url){driver.get(Url);}
    //--------------------------------------------------------------------------------------------------------//
    public static void Screenshot(String methodName) throws IOException {

        File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(image,new File("./Screenshot/" + methodName + "-" + ".jpg"));
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    @Test
    public static void CheckCurrentUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        if(!currentUrl.equals(url)){
            System.out.println("Current Url Mismatched, Test Failed");
            //System.exit(0);
            Assert.fail();
        }
        else{
            System.out.println("Current Url Matched, Test Passed");
        }
    }

    @Test
    public static void CheckNextUrl(String url){
        String nextUrl = driver.getCurrentUrl();
        System.out.println(nextUrl);
        if(!nextUrl.equals(url)){
            System.out.println("Next Url Mismatched, Test Failed");
            //System.exit(0);
            Assert.fail();
        }
        else{
            System.out.println("Next Url Matched, Test Passed");
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void Menu_NameClearance() throws InterruptedException {
        SmallWait();

        driver.findElement(By.xpath("//span[text()='সমিতি ব্যবস্থাপনা']")).click();
        driver.findElement(By.xpath("//span[text()='নেম ক্লিয়ারেন্স']")).click();
    }
    public static void Menu_ShomitiCreate() throws InterruptedException {
        SmallWait();

        driver.findElement(By.xpath("//span[text()='সমিতি ব্যবস্থাপনা']")).click();
        driver.findElement(By.xpath("//span[text()='সমিতি নিবন্ধনের আবেদন']")).click();
    }
    public static void Menu_Approve() throws InterruptedException {
        SmallWait();

        driver.findElement(By.xpath("//span[text()='অনুমোদন']")).click();
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByID_Click(String id){driver.findElement(By.id(id)).click();}
    public static void FindElementByID_Details(String id, String details){
        WebElement element = driver.findElement(By.id(id));
        String text = element.getAttribute("value");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByName_Click(String name){driver.findElement(By.name(name)).click();}
    public static void FindElementByName_Details(String name, String details){
        WebElement element = driver.findElement(By.name(name));
        String text = element.getAttribute("value");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByXpath_Click(String xpath){driver.findElement(By.xpath(xpath)).click();}
    public static void FindElementByXpath_Details(String xpath, String details){
        WebElement element = driver.findElement(By.xpath(xpath));
        String text = element.getAttribute("value");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByCssSelector_Click(String cssSelector){driver.findElement(By.cssSelector(cssSelector)).click();}
    //--------------------------------------------------------------------------------------------------------//
    public static void SelectBy_Name_VisibleText(String name, String text){
        WebElement element = driver.findElement(By.name(name));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void SelectBy_Xpath_VisibleText(String xpath, String text){
        WebElement element = driver.findElement(By.xpath(xpath));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void SelectBy_Xpath_Checkbox(String xpath){
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        if(!checkbox.isSelected()){
            checkbox.click();
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void SelectBy_Name_Checkbox(String name){
        WebElement checkbox = driver.findElement(By.name(name));
        if(!checkbox.isSelected()){
            checkbox.click();
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void SelectBy_Name_Radiobox(String name, String value){
        List<WebElement> user = driver.findElements(By.name(name));
        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase(value)){
                if(!option.isSelected()){
                    option.click();
                }
            }
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void UploadPicture(String cssSelector, String path) throws InterruptedException, IOException {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.cssSelector(cssSelector))).click().build().perform();
        LongWait();
        Runtime.getRuntime().exec(path);
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void Scroll_Down(String name){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.name(name));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}
