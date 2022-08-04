package com.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseClass {
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, IOException, NotFoundException, AWTException {}

    public static void FirefoxLaunch(){WebDriverManager.firefoxdriver().setup();driver = new FirefoxDriver();}

    public static void FirefoxQuit(){driver.quit();}

    public static void SmallWait() throws InterruptedException {Thread.sleep(2000);}

    public static void LongWait() throws InterruptedException {Thread.sleep(4000);}

    public static void OpenWebsite(String Url){driver.get(Url);}

    public static void Screenshot(String methodName) throws IOException {

        File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            //FileUtils.copyFile(image,new File("./Screenshot/image.png"));
            FileUtils.copyFile(image,new File("./Screenshot/" + methodName + "-" + ".jpg"));
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }

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

    public static void Incomplete_Apply() throws InterruptedException {
        SmallWait();
        List<WebElement> type = driver.findElements(By.name("samityLevel"));
        for(WebElement option : type){

            if(option.getAttribute("value").equalsIgnoreCase("2")){
                option.click();
            }
        }
    }
}
