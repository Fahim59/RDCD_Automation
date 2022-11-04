package com.RDCD_Coop.Central_Samity;

import com.BaseClass.BaseClass;
import com.RDCD_Coop.DataProvider.DataProviderClass;
import com.RetryScenario.Screenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Listeners(Screenshot.class)
public class Create_Central_Shomiti extends BaseClass {
    public static String aShomitiName = "Khulna Bar Association"; //First name
    public static String dShomitiName = "Khulna Bar Association 1"; //If same name available
    public static String sname;

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
    }

    @Test(description = "This is for SSO login scenario", priority = 1, enabled = false)
    public static void SSO_Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://dashboard.rdcd.orangebd.com/admin/login");

        SSO_Admin_Login();

        LongWait();
        CheckNextUrl("http://dashboard.rdcd.orangebd.com/admin/my-applications");

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

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true)
    public static void Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/login");

        Organizer_Login();
        //Admin_Login();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = true)
    public static void NameClearance() throws InterruptedException {
        Menu_AssociationManagement("//span[text()='নেম ক্লিয়ারেন্স']");

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

            if(option.getAttribute("value").equalsIgnoreCase("C")){
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

        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");
    }

    @Test(description = "This is shomiti name clearance approve scenario", priority =3, enabled = true)
    public static void NameClearance_Approve() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://rdcd.erainfotechbd.com:5005/login")){
            Admin_Login();

            LongWait();
            CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        }

        SmallWait();
        Menu_Approve("//span[text()='অনুমোদন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/approval");

        SmallWait();
        SelectBy_Name_VisibleText("serviceId","নেম ক্লিয়ারেন্স / নামের ছাড়পত্র");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String service = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[1]")).getText();
            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();

            if(service.equalsIgnoreCase("নেম ক্লিয়ারেন্স / নামের ছাড়পত্র")){
                if(shomitiName.equalsIgnoreCase(sname)){
                    SmallWait();
                    FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[4]/button");
                }
                else{
                    System.out.println("Shomiti does not exist");
                }
            }
            else{
                System.out.println("Service not available");
            }
        }

        Scroll_Down();

        LongWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        LongWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");
    }

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario", priority =4, enabled = true)
    public static void PrathomikTottho() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://rdcd.erainfotechbd.com:5005/login")){
            Organizer_Login();

            LongWait();
            CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        }

        Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/registration");

        int size = driver.findElements(By.xpath("//*[@name='samityLevel' and @value='1']")).size();

        if(size > 0){
            SelectBy_Name_Radiobox("samityLevel","1");

            SmallWait();
            SelectBy_Name_Radiobox("samityLevel","C"); //Shomiti Level

            SmallWait();
            WebElement dropdown = driver.findElement(By.name("samityName"));
            Select target = new  Select(dropdown);

            List<WebElement> elements = target.getOptions();

            List<String> list = new ArrayList<String>();
            for (WebElement webElement : elements) {
                list.add(webElement.getText());
            }

            if(list.contains(sname)){
                SelectBy_Name_VisibleText("samityName", sname);
            }
            else{
                System.out.println("Shomiti is not available");

                SmallWait();
                SelectBy_Name_Radiobox("samityLevel","2");

                SmallWait();
                SelectBy_Xpath_VisibleText("(.//*[@name='projectId'])[1]",sname);
            }
        }
        else{
            System.out.println("No Incomplete Shomiti is available");

            SmallWait();
            SelectBy_Name_Radiobox("samityLevel","C");

            SmallWait();
            SelectBy_Name_VisibleText("samityName", sname);
        }

        SmallWait();
        SelectBy_Name_VisibleText("samityUniThanaPawIdType","দাকোপ"); //Union

        SmallWait();
        FindElementByName_Details("samityDetailsAddress","বাড়ি নং-৩২, রাস্তা-০৯"); //Address

        SmallWait();
        SelectBy_Name_VisibleText("memberAreaType","ইউনিয়ন/পৌরসভা/থানা");

        Scroll_Down();

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[2]","দাকোপ ");

        SmallWait();
        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Work Place

        SmallWait();
        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']");

        SmallWait();
        FindElementByXpath_Date("//*[@type='tel']","03012020");//Create date

        SmallWait();
        FindElementByName_Details("memberAdmissionFee", "100"); //Admission fee

        SmallWait();
        FindElementByName_Details("noOfShare","100");//No of Share

        SmallWait();
        FindElementByName_Details("sharePrice", "500"); //Share Price

        SmallWait();
        FindElementByName_Details("soldShare","30");

        SmallWait();
        FindElementByName_Details("phoneNo","0273835618"); //Phone

        SmallWait();
        FindElementByName_Details("mobileNo","01738356180"); //Mobile

        SmallWait();
        FindElementByName_Details("emailId","shomiti@gmail.com"); //Email

        SmallWait();
        SelectBy_Name_VisibleText("enterprisingId","সমবায় অধিদপ্তর"); //Enterpriceid

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@name='projectId']","পরিবার কল্যাণ প্রকল্প"); //Projecteid

        SmallWait();
        FindElementByName_Details("website","https://www.samity.com"); //Website

        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']"); //Button

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/add-by-laws");
    }

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =5, enabled = true)
    public static void Lokkho_Uddessho() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/add-by-laws");

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-registration");
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", dataProvider = "Sodossho_Nibondhon_Central", dataProviderClass = DataProviderClass.class, priority =6, enabled = false)
    public static void Sodossho_Nibondhon(String samity, String date) throws InterruptedException, IOException {

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-registration");

        SmallWait();
        int size = driver.findElements(By.xpath("(.//*[@aria-label='a dense table'])")).size();

        if(size > 0){
            int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div[2]/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
            System.out.println(tr);

            if(tr < 6){
                //SmallWait();
                SelectBy_Name_VisibleText("samityName", samity);

                //SmallWait();
                FindElementByXpath_Details("//*[@type='tel']", date);

                Scroll_Down();

                SmallWait();
                UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe"); //Sodossho_Picture
                //UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\sodossho.exe");

                SmallWait();
                UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe"); //Sign_Picture
                //UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\sign.exe");

                /*SmallWait();
                UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
                //UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\prottoyon.exe");

                SmallWait();
                UploadPicture("div.MuiGrid-grid-xs-12:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
                //UploadPicture("div.MuiGrid-grid-xs-12:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","F:\\RDCD_Automation\\Picture\\prottoyon.exe");

                SmallWait();
                UploadPicture("div.MuiGrid-grid-xs-12:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
                //UploadPicture("div.MuiGrid-grid-xs-12:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","F:\\RDCD_Automation\\Picture\\prottoyon.exe");*/
            }
            else{
                FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

                LongWait();
                CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/designation");
            }
        }
        else{
            //SmallWait();
            SelectBy_Name_VisibleText("samityName", samity);

            //SmallWait();
            FindElementByXpath_Details("//*[@type='tel']", date);

            Scroll_Down();

            SmallWait();
            UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe"); //Sodossho_Picture
            //UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\sodossho.exe");

            SmallWait();
            UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe"); //Sign_Picture
            //UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\sign.exe");

            /*SmallWait();
            UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
            //UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\prottoyon.exe");

            SmallWait();
            UploadPicture("div.MuiGrid-grid-xs-12:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
            //UploadPicture("div.MuiGrid-grid-xs-12:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","F:\\RDCD_Automation\\Picture\\prottoyon.exe");

            SmallWait();
            UploadPicture("div.MuiGrid-grid-xs-12:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
            //UploadPicture("div.MuiGrid-grid-xs-12:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > span","F:\\RDCD_Automation\\Picture\\prottoyon.exe");*/
        }

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Scroll_Up();
    }

    @Test(description = "This is for shomiti create(committee bebosthapona) scenario", dataProvider = "Committee_Bebosthapona_Central", dataProviderClass = DataProviderClass.class, priority =7, enabled = true)
    public static void Committee_Bebosthapona(String name1, String name2, String name3, String name4, String name5, String name6) throws InterruptedException {

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/designation");

        SmallWait();
        SelectBy_Name_VisibleText("organizerp", name1); //Shongothok

        SmallWait();
        SelectBy_Name_VisibleText("communicationP", name2); //Jogajoger Bekti

        SmallWait();
        SelectBy_Name_VisibleText("signingp", name3); //Kndriyo Shomir Pokkhe Sakkhorito Bekti

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u' and @name='']","৬ জন"); //Member //৯ জন //১২ জন
        //================================================================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[1]", name1);

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[1]","সভাপতি");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[2]", name2);

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[2]","সহ-সভাপতি");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[3]", name3);

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[3]","সাধারন সম্পাদক");
        //===================================================//
        Scroll_Down();

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[4]", name4);

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[4]","যুগ্ম সম্পাদক");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[5]", name5);

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[5]","প্রচার সম্পাদক");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[6]", name6);

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[6]","কোষাধ্যক্ষ");
        //===================================================//

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-expenditure");
    }

    @Test(description = "This is for shomiti create(arthik totthadi) scenario", dataProvider = "Arthik_Totthadi_Central", dataProviderClass = DataProviderClass.class, priority =8, enabled = true)
    public static void Arthik_Totthadi(String a1, String a2, String b1, String b2, String c1, String c2, String d1, String d2, String e1, String e2, String f1, String f2) throws InterruptedException {

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-expenditure");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[1]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[1]", a1); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[1]", a2); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[2]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[2]", b1); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[2]", b2); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[3]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[3]", c1); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[3]", c2); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[4]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[4]", d1); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[4]", d2); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[5]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[5]", e1); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[5]", e2); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[6]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[6]", f1); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[6]", f2); //loanOutstanding

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='হালনাগাদ করুন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/budget");
    }

    @Test(description = "This is for shomiti create(shomitir budget) scenario", priority =9, enabled = false)
    public static void Shomiti_Budget() throws InterruptedException {

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

        Long_Scroll_Down();

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

        Long_Scroll_Down();

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

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        /*SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='হালনাগাদ করুন']")).click();

        SmallWait();
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='পরবর্তী পাতা']")).click();*/

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/income-expense");
    }

    @Test(description = "This is for shomiti create(shomitir aay_beey) scenario", priority =10, enabled = false)
    public static void Shomitir_Aay_Beey() throws InterruptedException {

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

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/required-doc");
    }

    @Test(description = "This is for shomiti create(kagoj potradi) scenario", priority =11, enabled = false)
    public static void Kagoj_Potradi() throws InterruptedException, IOException {

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
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");
        //UploadPicture("label.MuiButton-root","F:\\RDCD_Automation\\Picture\\chalan.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/samity-reg-report");
    }

    @Test(description = "This is for shomiti create(churanto data somuho) scenario", priority =12, enabled = false)
    public static void Churanto_Data_Somuho() throws InterruptedException {

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/samity-reg-report");

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='চূড়ান্ত জমা']");

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
        FindElementByXpath_Click("//*[@type='button' and @aria-label='চূড়ান্ত জমা']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/reports/basic-report/document-download");

        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");
    }

    @Test(description = "This is for shomiti approval scenario", priority =13, enabled = false)
    public static void Shomiti_Approval() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://rdcd.erainfotechbd.com:5005/login")){
            Admin_Login();

            LongWait();
            CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        }

        Menu_Approve("//span[text()='অনুমোদন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/approval");

        SmallWait();
        SelectBy_Name_VisibleText("serviceId","সমিতি নিবন্ধন");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String service = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[1]")).getText();
            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();

            if(service.equalsIgnoreCase("সমিতি নিবন্ধন")){
                if(shomitiName.equalsIgnoreCase(sname)){
                    SmallWait();
                    FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[4]/button");
                }
                else{
                    System.out.println("Shomiti does not exist");
                }
            }
            else{
                System.out.println("Service not available");
            }
        }

        Long_Scroll_Down();
        Scroll_Down_FindElement("serviceActionId");

        LongWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        LongWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");

        LongWait();
        Organizer_Login();

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি");

        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"Shomiti Successfully Created");

        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");
    }

    @AfterSuite
    public static void Close(){
        FirefoxQuit();
        //SendEmail();
    }
}
