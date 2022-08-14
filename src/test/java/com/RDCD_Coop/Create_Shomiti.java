package com.RDCD_Coop;

import com.BaseClass.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Listeners(Screenshot.class)
public class Create_Shomiti extends BaseClass {
    public static String aShomitiName = "Shomobay Shomiti"; //First name
    public static String dShomitiName = "Shomobay Shomiti 1"; //If same name available
    public static String sname;

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        //OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
        OpenWebsite("http://10.11.200.30:5001/login");
    }

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true)
    public static void Login() throws InterruptedException {
        //LongWait();
        //CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/login");

        FindElementByID_Details("email","saifur1985bd@gmail.com");
        FindElementByID_Details("password","12345");
        SelectBy_Name_Radiobox("isAdmin", "1");

        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)"); //Login_Button

        //LongWait();
        //CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = true)
    public static void NameClearance() throws InterruptedException {
        Menu_NameClearance();

        //LongWait();
        //CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/name-clearance");

        SelectBy_Name_VisibleText("division","খুলনা"); //Division
        SelectBy_Name_VisibleText("district","খুলনা"); //District
        SelectBy_Name_VisibleText("upazila","উপজেলা সমবায় অফিস, দাকোপ, খুলনা"); //Office Name
        SelectBy_Name_VisibleText("samityTypeId","মৎস্যজীবি বা মৎস্যচাষী সমবায় সমিতি"); //Organization Category

        WebElement name = driver.findElement(By.name("samityName"));
        String text = name.getAttribute("value");

        if(text.isEmpty()){
            name.sendKeys(aShomitiName);
            sname = aShomitiName;

            SmallWait();
            if(driver.findElements(By.xpath("//span[text()='সমিতিটির নাম বিদ্যমান রয়েছে']")).isEmpty()){
                System.out.println("Duplicate name not available");
            }
            else{
                name.clear();
                System.out.println("Duplicate name available");
                name.sendKeys(dShomitiName);
                sname = dShomitiName;
            }
        }
        else{
            name.clear();
            name.sendKeys(aShomitiName);
            sname = aShomitiName;

            SmallWait();
            if(driver.findElements(By.xpath("//span[text()='সমিতিটির নাম বিদ্যমান রয়েছে']")).isEmpty()){
                System.out.println("Duplicate name not available");
            }
            else{
                name.clear();
                System.out.println("Duplicate name available");
                name.sendKeys(dShomitiName);
                sname = dShomitiName;
            }
        }

        List<WebElement> user = driver.findElements(By.name("samityLevel"));
        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("P")){
                if(!option.isSelected()){
                    option.click();
                }
            }
        }

        FindElementByCssSelector_Click(".MuiButton-sizeMedium"); //Submit Button

        SmallWait();
        String textButton = driver.findElement(By.cssSelector(".MuiButton-sizeMedium")).getText();

        if(!textButton.equalsIgnoreCase("সংরক্ষন সফল ভাবে হয়েছে")){
            System.out.println("Name Clearance Failed");
            Assert.fail();
        }
        else{
            System.out.println("Name Clearance Passed");
        }
    }

    @Test(description = "This is for cancelling name clearance scenario", priority = 3, enabled = false)
    public static void CancelNameClearance() throws InterruptedException {
        //LongWait();
        //CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/name-clearance");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LongWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr")).size();

        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();
            //System.out.println(shomitiName);
            //System.out.println("Value of l is " +l);

            if(shomitiName.equals(sname)){

                WebElement cancel = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr["+l+"]/td[6]/button[2]"));
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

    @Test(description = "This is shomiti approve scenario", priority =4, enabled = false)
    public static void Shomiti_Approve() throws InterruptedException {
        SmallWait();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AccountCircleIcon'])[1]");

        SmallWait();
        FindElementByXpath_Click("//li[text()=' লগ-আউট']");

        SmallWait();
        FindElementByID_Details("email","dacope_uco");
        FindElementByID_Details("password","1234");
        SelectBy_Name_Radiobox("isAdmin","2");
        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");

        //LongWait();
        //CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");

        SmallWait();
        Menu_Approve();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        //System.out.println(tr);
        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();
            //System.out.println(shomitiName);
            //System.out.println(l);

            if(shomitiName.equalsIgnoreCase(sname)){

                SmallWait();
                FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[4]/button");

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                Scroll_Down("serviceActionId");

                LongWait();
                SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

                LongWait();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']"))).click();
            }
            else{
                System.out.println("Shomiti doesnot exist");
            }
        }

        //LongWait();
        //CheckNextUrl("http://rdcd.erainfotechbd.com:5005/approval");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AccountCircleIcon'])[1]");

        SmallWait();
        FindElementByXpath_Click("//li[text()=' লগ-আউট']");

        SmallWait();
        Login();

        //LongWait();
        //CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
    }
}
