package com.RDCD_Coop.Primary_Samity;

import com.BaseClass.BaseClass;
import com.RDCD_Coop.DataProvider.DataProviderClass;
import com.RetryScenario.Screenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Listeners(Screenshot.class)
public class Primary_Individual_Methods extends BaseClass {

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        driver.manage().window().maximize();
        OpenWebsite("http://"+link+"/login");
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

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true, enabled = true)
    public static void Login() throws InterruptedException {
        SmallWait();

        Organizer_Login();

        SmallWait();
    }

    @Test(description = "This is for name clearance scenario", priority =2, enabled = false)
    public static void NameClearance() throws InterruptedException {
        Menu_AssociationManagement("//span[text()='নেম ক্লিয়ারেন্স']");

        SmallWait();

        List<WebElement> user = driver.findElements(By.name("samityLevel"));
        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("P")){
                if(!option.isSelected()){
                    option.click();
                }
            }
        }

        SelectBy_Name_VisibleText("division","খুলনা"); //Division
        SelectBy_Name_VisibleText("district","খুলনা"); //District
        SelectBy_Name_VisibleText("upazila","উপজেলা সমবায় অফিস, দাকোপ, খুলনা"); //Office Name
        SelectBy_Name_VisibleText("samityTypeId","মৃৎশিল্পী সমবায় সমিতি"); //Organization Category

        WebElement name = driver.findElement(By.name("samityName"));
        String text = name.getAttribute("value");

        if(text.isEmpty()){
            name.sendKeys(sname);
        }
        else{
            name.clear();
            name.sendKeys(sname);
        }

        SmallWait();
        FindElementByCssSelector_Click(".MuiButton-sizeMedium"); //Submit Button

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for cancelling name clearance scenario", priority = 4, enabled = false)
    public static void NameClearance_Cancel() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        LongWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr")).size();

        for(int l = 1; l<= tr; l++){

            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();

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

    @Test(description = "This is shomiti name clearance approve scenario", priority =3, enabled = false)
    public static void NameClearance_Approve() throws InterruptedException {
        Admin_Login();

        SmallWait();

        SmallWait();
        Menu_Approve("//span[text()='অনুমোদন']");

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

        driver.findElement(By.cssSelector("div.tox-icon > svg:nth-child(1) > path:nth-child(1)")).click();
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("#tinymce")).sendKeys("Name Clearance approved for " + "'"+sname+"'");
        driver.switchTo().defaultContent();

        SmallWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario", priority =4, enabled = false)
    public static void PrathomikTottho() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/login/")){
            Organizer_Login();
        }

        Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();

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
                SelectBy_Name_VisibleText("samityName", sname);  //------- CHANGE -------//
            }
            else{
                System.out.println("Shomiti is not available");

                SmallWait();
                SelectBy_Name_Radiobox("samityLevel","2");

                SmallWait();
                SelectBy_Xpath_VisibleText("(.//*[@name='projectId'])[1]",sname); //------- CHANGE -------//
            }
        }
        else{
            System.out.println("No Incomplete Shomiti is available");

            SmallWait();
            SelectBy_Name_Radiobox("samityLevel","P");

            SmallWait();
            SelectBy_Name_VisibleText("samityName", sname); //------- CHANGE -------//
        }

        SelectBy_Name_VisibleText("samityUniThanaPawIdType","দাকোপ"); //Union //দাকোপ //ময়মনসিংহ পৌরসভা
        FindElementByName_Details("samityDetailsAddress","বাড়ি নং-৩২, রাস্তা-০৯"); //Address

        SelectBy_Name_VisibleText("memberAreaType","ইউনিয়ন/পৌরসভা/থানা");
        Scroll_Down();
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[2]","দাকোপ");

        //SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Work Place
        SelectBy_Name_VisibleText("workingAreaType","গ্রাম/মহল্লা");
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[3]","দাকোপ");
        FindElementByXpath_Details("(.//*[@name='detailsAddress'])[2]","বাড়ি নং-৩২, রাস্তা-০৯");

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']");

        FindElementByXpath_Date("//*[@type='tel']","03012022");//Create date

        FindElementByName_Details("memberAdmissionFee", "300"); //Admission fee

        FindElementByName_Details("noOfShare","30");//No of Share

        FindElementByName_Details("sharePrice", "500"); //Share Price

        FindElementByName_Details("soldShare","30");

        FindElementByName_Details("phoneNo","0273835618"); //Phone

        FindElementByName_Details("mobileNo","01738356180"); //Mobile

        FindElementByName_Details("emailId","shomobay_shomiti@gmail.com"); //Email

        SelectBy_Name_VisibleText("enterprisingId","প্রধানমন্ত্রীর কার্যালয়"); //Enterpriceid

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@name='projectId']","আশ্রয়ন "); //Projecteid

        FindElementByName_Details("website","https://www.samity.com"); //Website

        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']"); //Button

        SmallWait();
    }

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =5, enabled = false)
    public static void Lokkho_Uddessho() throws InterruptedException {
        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        //SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", dataProvider = "Sodossho_Nibondhon", dataProviderClass = DataProviderClass.class, priority =6, enabled = false)
    public static void Sodossho_Nibondhon(String nidorbrn, String nidorbrnValue, String dob, String name, String nameBangla, String fatherName, String motherName, String mobile, String gender, String email, String eduLevel, String jobType, String religion, String maritalStatus, String district, String upazila, String thana, String address) throws InterruptedException, IOException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //---------------------------------------------------------------------------------------------------//
        SmallWait();

        int level = driver.findElements(By.xpath("//div[contains(text(),'আপনি কোন সদস্য যোগ করেননি ! নতুন সদস্য যোগ করতে')]")).size();
        if(level > 0){
            FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");
        }

        SmallWait();
        SelectBy_Name_Radiobox("NidOrBrn", nidorbrn); //Nid or BirthRegNo

        //SmallWait();
        if(driver.findElement(By.xpath("//input[@name='NidOrBrn' and @value='1']")).isSelected()){
            FindElementByName_Details("nid", nidorbrnValue); //NID or BirthRegNo
        }
        else if(driver.findElement(By.xpath("//input[@name='NidOrBrn' and @value='2']")).isSelected()){
            FindElementByName_Details("brn", nidorbrnValue); //NID or BirthRegNo
        }

        FindElementByXpath_Details("//*[@type='tel']", dob); //DOB

        FindElementByName_Details("memberName", name); //MemberName

        FindElementByName_Details("memberNameBangla", nameBangla); //MemberNameBangla

        FindElementByName_Details("fatherName", fatherName); //Father Name

        FindElementByName_Details("motherName", motherName); //Mother Name

        FindElementByName_Details("mobileNo", mobile); //Mobile

        //SmallWait();
        SelectBy_Name_Radiobox("genderId", gender); //Gender

        FindElementByName_Details("email", email); //Email

        SelectBy_Name_VisibleText("educationLevelId", eduLevel); //Education Level

        SelectBy_Name_VisibleText("occupationId", jobType); //Job Type

        SelectBy_Name_VisibleText("religionId", religion);

        //SmallWait();
        WebElement mstatus =driver.findElement(By.name("maritalStatusId"));
        Select select = new Select(mstatus);
        select.selectByVisibleText(maritalStatus); //Marital Status

        WebElement value = select.getFirstSelectedOption();
        String text = value.getText();

        SmallWait();
        if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='1']")).isSelected()){ //If বিবাহিত and gender is male
            FindElementByName_Details("spouseName","আমিনা বেগম"); //Only for Married
        }
        else if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='2']")).isSelected()){ //If বিবাহিত and gender is female
            FindElementByName_Details("spouseName","আব্দুল হামিদ"); //Only for Married
        }

        Scroll_Down();

        //SmallWait();
        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Present Address

        SmallWait();
        SelectBy_Name_VisibleText("district", district); //District

        SmallWait();
        SelectBy_Name_VisibleText("upazila", upazila); //Union

        SmallWait();
        SelectBy_Name_VisibleText("uniThanaPawNameBangla", thana);

        FindElementByName_Details("villageArea", address);

        Scroll_Down();

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe"); //Sodossho_Picture

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe"); //Sign_Picture

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        int size = driver.findElements(By.xpath("(.//*[@aria-label='a dense table'])")).size();

        if(size > 0){
            int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr")).size();
            System.out.println(tr);

            if(tr < 6){
                LongScroll_Up();
                FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");
            }
            else{
                LongScroll_Up();
                FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

                SmallWait();
            }
        }
        else{
            LongScroll_Up();
            FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");
        }
    }

    @Test(description = "This is for shomiti create(committee bebosthapona) scenario", dataProvider = "Committee_Bebosthapona", dataProviderClass = DataProviderClass.class, priority =7, enabled = false)
    public static void Committee_Bebosthapona(String name1, String name2, String name3, String name4, String name5, String name6) throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //----------------------------------------------------------------------------//

        SmallWait();
        SelectBy_Name_VisibleText("organizerp",name1); //Shongothok

        SelectBy_Name_VisibleText("communicationP",name2); //Jogajoger Bekti

        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input MuiSwitch-input css-1m9pwf3' and @type='checkbox']");

        SelectBy_Name_VisibleText("signingp",name3); //Kndriyo Shomir Pokkhe Sakkhorito Bekti

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u' and @name='']","৬ জন"); //Member //৯ জন //১২ জন
        //================================================================================================//
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[1]",name1);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[1]","সভাপতি");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[2]",name2);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[2]","সহ-সভাপতি");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[3]",name3);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[3]","সাধারন সম্পাদক");

        Scroll_Down();

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[4]",name4);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[4]","যুগ্ম সম্পাদক");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[5]",name5);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[5]","প্রচার সম্পাদক");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[6]",name6);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[6]","কোষাধ্যক্ষ");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(arthik totthadi) scenario", dataProvider = "Arthik_Totthadi", dataProviderClass = DataProviderClass.class, priority =8, enabled = true)
    public static void Arthik_Totthadi(String sh1, String sv1, String lo1,String sh2, String sv2, String lo2,String sh3, String sv3, String lo3,String sh4, String sv4, String lo4,String sh5, String sv5, String lo5,String sh6, String sv6, String lo6) throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //-------------------------------------------------------------------------------//
        FindElementByXpath_Details("(.//*[@name='noOfShare'])[1]", sh1); //noOfShare
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[1]", sv1); //savingsAmount
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[1]", lo1); //loanOutstanding

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[2]", sh2);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[2]", sv2);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[2]", lo2);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[3]", sh3);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[3]", sv3);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[3]", lo3);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[4]", sh4);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[4]", sv4);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[4]", lo4);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[5]", sh5);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[5]", sv5);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[5]", lo5);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[6]", sh6);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[6]", sv6);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[6]", lo6);
        //--------------------------------------------------------------------------//
        /*FindElementByXpath_Details("(.//*[@name='noOfShare'])[7]", sh1);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[7]", sv1);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[7]", lo1);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[8]", sh2);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[8]", sv2);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[8]", lo2);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[9]", sh3);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[9]", sv3);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[9]", lo3);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[10]", sh4);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[10]", sv4);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[10]", lo4);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[11]", sh5);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[11]", sv5);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[11]", lo5);

        FindElementByXpath_Details("(.//*[@name='noOfShare'])[12]", sh6);
        FindElementByXpath_Details("(.//*[@name='savingsAmount'])[12]", sv6);
        FindElementByXpath_Details("(.//*[@name='loanOutstanding'])[12]", lo6);*/
        //-----------------------------------------------------------------------------//
        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='হালনাগাদ করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(shomitir budget) scenario", priority =9, enabled = false)
    public static void Shomiti_Budget() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }

        SmallWait();
        SelectBy_Name_VisibleText("budgetYear","২০২১-২০২২");

        //-------------- বর্তমান বছরের সমিতির বাজেট --------------// (Be Careful with Xpath, when increase or decrease)
        //---------------------- আয় ----------------------//
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[1]","বাড়ি ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[1]","1500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[2]","বিক্রয়");
        FindElementByXpath_Details("(.//*[@name='amount'])[2]","2500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[3]","চেক বই বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[3]","3500");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[1]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[4]","ফর্ম বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[4]","4500");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[1]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[5]","ভর্তি ফি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[5]","5500");

        //---------------------- ব্যয় ----------------------//

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[6]","বিদ্যুৎ খরচ");
        FindElementByXpath_Details("(.//*[@name='amount'])[6]","500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[7]","ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[7]","1000");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[8]","পেনশন ও গ্র্যাচুইটি");
        FindElementByXpath_Details("(.//*[@name='amount'])[8]","400");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[2]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[9]","সম্মানি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[9]","900");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[2]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[10]","বিনোদন খরচ ");
        FindElementByXpath_Details("(.//*[@name='amount'])[10]","200");

        Long_Scroll_Down();

        //-------------- পরবর্তী বছরের সমিতির বাজেট --------------//
        //---------------------- আয় ----------------------//
        SmallWait();
        SelectBy_Name_VisibleText("budgetFYear","২০২২-২০২৩");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[11]","সবজি বিক্রয়  ");
        FindElementByXpath_Details("(.//*[@name='amount'])[11]","2500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[12]","স্থায়ী সম্পত্তি বিক্রয় হতে লাভ ");
        FindElementByXpath_Details("(.//*[@name='amount'])[12]","4500");

        Long_Scroll_Down();

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[13]","চেক বই বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[13]","1000");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[3]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[14]","ফর্ম বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[14]","3500");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[3]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[15]","বিবিধ আয়");
        FindElementByXpath_Details("(.//*[@name='amount'])[15]","5500");

        //---------------------- ব্যয় ----------------------//

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[16]","কাঁচামাল ক্রয়");
        FindElementByXpath_Details("(.//*[@name='amount'])[16]","3500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[17]","ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[17]","5500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[18]","কৃষি খরচ ");
        FindElementByXpath_Details("(.//*[@name='amount'])[18]","2500");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[4]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[19]","প্রভিডেন্ট ফান্ড/ ভবিষ্যৎ তহবিল ");
        FindElementByXpath_Details("(.//*[@name='amount'])[19]","2500");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[4]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[20]","বাড়ি ভাড়া ভাতা");
        FindElementByXpath_Details("(.//*[@name='amount'])[20]","1500");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(shomitir aay_beey) scenario", priority =10, enabled = false)
    public static void Shomitir_Aay_Beey() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //-----------------------------------------------------------------------------------//

        //---------------------- আয় ----------------------// (Be Careful with Xpath, when increase or decrease)
        SmallWait();

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[1]","বাড়ি ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[1]","1500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[2]","ভর্তি ফি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[2]","5500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[3]","সুদ- সঞ্চয়ী হিসাব");
        FindElementByXpath_Details("(.//*[@name='amount'])[3]","3500");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[1]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[4]","ফর্ম বিক্রয় ");
        FindElementByXpath_Details("(.//*[@name='amount'])[4]","4500");

        //---------------------- ব্যয় ----------------------//
        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[5]","বিদ্যুৎ খরচ");
        FindElementByXpath_Details("(.//*[@name='amount'])[5]","500");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[6]","ভাড়া");
        FindElementByXpath_Details("(.//*[@name='amount'])[6]","1000");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[7]","পেনশন ও গ্র্যাচুইটি");
        FindElementByXpath_Details("(.//*[@name='amount'])[7]","400");

        FindElementByXpath_Click("(.//*[@data-testid='AddIcon'])[2]");

        SelectBy_Xpath_VisibleText("(.//*[@name='details'])[8]","সম্মানি ");
        FindElementByXpath_Details("(.//*[@name='amount'])[8]","900");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(kagoj potradi) scenario", priority =11, enabled = false)
    public static void Kagoj_Potradi() throws InterruptedException, IOException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //------------------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("documentTypeId","সাংগঠনিক সভার রেজুলেশন");
        FindElementByName_Details("docReferenceNo","0012456");
        FindElementByXpath_Details("(.//*[@type='tel'])[1]","01082020");
        FindElementByXpath_Details("(.//*[@type='tel'])[2]","01082024");

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\resulation.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //--------------------------------------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("documentTypeId","নির্বাচনী নোটিশ");
        FindElementByName_Details("docReferenceNo","0012457");
        FindElementByXpath_Details("(.//*[@type='tel'])[1]","01052021");
        FindElementByXpath_Details("(.//*[@type='tel'])[2]","01042023");

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\notice.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(churanto data somuho) scenario", priority =12, enabled = false)
    public static void Churanto_Data_Somuho() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //-------------------------------------------------------------------------------------//
        SmallWait();

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='আবেদনের চূড়ান্ত জমা']");

        FindElementByName_Details("invoiceNo","0012456");

        FindElementByXpath_Details("(.//*[@type='tel'])[1]","01082020");

        SelectBy_Name_Radiobox("viaDocuments","p"); //Upojela Office(u), Email(e)

        SelectBy_Name_Checkbox("declaration");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='আবেদনের চূড়ান্ত জমা']");

        LongWait();

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for shomiti approval scenario", priority =13, enabled = false)
    public static void Shomiti_Approval() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/login/")){
            Admin_Login();
        }

        SmallWait();
        Menu_Approve("//span[text()='অনুমোদন']");

        LongWait();

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

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LargeWait();
        Logout_Coop();

        SmallWait();
        Organizer_Login();

        LargeWait();
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি"); //Shomobay Shomiti -অনুমোদিত সমিতি

        System.out.println("Shomiti Successfully Created");

//        JFrame frame = new JFrame();
//        frame.setAlwaysOnTop(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JOptionPane.showMessageDialog(frame,"Shomiti Successfully Created");
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
