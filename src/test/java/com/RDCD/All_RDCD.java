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

    @Test(description = "This is for shomiti create(arthik totthadi) scenario", priority =8, enabled = false)
    public static void Arthik_Totthadi() throws InterruptedException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectRadioboxByName("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectByVisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-expenditure");

        SmallWait();
        FindElementByXpath("(.//*[@name='noOfShare'])[1]","1"); //noOfShare
        FindElementByXpath("(.//*[@name='savingsAmount'])[1]","5000"); //savingsAmount
        FindElementByXpath("(.//*[@name='loanOutstanding'])[1]","4000"); //loanOutstanding

        SmallWait();
        FindElementByXpath("(.//*[@name='noOfShare'])[2]","2"); //noOfShare
        FindElementByXpath("(.//*[@name='savingsAmount'])[2]","2000"); //savingsAmount
        FindElementByXpath("(.//*[@name='loanOutstanding'])[2]","1500"); //loanOutstanding

        SmallWait();
        FindElementByXpath("(.//*[@name='noOfShare'])[3]","1"); //noOfShare
        FindElementByXpath("(.//*[@name='savingsAmount'])[3]","3000"); //savingsAmount
        FindElementByXpath("(.//*[@name='loanOutstanding'])[3]","2000"); //loanOutstanding

        SmallWait();
        FindElementByXpath("(.//*[@name='noOfShare'])[4]","2"); //noOfShare
        FindElementByXpath("(.//*[@name='savingsAmount'])[4]","10000"); //savingsAmount
        FindElementByXpath("(.//*[@name='loanOutstanding'])[4]","5000"); //loanOutstanding

        SmallWait();
        FindElementByXpath("(.//*[@name='noOfShare'])[5]","2"); //noOfShare
        FindElementByXpath("(.//*[@name='savingsAmount'])[5]","7000"); //savingsAmount
        FindElementByXpath("(.//*[@name='loanOutstanding'])[5]","6000"); //loanOutstanding

        SmallWait();
        FindElementByXpath("(.//*[@name='noOfShare'])[6]","1"); //noOfShare
        FindElementByXpath("(.//*[@name='savingsAmount'])[6]","4000"); //savingsAmount
        FindElementByXpath("(.//*[@name='loanOutstanding'])[6]","5000"); //loanOutstanding

        /*SmallWait();
        FindElementByXpath("",""); //noOfShare
        FindElementByXpath("",""); //savingsAmount
        FindElementByXpath("",""); //loanOutstanding*/ //If member increases

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='হালনাগাদ করুন']")).click(); //Button_সংরক্ষন করুন

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/budget");
    }

    @Test(description = "This is for shomiti create(shomitir budget) scenario", priority =9, enabled = false)
    public static void Shomiti_Budget() throws InterruptedException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectRadioboxByName("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectByVisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/budget");

        SmallWait();
        SelectByVisibleText("budgetYear","2021-2022");

        //-------------- বর্তমান বছরের সমিতির বাজেট --------------// (Be Careful with Xpath, when increase or decrease)
        //---------------------- আয় ----------------------//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[1]","বাড়ি ভাড়া");
        FindElementByXpath("(.//*[@name='amount'])[1]","1500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[2]","বিক্রয়");
        FindElementByXpath("(.//*[@name='amount'])[2]","2500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[3]","চেক বই বিক্রয় ");
        FindElementByXpath("(.//*[@name='amount'])[3]","3500");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[1]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[4]","ফর্ম বিক্রয় ");
        FindElementByXpath("(.//*[@name='amount'])[4]","4500");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[1]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[5]","ভর্তি ফি ");
        FindElementByXpath("(.//*[@name='amount'])[5]","5500");

        //---------------------- ব্যয় ----------------------//

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[6]","বিদ্যুৎ খরচ");
        FindElementByXpath("(.//*[@name='amount'])[6]","500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[7]","ভাড়া");
        FindElementByXpath("(.//*[@name='amount'])[7]","1000");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[8]","পেনশন ও গ্র্যাচুইটি");
        FindElementByXpath("(.//*[@name='amount'])[8]","400");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[2]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[9]","সম্মানি ");
        FindElementByXpath("(.//*[@name='amount'])[9]","900");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[2]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[10]","বিনোদন খরচ ");
        FindElementByXpath("(.//*[@name='amount'])[10]","200");

        //-------------- পরবর্তী বছরের সমিতির বাজেট --------------//
        //---------------------- আয় ----------------------//
        LongWait();
        SelectByVisibleText("budgetFYear","2022-2023");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[11]","সবজি বিক্রয়  ");
        FindElementByXpath("(.//*[@name='amount'])[11]","2500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[12]","স্থায়ী সম্পত্তি বিক্রয় হতে লাভ ");
        FindElementByXpath("(.//*[@name='amount'])[12]","4500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[13]","চেক বই বিক্রয় ");
        FindElementByXpath("(.//*[@name='amount'])[13]","1000");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[3]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[14]","ফর্ম বিক্রয় ");
        FindElementByXpath("(.//*[@name='amount'])[14]","3500");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[3]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[15]","বিবিধ আয়");
        FindElementByXpath("(.//*[@name='amount'])[15]","5500");

        //---------------------- ব্যয় ----------------------//

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[16]","কাঁচামাল ক্রয়");
        FindElementByXpath("(.//*[@name='amount'])[16]","3500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[17]","ভাড়া");
        FindElementByXpath("(.//*[@name='amount'])[17]","5500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[18]","কৃষি খরচ ");
        FindElementByXpath("(.//*[@name='amount'])[18]","2500");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[4]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[19]","প্রভিডেন্ট ফান্ড/ ভবিষ্যৎ তহবিল ");
        FindElementByXpath("(.//*[@name='amount'])[19]","2500");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[4]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[20]","বাড়ি ভাড়া ভাতা");
        FindElementByXpath("(.//*[@name='amount'])[20]","1500");

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click();

        /*SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='হালনাগাদ করুন']")).click();

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='পরবর্তী পাতা']")).click();*/

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/income-expense");
    }

    @Test(description = "This is for shomiti create(shomitir aay_beey) scenario", priority =10, enabled = false)
    public static void Shomitir_Aay_Beey() throws InterruptedException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectRadioboxByName("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectByVisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/income-expense");

        //---------------------- আয় ----------------------// (Be Careful with Xpath, when increase or decrease)
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[1]","বাড়ি ভাড়া");
        FindElementByXpath("(.//*[@name='amount'])[1]","1500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[2]","ভর্তি ফি ");
        FindElementByXpath("(.//*[@name='amount'])[2]","5500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[3]","সুদ- সঞ্চয়ী হিসাব");
        FindElementByXpath("(.//*[@name='amount'])[3]","3500");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[1]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[4]","ফর্ম বিক্রয় ");
        FindElementByXpath("(.//*[@name='amount'])[4]","4500");

        //---------------------- ব্যয় ----------------------//
        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[5]","বিদ্যুৎ খরচ");
        FindElementByXpath("(.//*[@name='amount'])[5]","500");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[6]","ভাড়া");
        FindElementByXpath("(.//*[@name='amount'])[6]","1000");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[7]","পেনশন ও গ্র্যাচুইটি");
        FindElementByXpath("(.//*[@name='amount'])[7]","400");

        SmallWait();
        FindElementByXpath("(.//*[@data-testid='AddIcon'])[2]");

        SmallWait();
        SelectByVisibleTextXpath("(.//*[@name='details'])[8]","সম্মানি ");
        FindElementByXpath("(.//*[@name='amount'])[8]","900");

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/required-doc");
    }

    @Test(description = "This is for shomiti create(kagoj potradi) scenario", priority =11, enabled = true)
    public static void Kagoj_Potradi() throws InterruptedException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectRadioboxByName("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectByVisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/required-doc");

        /*SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/samity-reg-report");*/
    }

    @AfterClass
    @Test(enabled = false)
    public static void Close(){
        FirefoxQuit();
    }
}
