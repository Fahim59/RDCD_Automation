package com.RDCD_Coop;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Listeners(Screenshot.class)
public class Create_Shomiti extends BaseClass {
    public static String aShomitiName = "Friend's Shomobay Shomiti"; //First name
    public static String dShomitiName = "Friend's Shomobay Shomiti 1"; //If same name available
    public static String sname;

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
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

        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");
    }

    @Test(description = "This is shomiti name clearance approve scenario", priority =3, enabled = true)
    public static void NameClearance_Approve() throws InterruptedException {
        Admin_Login();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");

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
        Organizer_Login();

        Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/registration");

        int size = driver.findElements(By.xpath("//*[@name='samityLevel' and @value='1']")).size();

        if(size > 0){
            SelectBy_Name_Radiobox("samityLevel","1");

            SmallWait();
            SelectBy_Name_Radiobox("samityLevel","P"); //Shomiti Level

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
            SelectBy_Name_Radiobox("samityLevel","P");

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
        FindElementByName_Details("emailId","shomobay_shomiti@gmail.com"); //Email

        SmallWait();
        SelectBy_Name_VisibleText("enterprisingId","সমবায় অধিদপ্তর"); //Enterpriceid

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@name='projectId']","আদর্শ গ্রাম-২ প্রকল্প"); //Projecteid

        SmallWait();
        FindElementByName_Details("website","https://www.dddd.com"); //Website

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

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", dataProvider = "Sodossho_Nibondhon", dataProviderClass = DataProviderClass.class, priority =6, enabled = true)
    public static void Sodossho_Nibondhon(String nidorbrn, String nidorbrnValue, String dob, String name, String nameBangla, String fatherName, String motherName, String mobile, String gender, String email, String eduLevel, String jobType, String maritalStatus, String district, String upazila, String thana, String address) throws InterruptedException, IOException {
        /*Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Test_2"); //Shomobay Shomiti*/

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-registration");

        SmallWait();
        SelectBy_Name_Radiobox("NidOrBrn", nidorbrn); //Nid or BirthRegNo

        SmallWait();
        if(driver.findElement(By.xpath("//input[@name='NidOrBrn' and @value='1']")).isSelected()){
            //SmallWait();
            FindElementByName_Details("nid", nidorbrnValue); //NID or BirthRegNo
        }
        else if(driver.findElement(By.xpath("//input[@name='NidOrBrn' and @value='2']")).isSelected()){
            //SmallWait();
            FindElementByName_Details("brn", nidorbrnValue); //NID or BirthRegNo
        }

        //SmallWait();
        FindElementByXpath_Details("//*[@type='tel']", dob); //DOB

        //SmallWait();
        FindElementByName_Details("memberName", name); //MemberName

        //SmallWait();
        FindElementByName_Details("memberNameBangla", nameBangla); //MemberNameBangla

        //SmallWait();
        FindElementByName_Details("fatherName", fatherName); //Father Name

        //SmallWait();
        FindElementByName_Details("motherName", motherName); //Mother Name

        //SmallWait();
        FindElementByName_Details("mobileNo", mobile); //Mobile

        SmallWait();
        SelectBy_Name_Radiobox("radioValue", gender); //Gender

        //SmallWait();
        FindElementByName_Details("email", email); //Email

        //SmallWait();
        SelectBy_Name_VisibleText("educationLevelId", eduLevel); //Education Level //স্নাতকোত্তর, এইচ.এস.সি, স্নাতক, স্নাতকোত্তর, পঞ্চম শ্রেণী, অষ্টম শ্রেণী

        //SmallWait();
        SelectBy_Name_VisibleText("jobType", jobType); //Job Type //শিক্ষক, সরকারি কর্মচারী, কৃষক, বেসরকারী কর্মচারী, গৃহিনী, স্বনির্ভর, অন্যান্য

        SmallWait();
        WebElement mstatus =driver.findElement(By.name("maritalStatusId"));
        Select select = new Select(mstatus);
        select.selectByVisibleText(maritalStatus); //Marital Status //বিবাহিত, অবিবাহিত, ডিভোর্সি,বিপত্নীক, অন্যান্য

        WebElement value = select.getFirstSelectedOption();
        String text = value.getText();

        SmallWait();
        if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='radioValue' and @value='1']")).isSelected()){
            FindElementByName_Details("spouseName","Mrs.Xyz"); //Only for Married
        }
        else if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='radioValue' and @value='2']")).isSelected()){
            FindElementByName_Details("spouseName","Mr.Xyz"); //Only for Married
        }

        Scroll_Down();

        SmallWait();
        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Present Address

        SmallWait();
        SelectBy_Name_VisibleText("district", district); //District

        SmallWait();
        SelectBy_Name_VisibleText("upazila", upazila); //Union

        SmallWait();
        SelectBy_Name_VisibleText("uniThanaPawNameBangla", thana);

        SmallWait();
        FindElementByName_Details("villageArea", address);

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

        driver.findElement(By.xpath("//*[@type='button' and @aria-label='আগের পাতায়']")).click(); //Button_আগের পাতায়
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='বন্ধ করুন']")).click(); //Button_বন্ধ করুন
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন*/

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();

        SmallWait();
        int size = driver.findElements(By.xpath("(.//*[@aria-label='a dense table'])")).size();

        if(size > 0){
            int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr")).size();
            System.out.println(tr);

            if(tr < 6){
                driver.findElement(By.xpath("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']")).click();
            }
            else{
                FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

                LongWait();
                CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/designation");
            }
        }
        else{
            FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");
        }
    }

    @Test(description = "This is for shomiti create(committee bebosthapona) scenario", priority =7, enabled = true)
    public static void Committee_Bebosthapona() throws InterruptedException {
        /*Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");
        //----------------------------------------------------------------------------//*/

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/designation");

        SmallWait();
        SelectBy_Name_VisibleText("organizerp","মোস্তাফিজুর রহমান"); //Shongothok

        SmallWait();
        SelectBy_Name_VisibleText("communicationP","আসাদ হক"); //Jogajoger Bekti

        SmallWait();
        SelectBy_Name_VisibleText("signingp","হাসান উদ্দিন"); //Kndriyo Shomir Pokkhe Sakkhorito Bekti

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u' and @name='']","৬ জন"); //Member //৯ জন //১২ জন
        //================================================================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[1]","মোস্তাফিজুর রহমান");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[1]","সভাপতি");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[2]","আসাদ হক");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[2]","সহ-সভাপতি");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[3]","হাসিনা খাতুন");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[3]","সাধারন সম্পাদক");
        //===================================================//
        Scroll_Down();

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[4]","হালিমা আক্তার");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[4]","যুগ্ম সম্পাদক");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[5]","হাসান উদ্দিন");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[5]","প্রচার সম্পাদক");
        //===================================================//
        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[6]","কবির উদ্দিন");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedDesig'])[6]","কোষাধ্যক্ষ");
        //===================================================//

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-expenditure");
    }

    @Test(description = "This is for shomiti create(arthik totthadi) scenario", priority =8, enabled = true)
    public static void Arthik_Totthadi() throws InterruptedException {
        /*Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");
        //-------------------------------------------------------------------------------//*/

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-expenditure");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[1]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[1]","5000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[1]","3000"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[2]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[2]","2000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[2]","500"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[3]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[3]","10000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[3]","2000"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[4]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[4]","4000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[4]","2000"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[5]","2"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[5]","4000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[5]","1500"); //loanOutstanding

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[6]","1"); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[6]","4000"); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[6]","5000"); //loanOutstanding

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='হালনাগাদ করুন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/budget");
    }

    @Test(description = "This is for shomiti create(shomitir budget) scenario", priority =9, enabled = true)
    public static void Shomiti_Budget() throws InterruptedException {
        /*Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");
        //------------------------------------------------------------------------------//*/

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

    @Test(description = "This is for shomiti create(shomitir aay_beey) scenario", priority =10, enabled = true)
    public static void Shomitir_Aay_Beey() throws InterruptedException {
        /*Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");
        //-----------------------------------------------------------------------------------//*/

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

    @Test(description = "This is for shomiti create(kagoj potradi) scenario", priority =11, enabled = true)
    public static void Kagoj_Potradi() throws InterruptedException, IOException {
        /*Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");
        //------------------------------------------------------------------------------//*/

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

    @Test(description = "This is for shomiti create(churanto data somuho) scenario", priority =12, enabled = true)
    public static void Churanto_Data_Somuho() throws InterruptedException {
        /*Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");
        //------------------------------------------------------------------------//*/

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
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/reports/basic-report/document-download");
    }

    @Test(description = "This is for shomiti approval scenario", priority =13, enabled = true)
    public static void Shomiti_Approval() throws InterruptedException {
        Logout_Coop();
        Admin_Login();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");

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

        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");

        SmallWait();
        Organizer_Login();

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]","Shomobay Shomiti -অনুমোদিত সমিতি");

        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,"Shomiti Successfully Created");
    }

    @AfterSuite
    public static void Close(){
        FirefoxQuit();
        SendEmail();
    }
}
