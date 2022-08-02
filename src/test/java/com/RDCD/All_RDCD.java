package com.RDCD;

import com.BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Listeners(Screenshot.class)
public class All_RDCD extends BaseClass {

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://10.11.200.30:5001/login");
    }

    /*@Test(description = "", priority =, enabled = true)
    public static void xyz(){

    }*/

    @Test(description = "This is for login scenario", priority = 1, enabled = true, alwaysRun = true)
    public static void Login(){
        driver.findElement(By.id("email")).sendKeys("saifur1985bd@gmail.com");
        driver.findElement(By.id("password")).sendKeys("1234");

        List<WebElement> user = driver.findElements(By.name("isAdmin"));

        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("1")){
                option.click();
            }
        }
        driver.findElement(By.cssSelector("button.MuiButton-root:nth-child(4)")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        /*String strUrl = driver.getCurrentUrl();
        if(strUrl.contains("dashboard")){
            System.out.println("Login Test Passed");
        }
        else{
            Assert.fail();
        }*/
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = false)
    public static void NameClearance() throws InterruptedException {
        String strUrl = driver.getCurrentUrl();
        if(strUrl.contains("name-clearance")){
            System.out.println("Test Passed");
        }
        else{
            Assert.fail();
        }
        //--------------------------------------------------------------------------------------//
        driver.findElement(By.cssSelector(".MuiButton-containedPrimary")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiListItemButton-root:nth-child(3) .MuiTypography-root")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiCollapse-root .MuiButtonBase-root:nth-child(1) .MuiTypography-root")).click();

        //Divisions
        WebElement division = driver.findElement(By.name("division"));
        Select select1 = new Select(division);
        select1.selectByValue("2451");

        //District
        WebElement district = driver.findElement(By.name("district"));
        Select select2 = new Select(district);
        select2.selectByValue("5104");

        //Office Name
        WebElement office = driver.findElement(By.id("mui-17"));
        Select select3 = new Select(office);
        select3.selectByValue("216");

        //Organization Category
        WebElement org = driver.findElement(By.id("mui-18"));
        Select select4 = new Select(org);
        select4.selectByValue("2");

        driver.findElement(By.id("mui-19")).sendKeys("SROMOJIBI SOMOBAY");

        List<WebElement> user = driver.findElements(By.name("samityLevel"));

        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("P")){
                option.click();
            }
        }

        driver.findElement(By.cssSelector(".MuiButton-sizeMedium")).click();

        SmallWait();
    }

    @Test(description = "This is for name cancelling name clearance scenario", enabled = false)
    public static void CancelNameClearance() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String strUrl = driver.getCurrentUrl();
        if(strUrl.contains("name-clearance")){
            System.out.println("Test Passed");
        }
        else{
            Assert.fail();
        }

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr")).size();

        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr["+l+"]/td[5]")).getText();
            System.out.println(shomitiName);

            if(shomitiName.equals("SROMOJIBI SOMOBAY")){
                //driver.findElement(By.cssSelector("button.MuiButton-outlined:nth-child(2)")).click();

                WebElement cancel = driver.findElement(By.cssSelector("button.MuiButton-outlined:nth-child(2)"));
                JavascriptExecutor executor1 = (JavascriptExecutor)driver;
                executor1.executeScript("arguments[0].click();", cancel);

                SmallWait();

                WebElement confirm = driver.findElement(By.cssSelector(".swal2-confirm"));
                JavascriptExecutor executor2 = (JavascriptExecutor)driver;
                executor2.executeScript("arguments[0].click();", confirm);

                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".swal2-confirm"))).click();
            }
            else{
                System.out.println("Shomiti doesnot match");
            }
        }
    }

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario", priority =3, enabled = true)
    public static void PrathomikTottho(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButton-containedPrimary")));

        //driver.findElement(By.cssSelector(".MuiButton-containedPrimary")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiListItemButton-root:nth-child(3) .MuiTypography-root")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiCollapse-root .MuiButtonBase-root:nth-child(2) .MuiTypography-root")).click();

        String strUrl = driver.getCurrentUrl();
        if(strUrl.contains("registration")){
            System.out.println("Test Passed");
        }
        else{
            Assert.fail();
        }

        //Shomiti Level
        List<WebElement> user = driver.findElements(By.name("samityLevel"));
        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("P")){
                option.click();
            }
        }

        //Shomiti Name
        WebElement shomitiname = driver.findElement(By.name("samityName"));
        Select select1 = new Select(shomitiname);
        select1.selectByVisibleText("SROMOJIBI SOMOBAY");

        //Union
        WebElement union = driver.findElement(By.name("uniThanaPawNameBangla"));
        Select select2 = new Select(union);
        select2.selectByVisibleText("দাকোপ");
    }

    @AfterClass
    @Test(enabled = false)
    public static void Close(){
        FirefoxQuit();
    }
}