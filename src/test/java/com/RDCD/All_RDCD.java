package com.RDCD;

import com.BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
        driver.findElement(By.cssSelector(".MuiButton-containedPrimary")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiListItemButton-root:nth-child(3) .MuiTypography-root")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiCollapse-root .MuiButtonBase-root:nth-child(1) .MuiTypography-root")).click();

        /*String strUrl = driver.getCurrentUrl();
        if(strUrl.contains("name-clearance")){
            System.out.println("Test Passed");
        }
        else{
            Assert.fail();
        }*/

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

        driver.findElement(By.id("mui-19")).sendKeys("SROMOJIBI SOMOBAY 2");

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
    public static void PrathomikTottho() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButton-containedPrimary")));
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiListItemButton-root:nth-child(3) .MuiTypography-root")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiCollapse-root .MuiButtonBase-root:nth-child(2) .MuiTypography-root")).click();

        /*String strUrl = driver.getCurrentUrl();
        if(strUrl.contains("registration")){
            System.out.println("Test Passed");
        }
        else{
            Assert.fail();
        }*/

        /*//Abedon Type (Incomplete)
        List<WebElement> type = driver.findElements(By.name("samityLevel"));
        for(WebElement option : type){

            if(option.getAttribute("value").equalsIgnoreCase("2")){
                option.click();
            }
        }*/

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
        select1.selectByVisibleText("SROMOJIBI SOMOBAY 2");

        //Union
        WebElement union = driver.findElement(By.name("uniThanaPawNameBangla"));
        Select select2 = new Select(union);
        select2.selectByVisibleText("দাকোপ");

        //House no
        driver.findElement(By.name("villageArea")).sendKeys("বাড়ি নং-৩২, রাস্তা-০৯");
        driver.findElement(By.name("detailsAddress")).sendKeys("বাড়ি নং-৩২, রাস্তা-০৯");

        //Work Place
        driver.findElement(By.xpath("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']")).click();

        //Shomiti Totthadi - Create date
        driver.findElement(By.xpath("//*[@type='tel']")).sendKeys("03012020");

        //Admission fee
        driver.findElement(By.name("memberAdmissionFee")).sendKeys("1000");

        //No of Share
        driver.findElement(By.name("noOfShare")).sendKeys("100");

        //Share Price
        driver.findElement(By.name("sharePrice")).sendKeys("50");

        //Sold Share
        driver.findElement(By.name("soldShare")).sendKeys("5");

        //Shomiti Onnann Totthadi - Phone
        driver.findElement(By.name("phoneNo")).sendKeys("0273835618");

        //Mobile
        driver.findElement(By.name("mobileNo")).sendKeys("01738356180");

        //Email
        driver.findElement(By.name("emailId")).sendKeys("hasib.2030.hu@gmail.com");

        SmallWait();

        //Enterpriceid
        WebElement enterpriceid = driver.findElement(By.name("enterprisingId"));
        Select select3 = new Select(enterpriceid);
        select3.selectByValue("1");

        //Projecteid
        WebElement projectId = driver.findElement(By.name("projectId"));
        Select select4 = new Select(projectId);
        select4.selectByValue("3");

        //Website
        driver.findElement(By.name("website")).sendKeys("https://www.dddd.com");

        //Button
        driver.findElement(By.xpath("//*[@class='MuiButton-root MuiButton-contained MuiButton-containedSuccess MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-lq45lw' and @type='button']")).click();
    }

    @AfterClass
    @Test(enabled = false)
    public static void Close(){
        FirefoxQuit();
    }
}
