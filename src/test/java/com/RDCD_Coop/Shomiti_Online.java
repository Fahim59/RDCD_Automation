package com.RDCD_Coop;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

@Listeners(Screenshot.class)
public class Shomiti_Online extends BaseClass {
    public static String ShomitiName = "Shomobay Shomiti";
    public static String AuthorizePerson = "Mustafizur Rahman";

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        //OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
        OpenWebsite("http://dashboard.rdcd.orangebd.com/admin/login");
    }

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true)
    public static void Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/login");
        //CheckCurrentUrl("http://dashboard.rdcd.orangebd.com/admin/login");

        Admin_Login();
        //SSO_Admin_Login();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        //CheckNextUrl("http://dashboard.rdcd.orangebd.com/admin/my-applications");
    }

    @Test(description = "SSO", priority = 2, enabled = false)
    public static void SSO() throws InterruptedException {
        FindElementByXpath_Click("//a[contains(@href,'http://dashboard.rdcd.orangebd.com/admin/users')]");

        LongWait();
        CheckCurrentUrl("http://dashboard.rdcd.orangebd.com/admin/users");

        SmallWait();
        WebElement uco = driver.findElement(By.xpath("//a[@href='http://dashboard.rdcd.orangebd.com/admin/users/67/edit']"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click();", uco);

        LongWait();
        CheckCurrentUrl("http://dashboard.rdcd.orangebd.com/admin/users/67/edit");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-action='button#confirm'])[1]");

        SmallWait();
        FindElementByXpath_Click("(.//*[@class='btn btn-default'])[3]");

        LargeWait();LargeWait();
        CheckCurrentUrl("http://dashboard.rdcd.orangebd.com/admin/admin-dashboard");

        SmallWait();
        FindElementByXpath_Click("//a[contains(@href,'http://dashboard.rdcd.orangebd.com/redirectTo/6')]");

        //SmallWait();
        //CheckCurrentUrl("http://10.11.200.30:5001/dashboard");
    }

    @Test(description = "This is for shomiti online scenario", priority = 2, enabled = false)
    public static void Shomiti_Online() throws InterruptedException, IOException {
        Menu_ShomitiOnline();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/manual-samity");

        SelectBy_Name_Radiobox("samityLevel","P");

        SmallWait();
        FindElementByName_Details("samityName", ShomitiName);

        SmallWait();
        FindElementByName_Details("samityCode", "5566");

        SmallWait();
        FindElementByXpath_Details("(.//*[@type='tel'])[1]","10082022");

        SmallWait();
        FindElementByXpath_Details("(.//*[@type='tel'])[2]","03082022");

        SmallWait();
        SelectBy_Name_VisibleText("samityTypeId","মৎস্যজীবি বা মৎস্যচাষী সমবায় সমিতি");

        SmallWait();
        SelectBy_Name_VisibleText("enterprisingOrg","সমবায় অধিদপ্তর");

        SmallWait();
        SelectBy_Name_VisibleText("projectId","নৃ-তাত্ত্বিক");

        SmallWait();
        SelectBy_Name_Radiobox("samityEffectiveness","E");

        SmallWait();
        SelectBy_Name_VisibleText("uniThanaPawIdType","দাকোপ ");

        SmallWait();
        FindElementByName_Details("villageArea","বাড়ি নং-৩২, রাস্তা-০৯");

        Scroll_Down();

        SmallWait();
        SelectBy_Name_VisibleText("memberAreaType","ইউনিয়ন/পৌরসভা/থানা");

        SmallWait();
        SelectBy_Name_VisibleText("samityUniThanaPawIdType","দাকোপ ");

        SmallWait();
        FindElementByName_Details("detailsAddress","বাড়ি নং-৩২, রাস্তা-০৯");

        SmallWait();
        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']");

        Scroll_Down();

        SmallWait();
        FindElementByName_Details("authorizedPersonName","Mustafizur Rahman");

        SmallWait();
        FindElementByName_Details("authorizedPersonNid","4655155903");

        SmallWait();
        FindElementByName_Details("authorizedPersonMobileNo","01686026037");

        Scroll_Down();

        SmallWait();
        SelectBy_Name_VisibleText("documentType","চালান কপি");

        Scroll_Down();

        SmallWait();
        FindElementByName_Details("documentNumber","5656");

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");
        //UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\chalan.exe");

        Scroll_Down();

        SmallWait();
        SelectBy_Name_Radiobox("ownOrOthers","own");

        SmallWait();
        SelectBy_Name_VisibleText("officerId","উপজেলা সমবায় অফিসার - প্রনয় রঞ্জন মন্ডল");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        driver.findElement(By.xpath("//span[text()='অনুমোদন']")).click();

        SmallWait();
        SelectBy_Name_VisibleText("serviceId","ম্যানুয়াল সমিতি মাইগ্রেশন ");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();

            if(shomitiName.equalsIgnoreCase(ShomitiName)){
                SmallWait();
                FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[4]/button");
            }
            else{
                System.out.println("Shomiti does not exist");
            }
        }

        Scroll_Down_FindElement("serviceActionId");

        LongWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        LongWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for selecting authorize person scenario", priority = 3, enabled = false)
    public static void Shomiti_AuthorizePersonSelect() throws InterruptedException {
        Menu_ShomitiAuthorizePersonSelect();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/authorized-person");

        SmallWait();
        SelectBy_Name_VisibleText("samityName", ShomitiName);

        SmallWait();
        SelectBy_Name_VisibleText("newAuth", AuthorizePerson);

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for staff management(podobi boraddhokoron) scenario", priority = 4, enabled = false)
    public static void Staff_Management_AssignTitle() throws InterruptedException {
        Menu_AssignTitle();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/assignment");

        SmallWait();
        SelectBy_Name_VisibleText("samityId", ShomitiName);

        SmallWait();
        FindElementByName_Details("designationName","");

        SmallWait();
        FindElementByName_Details("rank","");

        SmallWait();
        SelectBy_Name_Radiobox("status","true");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for staff management(staff info) scenario", priority = 5, enabled = false)
    public static void Staff_Management_StaffInfo() throws InterruptedException, IOException {
        Menu_SaffInfo();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/information");
        //CheckCurrentUrl("http://10.11.200.30:5001/employee-management/information");

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[1]", ShomitiName);

        SmallWait();
        FindElementByName_Details("employeeId","");

        SmallWait();
        FindElementByName_Details("nid","");

        SmallWait();
        FindElementByName_Details("brn","");

        SmallWait();
        FindElementByXpath_Details("//*[@type='tel']", "03011999");

        SmallWait();
        FindElementByName_Details("name","");

        SmallWait();
        FindElementByName_Details("fatherName","");

        SmallWait();
        FindElementByName_Details("motherName","");

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[2]","অবিবাহিত"); //বিবাহিত, ডিভোর্সি, বিপত্নীক

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[3]","স্নাতক"); //নবম শ্রেণী, সপ্তম শ্রেণী

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[4]","");

        SmallWait();

        SmallWait();
        FindElementByName_Details("basic_salary","10000");

        SmallWait();
        FindElementByName_Details("gross_salary","25000");

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[5]","ইসলাম");

        SmallWait();
        SelectBy_Name_Radiobox("gender","1");

        SmallWait();
        SelectBy_Name_Radiobox("gender","A");

        SmallWait();
        FindElementByName_Details("experience","Experienced");

        SmallWait();
        FindElementByName_Details("presentAddress","বাড়ি নং-৩২, রাস্তা-০৯");

        SmallWait();
        FindElementByName_Details("permanentAddress","বাড়ি নং-৩২, রাস্তা-০৯");

        Scroll_Down();

        SmallWait();
        UploadPicture("div.MuiGrid-root:nth-child(21) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe"); //Sodossho_Picture
        //UploadPicture("div.MuiGrid-root:nth-child(21) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","F:\\RDCD_Automation\\Picture\\sodossho.exe");

        SmallWait();
        UploadPicture("div.MuiGrid-root:nth-child(22) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe"); //Sign_Picture
        //UploadPicture("div.MuiGrid-root:nth-child(22) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","F:\\RDCD_Automation\\Picture\\sign.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for staff management(giving salary) scenario", priority = 6, enabled = false)
    public static void Staff_Management_GivingSalary() throws InterruptedException, IOException {
        Menu_Salary();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/salary-payment");

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[1]", ShomitiName);

        SmallWait();
        FindElementByName_Details("salaryMonthYear","");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
