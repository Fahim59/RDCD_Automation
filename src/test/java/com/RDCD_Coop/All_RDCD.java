package com.RDCD_Coop;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public static String aShomitiName = "Shomobay Shomiti"; //First name
    public static String dShomitiName = "Shomobay Shomiti 1"; //If same name available

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
        //OpenWebsite("http://10.11.200.30:5001/login");
    }

    @Test(description = "This is for login scenario", priority = 1, enabled = true, alwaysRun = true)
    public static void Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/login");

        FindElementByID_Details("email","saifur1985bd@gmail.com");
        FindElementByID_Details("password","12345");
        SelectBy_Name_Radiobox("isAdmin", "1");

        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)"); //Login_Button

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = true)
    public static void NameClearance() throws InterruptedException {
        Menu_NameClearance();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/name-clearance");

        SelectBy_Name_VisibleText("division","খুলনা"); //Division
        SelectBy_Name_VisibleText("district","খুলনা"); //District
        SelectBy_Name_VisibleText("upazila","উপজেলা সমবায় অফিস, দাকোপ, খুলনা"); //Office Name
        SelectBy_Name_VisibleText("samityTypeId","মৎস্যজীবি বা মৎস্যচাষী সমবায় সমিতি"); //Organization Category

        WebElement name = driver.findElement(By.name("samityName"));
        String text = name.getAttribute("value");

        if(text.isEmpty()){
            name.sendKeys(aShomitiName);

            SmallWait();
            if(driver.findElements(By.xpath("//span[text()='সমিতিটির নাম বিদ্যমান রয়েছে']")).isEmpty()){
                System.out.println("Duplicate name not available");
            }
            else{
                name.clear();
                System.out.println("Duplicate name available");
                name.sendKeys(dShomitiName);
            }
        }
        else{
            name.clear();
            name.sendKeys(aShomitiName);

            SmallWait();
            if(driver.findElements(By.xpath("//span[text()='সমিতিটির নাম বিদ্যমান রয়েছে']")).isEmpty()){
                System.out.println("Duplicate name not available");
            }
            else{
                name.clear();
                System.out.println("Duplicate name available");
                name.sendKeys(dShomitiName);
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

    @Test(description = "This is for cancelling name clearance scenario", enabled = true, priority = 3)
    public static void CancelNameClearance() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/name-clearance");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LongWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr")).size();

        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();
            //System.out.println(shomitiName);
            //System.out.println("Value of l is " +l);

            if(shomitiName.equals(dShomitiName)){

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

    @Test(description = "This is shomiti approve scenario", priority =3, enabled = false)
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

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");

        SmallWait();
        Menu_Approve();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        //System.out.println(tr);
        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();
            //System.out.println(shomitiName);
            //System.out.println(l);

            if(shomitiName.equalsIgnoreCase(aShomitiName)){

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

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/approval");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AccountCircleIcon'])[1]");

        SmallWait();
        FindElementByXpath_Click("//li[text()=' লগ-আউট']");

        SmallWait();
        Login();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
    }

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario", priority =4, enabled = false)
    public static void PrathomikTottho() throws InterruptedException {
        Menu_ShomitiCreate();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/registration");

        SelectBy_Name_Radiobox("samityLevel","P"); //Shomiti Level

        SmallWait();
        SelectBy_Name_VisibleText("samityName", aShomitiName); //Shomiti Name

        SmallWait();
        SelectBy_Name_VisibleText("samityUniThanaPawIdType","দাকোপ"); //Union

        SmallWait();
        FindElementByName_Details("samityDetailsAddress","বাড়ি নং-৩২, রাস্তা-০৯"); //Address

        SmallWait();
        SelectBy_Name_VisibleText("memberAreaType","গ্রাম/মহল্লা");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[2]","দাকোপ ");

        SmallWait();
        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Work Place

        SmallWait();
        FindElementByXpath_Date("//*[@type='tel']","03012020");//Create date

        SmallWait();
        FindElementByName_Details("memberAdmissionFee", "100"); //Admission fee

        SmallWait();
        FindElementByName_Details("noOfShare","100");//No of Share

        SmallWait();
        FindElementByName_Details("sharePrice", "500"); //Share Price

        SmallWait();
        FindElementByName_Details("soldShare","10");

        SmallWait();
        FindElementByName_Details("phoneNo","0273835618"); //Phone

        SmallWait();
        FindElementByName_Details("mobileNo","01738356180"); //Mobile

        SmallWait();
        FindElementByName_Details("emailId","hasib.2030.hu@gmail.com"); //Email

        SmallWait();
        SelectBy_Name_VisibleText("enterprisingId","সমবায় অধিদপ্তর"); //Enterpriceid

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@name='projectId']","আদর্শ গ্রাম-২ প্রকল্প"); //Projecteid

        SmallWait();
        FindElementByName_Details("website","https://www.dddd.com"); //Website

        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']")).click(); //Button
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']"); //Button

        LongWait();
        CheckNextUrl("");
    }

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =5, enabled = false)
    public static void Lokkho_Uddessho() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("");

        Menu_ShomitiCreate();

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click(); //Button

        LongWait();
        CheckNextUrl("");
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", priority =6, enabled = false)
    public static void Sodossho_Nibondhon() throws InterruptedException, IOException {
        LongWait();
        CheckCurrentUrl("");

        Menu_ShomitiCreate();

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        SmallWait();
        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr")).size();
        System.out.println(tr);

        if(tr < 6){
            driver.findElement(By.xpath("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']")).click();
        }

        SmallWait();
        //SelectRadioboxByName("NidOrBrn","1"); //Nid
        SelectBy_Name_Radiobox("NidOrBrn","2"); //BirthRegNo

        SmallWait();
        //FindElementByName("nid","4655155904"); //NID
        FindElementByName_Details("brn","19994578963254782"); //BirthRegNo

        SmallWait();
        FindElementByXpath_Details("//*[@type='tel']","03011999"); //DOB

        SmallWait();
        FindElementByName_Details("memberName","Asad Haq"); //MemberName

        SmallWait();
        FindElementByName_Details("memberNameBangla","আসাদ হক"); //MemberNameBangla

        SmallWait();
        FindElementByName_Details("fatherName","Mr. Abc"); //Father Name

        SmallWait();
        FindElementByName_Details("motherName","Mrs. Abc"); //Mother Name

        SmallWait();
        FindElementByName_Details("mobileNo","01968956730"); //Mobile

        SmallWait();
        SelectBy_Name_Radiobox("radioValue","1"); //Gender

        SmallWait();
        FindElementByName_Details("email","asad@erainfotechbd.com");

        SmallWait();
        SelectBy_Name_VisibleText("educationLevelId","স্নাতকোত্তর"); //Education Level //এইচ.এস.সি, স্নাতক, স্নাতকোত্তর, পঞ্চম শ্রেণী, অষ্টম শ্রেণী

        SmallWait();
        SelectBy_Name_VisibleText("jobType","শিক্ষক"); //Job Type //সরকারি কর্মচারী, কৃষক, বেসরকারী কর্মচারী, গৃহিনী, স্বনির্ভর, অন্যান্য

        SmallWait();
        SelectBy_Name_VisibleText("maritalStatusId","- বিবাহিত -"); //Marital Status //- অবিবাহিত -, - বিধবা -, - তালাকপ্রাপ্ত -
        FindElementByName_Details("spouseName","Mrs.Xyz"); //Only for Married

        SmallWait();
        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Present Address

        SmallWait();
        SelectBy_Name_VisibleText("upazila","দাকোপ"); //Sub District

        SmallWait();
        SelectBy_Name_VisibleText("uniThanaPawNameBangla","দাকোপ"); //Union

        SmallWait();
        FindElementByName_Details("villageArea","বাড়ি নং-৩২, রাস্তা-০৯");

        SmallWait();
        //UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe"); //Sodossho_Picture
        UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\sodossho.exe");

        SmallWait();
        //UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe"); //Sign_Picture
        UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\sign.exe");

        SmallWait();
        //UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
        UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\prottoyon.exe");

        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='আগের পাতায়']")).click(); //Button_আগের পাতায়
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='বন্ধ করুন']")).click(); //Button_বন্ধ করুন
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click(); //Button_সংরক্ষন করুন

        LongWait();
        CheckNextUrl("");
    }

    @Test(description = "This is for shomiti create(committee bebosthapona) scenario", priority =7, enabled = false)
    public static void Committee_Bebosthapona() throws InterruptedException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/designation");

        SmallWait();
        SelectBy_Name_VisibleText("organizerp","সাইফুর রহমান"); //Shongothok

        SmallWait();
        SelectBy_Name_VisibleText("communicationP","হামিদ সায়মন"); //Jogajoger Bekti

        SmallWait();
        SelectBy_Name_VisibleText("signingp","আসাদ হক"); //Kndriyo Shomir Pokkhe Sakkhorito Bekti

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u' and @name='']","৬ জন"); //Member //৯ জন //১২ জন
        //================================================================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[1]","সাইফুর রহমান");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[1]","সভাপতি");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[2]","হামিদ সায়মন");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[2]","সহ-সভাপতি");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[3]","আসাদ হক");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[3]","সাধারন সম্পাদক");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[4]","  আব্দুল কালাম");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[4]","যুগ্ম সম্পাদক");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[5]","হাসনাত আলী");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[5]","প্রচার সম্পাদক");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[6]","আবির হাসান");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[6]","কোষাধ্যক্ষ");
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
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-expenditure");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[1]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[1]","5000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[1]","4000"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[2]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[2]","2000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[2]","1500"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[3]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[3]","3000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[3]","2000"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[4]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[4]","10000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[4]","5000"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[5]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[5]","7000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[5]","6000"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[6]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[6]","4000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[6]","5000"); //loanOutstanding

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
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/budget");

        SmallWait();
        SelectBy_Name_VisibleText("budgetYear","2021-2022");

        //-------------- বর্তমান বছরের সমিতির বাজেট --------------// (Be Careful with Xpath, when increase or decrease)
        //---------------------- আয় ----------------------//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[1]","বাড়ি ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[1]","1500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[2]","বিক্রয়");
        FindElementByXpath_Details("(.//*[@name='amount'])[2]","2500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[3]","চেক বই বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[3]","3500");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[1]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[4]","ফর্ম বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[4]","4500");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[1]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[5]","ভর্তি ফি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[5]","5500");

        //---------------------- ব্যয় ----------------------//

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[6]","বিদ্যুৎ খরচ");
        FindElementByXpath_Details("(.//*[@name='amount'])[6]","500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[7]","ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[7]","1000");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[8]","পেনশন ও গ্র্যাচুইটি");
        FindElementByXpath_Details("(.//*[@name='amount'])[8]","400");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[2]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[9]","সম্মানি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[9]","900");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[2]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[10]","বিনোদন খরচ ");
        FindElementByXpath_Details("(.//*[@name='amount'])[10]","200");

        //-------------- পরবর্তী বছরের সমিতির বাজেট --------------//
        //---------------------- আয় ----------------------//
        LongWait();
        SelectBy_Name_VisibleText("budgetFYear","2022-2023");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[11]","সবজি বিক্রয়  ");
        FindElementByXpath_Details("(.//*[@name='amount'])[11]","2500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[12]","স্থায়ী সম্পত্তি বিক্রয় হতে লাভ ");
        FindElementByXpath_Details("(.//*[@name='amount'])[12]","4500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[13]","চেক বই বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[13]","1000");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[3]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[14]","ফর্ম বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[14]","3500");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[3]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[15]","বিবিধ আয়");
        FindElementByXpath_Details("(.//*[@name='amount'])[15]","5500");

        //---------------------- ব্যয় ----------------------//

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[16]","কাঁচামাল ক্রয়");
        FindElementByXpath_Details("(.//*[@name='amount'])[16]","3500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[17]","ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[17]","5500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[18]","কৃষি খরচ ");
        FindElementByXpath_Details("(.//*[@name='amount'])[18]","2500");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[4]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[19]","প্রভিডেন্ট ফান্ড/ ভবিষ্যৎ তহবিল ");
        FindElementByXpath_Details("(.//*[@name='amount'])[19]","2500");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[4]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[20]","বাড়ি ভাড়া ভাতা");
        FindElementByXpath_Details("(.//*[@name='amount'])[20]","1500");

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
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/income-expense");

        //---------------------- আয় ----------------------// (Be Careful with Xpath, when increase or decrease)
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[1]","বাড়ি ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[1]","1500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[2]","ভর্তি ফি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[2]","5500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[3]","সুদ- সঞ্চয়ী হিসাব");
        FindElementByXpath_Details("(.//*[@name='amount'])[3]","3500");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[1]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[4]","ফর্ম বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[4]","4500");

        //---------------------- ব্যয় ----------------------//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[5]","বিদ্যুৎ খরচ");
        FindElementByXpath_Details("(.//*[@name='amount'])[5]","500");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[6]","ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[6]","1000");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[7]","পেনশন ও গ্র্যাচুইটি");
        FindElementByXpath_Details("(.//*[@name='amount'])[7]","400");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[2]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[8]","সম্মানি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[8]","900");

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/required-doc");
    }

    @Test(description = "This is for shomiti create(kagoj potradi) scenario", priority =11, enabled = false)
    public static void Kagoj_Potradi() throws InterruptedException, IOException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/required-doc");

        SmallWait();
        SelectBy_Name_VisibleText("documentTypeId","চালান কপি");

        SmallWait();
        FindElementByName_Details("docReferenceNo","0012456");

        SmallWait();
        WebElement date = driver.findElement(By.xpath("(.//*[@type='tel'])[1]"));
        date.clear();
        date.sendKeys("01082020");

        SmallWait();
        WebElement expiredate = driver.findElement(By.xpath("(.//*[@type='tel'])[2]"));
        expiredate.clear();
        expiredate.sendKeys("01082024");

        SmallWait();
        //UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");
        UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\chalan.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/samity-reg-report");
    }

    @Test(description = "This is for shomiti create(churanto data somuho) scenario", priority =12, enabled = false)
    public static void Churanto_Data_Somuho() throws InterruptedException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/samity-reg-report");

        SmallWait();
        FindElementByName_Details("invoiceNo","0012456");

        SmallWait();
        WebElement date = driver.findElement(By.xpath("(.//*[@type='tel'])[1]"));
        date.clear();
        date.sendKeys("01082020");

        SmallWait();
        SelectBy_Name_Radiobox("viaDocuments","p"); //Upojela Office(u), Email(e)

        SmallWait();
        SelectBy_Name_Checkbox("declaration");

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='চূড়ান্ত জমা']")).click();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/reports/basic-report/document-download");
    }

    @AfterClass
    @Test(enabled = false)
    public static void Close(){
        FirefoxQuit();
    }
}
