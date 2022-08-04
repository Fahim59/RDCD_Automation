package com.RDCD;

import com.BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
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
    public static void Login() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("saifur1985bd@gmail.com");
        driver.findElement(By.id("password")).sendKeys("12345");

        List<WebElement> user = driver.findElements(By.name("isAdmin"));

        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("1")){
                option.click();
            }
        }
        driver.findElement(By.cssSelector("button.MuiButton-root:nth-child(4)")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = true)
    public static void NameClearance() throws InterruptedException {

        Menu_NameClearance();

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

        //driver.findElement(By.id("mui-19")).sendKeys("Shomobay Shomiti");

        WebElement name = driver.findElement(By.id("mui-19"));
        String text = name.getAttribute("value");

        if(text.isEmpty()){
            name.sendKeys("Shomobay Shomiti");
        }
        else{
            name.clear();
            name.sendKeys("Shomobay Shomiti 1");
        }

        List<WebElement> user = driver.findElements(By.name("samityLevel"));

        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("P")){
                option.click();
            }
        }

        driver.findElement(By.cssSelector(".MuiButton-sizeMedium")).click();
    }

    @Test(description = "This is for name cancelling name clearance scenario", enabled = true, priority = 3)
    public static void CancelNameClearance() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LongWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr")).size();

        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr["+l+"]/td[5]")).getText();
            System.out.println(shomitiName);

            if(shomitiName.equals("Shomobay Shomiti 1")){

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

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario", priority =3, enabled = false)
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
        select1.selectByVisibleText("Shomobay Shomiti");

        //SmallWait();
        //Union
        WebElement union = driver.findElement(By.name("samityUniThanaPawIdType"));
        Select select2 = new Select(union);
        select2.selectByVisibleText("দাকোপ");

        //House no
        driver.findElement(By.name("samityDetailsAddress")).sendKeys("বাড়ি নং-৩২, রাস্তা-০৯");
        driver.findElement(By.name("detailsAddress")).sendKeys("বাড়ি নং-৩২, রাস্তা-০৯");

        //Work Place
        driver.findElement(By.xpath("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']")).click();

        //Shomiti Totthadi - Create date
        driver.findElement(By.xpath("//*[@type='tel']")).sendKeys("03012020");

        //Admission fee
        driver.findElement(By.name("memberAdmissionFee")).sendKeys("100");

        //No of Share
        driver.findElement(By.name("noOfShare")).sendKeys("100");

        //Share Price
        driver.findElement(By.name("sharePrice")).sendKeys("1000");

        //Sold Share
        driver.findElement(By.name("soldShare")).sendKeys("50");

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

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =4, enabled = false)
    public static void Lokkho_Uddessho(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButton-containedPrimary")));
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiListItemButton-root:nth-child(3) .MuiTypography-root")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiCollapse-root .MuiButtonBase-root:nth-child(2) .MuiTypography-root")).click();

        //Abedon Type (Incomplete)
        List<WebElement> type = driver.findElements(By.name("samityLevel"));
        for(WebElement option : type){

            if(option.getAttribute("value").equalsIgnoreCase("2")){
                option.click();
            }
        }

        WebElement shomitiname = driver.findElement(By.name("projectId"));
        Select select1 = new Select(shomitiname);
        select1.selectByVisibleText("SROMOJIBI SOMOBAY");

        driver.findElement(By.xpath("//*[@class='MuiButton-root MuiButton-contained MuiButton-containedSuccess MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-lq45lw' and @type='button']")).click();
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", priority =5, enabled = false)
    public static void Sodossho_Nibondhon() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButton-containedPrimary")));
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiListItemButton-root:nth-child(3) .MuiTypography-root")).click();
        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(1) .MuiCollapse-root .MuiButtonBase-root:nth-child(2) .MuiTypography-root")).click();

        SmallWait();
        //Abedon Type (Incomplete)
        List<WebElement> type = driver.findElements(By.name("samityLevel"));
        for(WebElement option : type){

            if(option.getAttribute("value").equalsIgnoreCase("2")){
                option.click();
            }
        }

        WebElement shomitiname = driver.findElement(By.name("projectId"));
        Select select1 = new Select(shomitiname);
        select1.selectByVisibleText("SROMOJIBI SOMOBAY");

        driver.findElement(By.xpath("//*[@class='MuiButton-root MuiButton-contained MuiButton-containedSuccess MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-lq45lw' and @type='button']")).click();

        driver.findElement(By.name("nid")).sendKeys("4655155903"); //NID

        driver.findElement(By.xpath("//*[@type='tel']")).sendKeys("03012000"); //DOB

        driver.findElement(By.name("memberName")).sendKeys("Akther Hamid Saymon"); //MemberName

        driver.findElement(By.name("memberNameBangla")).sendKeys("আখতার হামিদ সায়মন"); //MemberNameBangla

        driver.findElement(By.name("fatherName")).sendKeys("Mr. Abc"); //Father Name

        driver.findElement(By.name("motherName")).sendKeys("Mrs. Abc"); //Mother Name

        driver.findElement(By.name("mobileNo")).sendKeys("01768956730"); //Mobile

        //Gender
        List<WebElement> gender = driver.findElements(By.name("radioValue"));
        for(WebElement option : gender){

            if(option.getAttribute("value").equalsIgnoreCase("1")){
                option.click();
            }
        }

        driver.findElement(By.name("email")).sendKeys("saymon@erainfotechbd.com"); //Mobile

        //Education Level
        WebElement education = driver.findElement(By.name("educationLevelId"));
        Select select2 = new Select(education);
        select2.selectByValue("24");

        //Job Type
        WebElement job = driver.findElement(By.name("jobType"));
        Select select3 = new Select(job);
        select3.selectByValue("68");

        //Marital Status
        WebElement mstatus = driver.findElement(By.name("maritalStatusId"));
        Select select4 = new Select(mstatus);
        select4.selectByValue("2");

        //Present Address
        driver.findElement(By.xpath("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']")).click();

        //Sub District
        WebElement sdis = driver.findElement(By.name("upazila"));
        Select select5 = new Select(sdis);
        select5.selectByVisibleText("দাকোপ ");

        //Union
        WebElement union = driver.findElement(By.name("uniThanaPawNameBangla"));
        Select select6 = new Select(union);
        select6.selectByVisibleText("দাকোপ ");

        SmallWait();

        //Picture
        Actions builder = new Actions(driver);

        builder.moveToElement(driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root"))).click().build().perform();
        SmallWait();
        Runtime.getRuntime().exec("D:\\Intellij Files\\RDCD\\sodossho.exe");
        //SmallWait();

        builder.moveToElement(driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root"))).click().build().perform();
        SmallWait();
        Runtime.getRuntime().exec("D:\\Intellij Files\\RDCD\\sign.exe");
        //SmallWait();

        builder.moveToElement(driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root"))).click().build().perform();
        SmallWait();
        Runtime.getRuntime().exec("D:\\Intellij Files\\RDCD\\prottoyon.exe");

        //driver.findElement(By.xpath("//*[@class='MuiButton-root MuiButton-contained MuiButton-containedSuccess MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButtonBase-root  css-lq45lw' and @type='button']")).click();
    }

    @AfterClass
    @Test(enabled = false)
    public static void Close(){
        FirefoxQuit();
    }
}
