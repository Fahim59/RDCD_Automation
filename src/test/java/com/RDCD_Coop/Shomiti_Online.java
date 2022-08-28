package com.RDCD_Coop;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

@Listeners(Screenshot.class)
public class Shomiti_Online extends BaseClass {
    public static String ShomitiName = "Shomobay Shomiti";

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
        //OpenWebsite("http://10.11.200.30:5001/login");
    }

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true)
    public static void Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/login");
        //CheckCurrentUrl("http://10.11.200.30:5001/login");

        Admin_Login();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        //CheckNextUrl("http://10.11.200.30:5001/dashboard");
    }

    @Test(description = "This is for shomiti online scenario", priority = 2, enabled = false)
    public static void Shomiti_Online() throws InterruptedException, IOException {
        Menu_ShomitiOnline();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/manual-samity");
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/manual-samity");

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
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/authorized-person");

        SmallWait();
        SelectBy_Name_VisibleText("samityName","");

        SmallWait();
        SelectBy_Name_VisibleText("newAuth","");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for staff management(podobi boraddhokoron) scenario", priority = 4, enabled = false)
    public static void Staff_Management_AssignTitle() throws InterruptedException {
        Menu_AssignTitle();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/assignment");
        //CheckCurrentUrl("http://10.11.200.30:5001/employee-management/assignment");

        SmallWait();
        SelectBy_Name_VisibleText("samityId","");

        SmallWait();
        SelectBy_Name_VisibleText("newAuth","");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

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
    public static void Staff_Management_StaffInfo() throws InterruptedException {
        Menu_SaffInfo();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/information");
        //CheckCurrentUrl("http://10.11.200.30:5001/employee-management/information");

        SmallWait();
        SelectBy_Name_VisibleText("samityId","");

        SmallWait();
        SelectBy_Name_VisibleText("newAuth","");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        FindElementByName_Details("designationName","");

        SmallWait();
        FindElementByName_Details("rank","");

        SmallWait();
        SelectBy_Name_Radiobox("status","true");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
