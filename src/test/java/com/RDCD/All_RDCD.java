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
import java.util.*;

@Listeners(Screenshot.class)
public class All_RDCD extends BaseClass {

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        //OpenWebsite("http://10.11.200.30:5001/login");
        OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
    }

    /*@Test(description = "", priority =, enabled = true)
    public static void xyz(){

    }*/

    @Test(description = "This is for login scenario", priority = 1, enabled = true, alwaysRun = true)
    public static void Login() {
        driver.findElement(By.id("email")).sendKeys("saifur1985bd@gmail.com");
        driver.findElement(By.id("password")).sendKeys("12345");
        SelectRadioboxByName("isAdmin", "1");

        driver.findElement(By.cssSelector("button.MuiButton-root:nth-child(4)")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = false)
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

    @Test(description = "This is for cancelling name clearance scenario", enabled = false, priority = 3)
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

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario", priority =4, enabled = false)
    public static void PrathomikTottho() throws InterruptedException {
        Menu_ShomitiCreate();

        SelectRadioboxByName("samityLevel","P"); //Shomiti Level

        SmallWait();
        SelectByVisibleText("samityName","Shomobay Shomiti"); //Shomiti Name

        SmallWait();
        SelectByVisibleText("samityUniThanaPawIdType","দাকোপ"); //Union

        SmallWait();
        FindElementByName("samityDetailsAddress","বাড়ি নং-৩২, রাস্তা-০৯"); //Address

        SmallWait();
        SelectByVisibleText("memberAreaType","গ্রাম/মহল্লা");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='samityUniThanaPawIdType'])[2]","দাকোপ ");

        SmallWait();
        SelectCheckboxByXpath("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Work Place

        SmallWait();
        WebElement date = driver.findElement(By.xpath("//*[@type='tel']")); //Create date
        date.clear();
        date.sendKeys("03012020");

        SmallWait();
        FindElementByName("memberAdmissionFee", "100"); //Admission fee

        SmallWait();
        FindElementByName("noOfShare","100");//No of Share

        SmallWait();
        FindElementByName("sharePrice", "500"); //Share Price

        SmallWait();
        FindElementByName("soldShare","10");

        SmallWait();
        FindElementByName("phoneNo","0273835618"); //Phone

        SmallWait();
        FindElementByName("mobileNo","01738356180"); //Mobile

        SmallWait();
        FindElementByName("emailId","hasib.2030.hu@gmail.com"); //Email

        SmallWait();
        SelectByVisibleText("enterprisingId","সমবায় অধিদপ্তর"); //Enterpriceid

        SmallWait();
        SelectByVisibleTextXpath("//select[@name='projectId']","আদর্শ গ্রাম-২ প্রকল্প"); //Projecteid

        SmallWait();
        FindElementByName("website","https://www.dddd.com"); //Website

        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']")).click(); //Button
    }

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =5, enabled = false)
    public static void Lokkho_Uddessho() throws InterruptedException {
        //SmallWait();
        //CheckUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/add-bye-laws");

        Menu_ShomitiCreate();

        SmallWait();
        SelectRadioboxByName("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectByVisibleText("projectId","Shomobay Shomiti");

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click(); //Button
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", priority =6, enabled = false)
    public static void Sodossho_Nibondhon() throws InterruptedException, IOException {
        //SmallWait();
        //CheckUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-registration");

        Menu_ShomitiCreate();

        SmallWait();
        SelectRadioboxByName("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectByVisibleText("projectId","Shomobay Shomiti");

        SmallWait();
        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr")).size();
        System.out.println(tr);

        if(tr < 6){
            driver.findElement(By.xpath("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']")).click();
        }

        SmallWait();
        //SelectRadioboxByName("NidOrBrn","1"); //Nid
        SelectRadioboxByName("NidOrBrn","2"); //BirthRegNo

        SmallWait();
        //FindElementByName("nid","4655155904"); //NID
        FindElementByName("brn","19994578963254782"); //BirthRegNo

        SmallWait();
        FindElementByXpath("//*[@type='tel']","03011999"); //DOB

        SmallWait();
        FindElementByName("memberName","Asad Haq"); //MemberName

        SmallWait();
        FindElementByName("memberNameBangla","আসাদ হক"); //MemberNameBangla

        SmallWait();
        FindElementByName("fatherName","Mr. Abc"); //Father Name

        SmallWait();
        FindElementByName("motherName","Mrs. Abc"); //Mother Name

        SmallWait();
        FindElementByName("mobileNo","01968956730"); //Mobile

        SmallWait();
        SelectRadioboxByName("radioValue","1"); //Gender

        SmallWait();
        FindElementByName("email","asad@erainfotechbd.com");

        SmallWait();
        SelectByVisibleText("educationLevelId","স্নাতকোত্তর"); //Education Level //এইচ.এস.সি, স্নাতক, স্নাতকোত্তর, পঞ্চম শ্রেণী, অষ্টম শ্রেণী

        SmallWait();
        SelectByVisibleText("jobType","শিক্ষক"); //Job Type //সরকারি কর্মচারী, কৃষক, বেসরকারী কর্মচারী, গৃহিনী, স্বনির্ভর, অন্যান্য

        SmallWait();
        SelectByVisibleText("maritalStatusId","- বিবাহিত -"); //Marital Status //- অবিবাহিত -, - বিধবা -, - তালাকপ্রাপ্ত -
        FindElementByName("spouseName","Mrs.Xyz"); //Only for Married

        SmallWait();
        SelectCheckboxByXpath("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Present Address

        SmallWait();
        SelectByVisibleText("upazila","দাকোপ"); //Sub District

        SmallWait();
        SelectByVisibleText("uniThanaPawNameBangla","দাকোপ"); //Union

        SmallWait();
        FindElementByName("villageArea","বাড়ি নং-৩২, রাস্তা-০৯");

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","F:\\RDCD_Automation\\sodossho.exe"); //Sodossho_Picture

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\sign.exe"); //Sign_Picture

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\prottoyon.exe"); //Authentication_Picture

        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='আগের পাতায়']")).click(); //Button_আগের পাতায়
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='বন্ধ করুন']")).click(); //Button_বন্ধ করুন
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click(); //Button_সংরক্ষন করুন
    }

    @Test(description = "This is for shomiti create(committee bebosthapona) scenario", priority =7, enabled = false)
    public static void Committee_Bebosthapona() throws InterruptedException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectRadioboxByName("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectByVisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/designation");

        SmallWait();
        SelectByVisibleText("organizerp","সাইফুর রহমান"); //Shongothok

        SmallWait();
        SelectByVisibleText("communicationP","হামিদ সায়মন"); //Jogajoger Bekti

        SmallWait();
        SelectByVisibleText("signingp","আসাদ হক"); //Kndriyo Shomir Pokkhe Sakkhorito Bekti

        SmallWait();
        SelectByVisibleTextXpath("//select[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u' and @name='']","৬ জন"); //Member //৯ জন //১২ জন
        //================================================================================================//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedId'])[1]","সাইফুর রহমান");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedDesig'])[1]","সভাপতি");
        //===================================================//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedId'])[2]","হামিদ সায়মন");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedDesig'])[2]","সহ-সভাপতি");
        //===================================================//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedId'])[3]","আসাদ হক");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedDesig'])[3]","সাধারন সম্পাদক");
        //===================================================//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedId'])[4]","  আব্দুল কালাম");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedDesig'])[4]","যুগ্ম সম্পাদক");
        //===================================================//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedId'])[5]","হাসনাত আলী");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedDesig'])[5]","প্রচার সম্পাদক");
        //===================================================//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedId'])[6]","আবির হাসান");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='selectedDesig'])[6]","কোষাধ্যক্ষ");
        //===================================================//
        /*SmallWait();
        SelectByVisibleTextXpath("","");*/ //If the committee member number increases

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click(); //Button_সংরক্ষন করুন

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-expenditure");
    }

    @Test(description = "This is for shomiti create(arthik totthadi) scenario", priority =8, enabled = true)
    public static void xyz(){

    }

    @AfterClass
    @Test(enabled = false)
    public static void Close(){
        FirefoxQuit();
    }
}
