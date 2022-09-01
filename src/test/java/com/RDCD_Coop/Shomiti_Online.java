package com.RDCD_Coop;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

@Listeners(Screenshot.class)
public class Shomiti_Online extends BaseClass {
    public static String ShomitiName = "Shomobay Shomiti";
    public static String AuthorizePerson = "Mustafizur Rahman";

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        //OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
        OpenWebsite("http://10.11.200.30:5001/login");
        //OpenWebsite("http://dashboard.rdcd.orangebd.com/admin/login");
    }

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true)
    public static void Login() throws InterruptedException {
        //LongWait();
        //CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/login");
        //CheckCurrentUrl("http://dashboard.rdcd.orangebd.com/admin/login");

        //Admin_Login();
        Authorized_Login();
        //SSO_Admin_Login();

        //LongWait();
        //CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
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
        Menu_AssociationManagement("//span[text()='সমিতি অনলাইনকরন']");

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
        FindElementByXpath_Click("//span[text()='অনুমোদন']");

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
        Menu_AssociationManagement("//span[text()='সমিতি অনুমোদিত ব্যক্তি']");

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
        Menu_StaffManagement("//span[text()='পদবী বরাদ্দকরন']");

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
        Menu_StaffManagement("//span[text()='কর্মকর্তা/কর্মচারী তথ্য']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/information");

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
    public static void Staff_Management_GivingSalary() throws InterruptedException {
        Menu_StaffManagement("//span[text()='বেতন প্রদান']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/salary-payment");

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[1]", ShomitiName);

        SmallWait();
        FindElementByName_Details("salaryMonthYear","");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for report management(basic report, document) scenario", priority = 7, enabled = false)
    public static void Report_Management_Document() throws InterruptedException {
        Menu_Report_Basic("//span[text()='ডকুমেন্টস']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/reports/basic-report/document-download");
    }

    @Test(description = "This is for report management(basic report, details member) scenario", priority = 8, enabled = false)
    public static void Report_Management_ShomitiDetails() throws InterruptedException {
        Menu_Report_Basic("//span[text()='সমিতির বিস্তারিত']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/reports/generated-report/samity-report");
    }

    @Test(description = "This is for report management(basic report, details shomiti) scenario", priority = 9, enabled = false)
    public static void Report_Management_MemberDetails() throws InterruptedException {
        Menu_Report_Basic("//span[text()='সদস্যের বিস্তারিত']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/reports/member-brife-info");
    }

    @Test(description = "This is for report management(basic report, chart of account) scenario", priority = 10, enabled = false)
    public static void Report_Management_ChartOfAccount() throws InterruptedException {
        Menu_Report_Basic("//span[text()='চার্ট অফ একাউন্ট']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/reports/chart-accounts");
    }

    @Test(description = "This is for report management(committee report, committee details) scenario", priority = 11, enabled = false)
    public static void Report_Management_CommitteeDetails() throws InterruptedException {
        Menu_Report_Committee("//span[text()='কমিটির বিস্তারিত']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/reports/committee-brife");
    }

    @Test(description = "This is for report management(committee report, committee order) scenario", priority = 12, enabled = false)
    public static void Report_Management_CommitteeOrder() throws InterruptedException {
        Menu_Report_Committee("//span[text()='কমিটির আদেশ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/reports/committee-adesh");
    }

    //---------------------------------------------------------------------------------------------------------------//

    @Test(description = "This is for shomiti management(update member info) scenario", priority = 13, enabled = false)
    public static void Update_Member_Info() throws InterruptedException {

        Menu_AssociationManagement("//span[text()='সদস্যের তথ্য সংশোধন']");

        //LongWait();
        //CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/member-details-correction");

        SmallWait();
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int num = Integer.parseInt(JOptionPane.showInputDialog(frame,"Choose Any Operation- " + "\n" + "1. Add New Member" + "\n" + "2. Update an Existing Member"+ "\n" + "3. Delete an Existing Member" + "\n\n" + "Enter Your Choice: (1/2/3)"));

        switch (num) {
            case 1:
                SmallWait();
                FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");

                //Member Add
                break;

            case 2:
                SmallWait();
                int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr")).size();
                for(int l = 1; l<= tr; l++){

                    String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();
                    System.out.println(memberName);
                    String mobile = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[9]")).getText();
                    System.out.println(mobile);

                    if(memberName.equalsIgnoreCase("a")){             //---------------------//
                        if(mobile.equalsIgnoreCase("০১৭৩৮৩৫৬১৮০")){  //---------------------//
                            SmallWait();
                            FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[11]/button[1]");
                        }
                        else{
                            System.out.println("Mobile number does not match");
                        }
                    }
                    else{
                        System.out.println("Member does not exist");
                    }
                }

                //LongWait();
                //CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/member-details-correction");

                SmallWait();
                FindElementByName_Details("motherName","Halima"); //---------------------//

                Long_Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//*[@type='button' and @aria-label='হালনাগাদ করুন']");

                SmallWait();
                int tr1 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr")).size();
                for(int l = 1; l<= tr1; l++){

                    String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[5]")).getText();
                    String mobile = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[10]")).getText();
                    String motherName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[7]")).getText();

                    if(memberName.equalsIgnoreCase("a")){
                        if(mobile.equalsIgnoreCase("০১৭৩৮৩৫৬১৮০")){
                            if(motherName.equalsIgnoreCase("Halima")){
                                System.out.println("Member Updated Successfully");
                            }
                            else{
                                System.out.println("Member Update Failed");
                            }
                        }
                        else{
                            System.out.println("Mobile number does not match, Member Update Failed");
                        }
                    }
                    else{
                        System.out.println("Member does not exist, Member Update Failed");
                    }
                }

                FindElementByXpath_Click("//button[text()='আবেদনটি জমা দিন']");
                break;

            case 3:
                SmallWait();
                int tr2 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr")).size();
                for(int l = 1; l<= tr2; l++){

                    String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();
                    String mobile = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[9]")).getText();

                    if(memberName.equalsIgnoreCase("d")){
                        if(mobile.equalsIgnoreCase("০১৭৩৮৩৫৬১৮০")){
                            SmallWait();
                            FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[11]/button[2]");
                        }
                        else{
                            System.out.println("Mobile number does not match");
                        }
                    }
                    else{
                        System.out.println("Member does not exist");
                    }
                }

                SmallWait();
                int tr4 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr")).size();
                for(int l = 1; l<= tr4; l++){

                    String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[5]")).getText();
                    String mobile = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[10]")).getText();

                    if(memberName.equalsIgnoreCase("a")){
                        if(mobile.equalsIgnoreCase("০১৭৩৮৩৫৬১৮০")){
                            System.out.println("Member Updated Successfully");
                        }
                        else{
                            System.out.println("Mobile number does not match, Member Update Failed");
                        }
                    }
                    else{
                        System.out.println("Member does not exist, Member Update Failed");
                    }
                }

                FindElementByXpath_Click("//button[text()='আবেদনটি জমা দিন']");
                break;

            default:
                JOptionPane.showMessageDialog(frame,"You have entered wrong input");
        }
    }

    @Test(description = "This is for Committee Management(committee gothoner abedon) scenario", priority = 14, enabled = false)
    public static void Apply_for_the_formation_of_the_Committee() throws InterruptedException, IOException {
        SmallWait();
        FindElementByXpath_Click("//span[text()='কমিটি গঠনের আবেদন']");

        SmallWait();
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int num = Integer.parseInt(JOptionPane.showInputDialog(frame,"Choose Any Operation- " + "\n" + "1. Application for formation of election committee" + "\n" + "2. Application for addition of selected committee"+ "\n" + "3. Application for formation of Interim Committee" + "\n\n" + "Enter Your Choice: (1/2/3)"));

        switch (num) {
            case 1:
                SelectBy_Name_VisibleText("servicesName","নির্বাচন কমিটি গঠনের আবেদন");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[1]","31082022");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[2]","05092022");

                SmallWait();
                SelectBy_Name_VisibleText("documentType","সাংগঠনিক সভার রেজুলেশন"); //নির্বাচনী নোটিশ

                SmallWait();
                FindElementByName_Details("documentNumber","2635");

                Scroll_Down();

                SmallWait();
                UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");
                //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\chalan.exe");

                Scroll_Down();

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সভাপতি");

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সহ-সভাপতি");

                Scroll_Down();

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সাধারন সম্পাদক");

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সদস্য-ব্যবস্থাপনা কমিটি");

                Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//button[text()='আবেদন করুন']");

                break;

            case 2:
                SelectBy_Name_VisibleText("servicesName","নির্বাচিত কমিটি সংযোজনের আবেদন");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[1]","31082022");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[2]","05092022");

                SmallWait();
                SelectBy_Name_VisibleText("documentType","সাংগঠনিক সভার রেজুলেশন"); //নির্বাচনী নোটিশ

                SmallWait();
                FindElementByName_Details("documentNumber","2635");

                Scroll_Down();

                SmallWait();
                UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");
                //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\chalan.exe");

                Scroll_Down();

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সভাপতি");

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সহ-সভাপতি");

                Scroll_Down();

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সাধারন সম্পাদক");

                SmallWait();
                Application_for_formation_of_election_committee("HASIB","সদস্য-ব্যবস্থাপনা কমিটি");

                Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//button[text()='আবেদন করুন']");

                break;

            case 3:
                SelectBy_Name_VisibleText("servicesName","অন্তর্বর্তী কমিটি গঠনের আবেদন");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[1]","31082022");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[2]","05092022");

                SmallWait();
                SelectBy_Name_VisibleText("documentType","সাংগঠনিক সভার রেজুলেশন"); //নির্বাচনী নোটিশ

                SmallWait();
                FindElementByName_Details("documentNumber","2635");

                Scroll_Down();

                SmallWait();
                UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");
                //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\chalan.exe");

                Scroll_Down();

                SmallWait();
                Application_for_formation_of_election_committee("H","সভাপতি");

                SmallWait();
                Application_for_formation_of_election_committee("","সহ-সভাপতি");

                Scroll_Down();

                SmallWait();
                Application_for_formation_of_election_committee("","সাধারন সম্পাদক");

                SmallWait();
                Application_for_formation_of_election_committee("","সদস্য-ব্যবস্থাপনা কমিটি");

                Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//button[text()='আবেদন করুন']");

                break;

            default:
                JOptionPane.showMessageDialog(frame,"You have entered wrong input");
        }
    }

    @Test(description = "This is for approved shomiti's website creation scenario", priority = 15, enabled = true)
    public static void Website_Setup() throws InterruptedException, IOException {
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]","Shomobay Shomiti -অনুমোদিত সমিতি");

        SmallWait();
        FindElementByXpath_Click("//span[text()='ওয়েব সাইট সেটআপ']");
        //===================================================================//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");

        SmallWait();
        SelectBy_Name_VisibleText("contentId","লোগো");

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\logo.exe"); //Sign_Picture
        //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\logo.exe");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("লোগো");

        driver.navigate().refresh();
        //---------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");

        SmallWait();
        SelectBy_Name_VisibleText("contentId","ব্যানার");

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\cover.exe");
        //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\cover.exe");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("ব্যানার");
        //-------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");

        SmallWait();
        SelectBy_Name_VisibleText("contentId","নোটিশ");

        SmallWait();
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys(Keys.TAB,"Let's meet on Sunday");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("নোটিশ");
        //--------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");

        SmallWait();
        SelectBy_Name_VisibleText("contentId","সামাজিক লিংক");

        SmallWait();
        FindElementByName_Details("facebook","facebook.com");

        SmallWait();
        FindElementByName_Details("twitter","twitter.com");

        SmallWait();
        FindElementByName_Details("skype","skype.com");

        SmallWait();
        FindElementByName_Details("messanger","facebook.com");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        driver.navigate().refresh();
        //--------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");

        SmallWait();
        SelectBy_Name_VisibleText("contentId","ছবিসমূহ");

        SmallWait();
        FindElementByXpath_Click("(.//*[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeSmall MuiButton-containedSizeSmall MuiButtonBase-root Sanction_btnOne__Bx6G8 css-1lpukdo'])[1]");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-md-5:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\cover.exe");
        //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\cover.exe");

        SmallWait();
        UploadPicture("div.css-1r482s6:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\cover.exe");
        //UploadPicture("div.css-1r482s6:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","F:\\RDCD_Automation\\Picture\\cover.exe");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("ছবিসমূহ");
        //============================================================================================//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","আমাদের সম্পর্কে");

        SmallWait();
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys(Keys.TAB,"At Mahamaya Bandhan Samity, in every step of the way, we have found people who care and are willing to do their share to help a child and spread hope and cheer among children in need of critical medical assistance.");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //===========================================================================================//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","সেবাসমূহ");

        SmallWait();
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys(Keys.TAB,"1. Saving Lives" + "\n" + " 2. Poverty Alleviation"+ "\n" + " 3. Climate Adaptation"+ "\n" + " 4. Empowerment");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //==========================================================================================//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রকল্প / কর্মসূচী");

        SmallWait();
        SelectBy_Name_VisibleText("contentId","ছবি প্রথমে");

        SmallWait();
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys(Keys.TAB,"Front Photo");

        Scroll_Down();

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\cover.exe");
        //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\cover.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LargeWait();
        Website_Setup_Table("ছবি প্রথমে");

        driver.navigate().refresh();
        //-----------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রকল্প / কর্মসূচী");

        SmallWait();
        SelectBy_Name_VisibleText("contentId","ছবি শেষে");

        Scroll_Down();

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\cover.exe");
        //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\cover.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LargeWait();
        Website_Setup_Table("ছবি শেষে");
        //==========================================================================================//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","সচরাচর জিঞ্জাসা");

        SmallWait();
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys(Keys.TAB,"Friends Forever");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //==========================================================================================//
        SmallWait();
        FindElementByXpath_Click("//span[text()='ড্যাশবোর্ড']");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]","Shomobay Shomiti -অনুমোদিত সমিতি");

        FindElementByXpath_Click("//button[text()='ওয়েব সাইট']");

        String oldTab = driver.getWindowHandle();

        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

        SmallWait();
        driver.switchTo().window(oldTab);
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}