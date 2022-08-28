package com.RDCD_Coop;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Listeners(Screenshot.class)
public class Create_Shomiti extends BaseClass {
    public static String aShomitiName = "Shomobay Shomiti"; //First name
    public static String dShomitiName = "Shomobay Shomiti 1"; //If same name available
    public static String sname;

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

        User_Login();
        //Admin_Login();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        //CheckNextUrl("http://10.11.200.30:5001/dashboard");
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = false)
    public static void NameClearance() throws InterruptedException {
        Menu_NameClearance();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/name-clearance");
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/name-clearance");

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
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/name-clearance");
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/name-clearance");

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

    @Test(description = "This is shomiti name clearance approve scenario", priority =4, enabled = false)
    public static void NameClearance_Approve() throws InterruptedException {
        Logout_Coop();
        Admin_Login();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        //CheckCurrentUrl("http://10.11.200.30:5001/dashboard");

        SmallWait();
        Menu_Approve();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/approval");
        //CheckNextUrl("http://10.11.200.30:5001/approval");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        //System.out.println(tr);
        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();
            //System.out.println(shomitiName);

            if(shomitiName.equalsIgnoreCase(sname)){
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

        Logout_Coop();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/login");
        //CheckNextUrl("http://10.11.200.30:5001/login");
    }

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario", priority =5, enabled = false)
    public static void PrathomikTottho() throws InterruptedException {
        User_Login();

        Menu_ShomitiCreate();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/registration");
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/coop/registration");

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

        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']"); //Button

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/add-by-laws");
        //CheckNextUrl("http://10.11.200.30:5001/samity-management/coop/add-by-laws");
    }

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =6, enabled = false)
    public static void Lokkho_Uddessho() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/add-by-laws");
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/coop/add-by-laws");

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click(); //Button
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-registration");
        //CheckNextUrl("http://10.11.200.30:5001/samity-management/coop/member-registration");
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", dataProvider = "Sodossho_Nibondhon", dataProviderClass = DataProviderClass.class, priority =7, enabled = true)
    public static void Sodossho_Nibondhon(String nidorbrn, String nidorbrnValue, String dob, String name, String nameBangla, String fatherName, String motherName, String mobile, String gender, String email, String eduLevel, String jobType, String maritalStatus, String district, String upazila, String thana, String address) throws InterruptedException, IOException {
        Menu_ShomitiCreate();

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId","Shomobay Shomiti");
        //------------------------------------------------------------------------------------------//

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-registration");
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/coop/member-registration");

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
                //CheckNextUrl("http://10.11.200.30:5001/samity-management/coop/designation");
            }
        }
        else{
            driver.findElement(By.xpath("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']")).click();
        }

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

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\prottoyon.exe"); //Authentication_Picture
        //UploadPicture(".MuiGrid-root:nth-child(3) > .MuiPaper-root .MuiButton-root","F:\\RDCD_Automation\\Picture\\prottoyon.exe");

        /*driver.findElement(By.xpath("//*[@type='button' and @aria-label='আগের পাতায়']")).click(); //Button_আগের পাতায়
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='বন্ধ করুন']")).click(); //Button_বন্ধ করুন
        driver.findElement(By.xpath("//*[@type='button' and @aria-label='মুছে ফেলুন']")).click(); //Button_মুছে ফেলুন*/

        SmallWait();
        //driver.findElement(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']")).click(); //Button_সংরক্ষন করুন
        driver.navigate().to("http://rdcd.erainfotechbd.com:5005/samity-management/coop/member-registration");
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
