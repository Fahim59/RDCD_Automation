package com.RDCD_Coop.Manual_Samity;

import com.BaseClass.*;
import com.RDCD_Coop.DataProvider.DataProviderClass;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import java.io.IOException;

@Listeners(Screenshot.class)
public class Shomiti_Online extends BaseClass {

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://"+link+"/login");
    }

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true, enabled = true)
    public static void Login() throws InterruptedException {
        SmallWait();

        Khulna_Admin_Login();
        //Jessore_Admin_Login();
        //Mymensingh_Admin_Login();
        //Organizer_Login();

        SmallWait();
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

    @Test(description = "This is for shomiti online scenario",dataProvider = "Samity_Manual", dataProviderClass = DataProviderClass.class, priority = 2, enabled = false)
    public static void Primary_Shomiti_Online(String sLevel,String sName,String cDate,String rDate,String regNo,String sType,String enterprise,String project,String effectiveness,String union,String address,String election_area,String work_area,String work_address,String apName,String nid,String mobile,String ownOther,String person) throws InterruptedException, IOException {
        Menu_AssociationManagement("//span[text()='সমিতি অনলাইনকরন']");

        SmallWait();

        SelectBy_Name_Radiobox("samityLevel", sLevel);

        FindElementByName_Details("samityName", sName);

        FindElementByXpath_Details("(.//*[@type='tel'])[1]", cDate);
        FindElementByXpath_Details("(.//*[@type='tel'])[2]", rDate);
        FindElementByName_Details("samityCode", "টেস্ট ম্যানুয়াল - "+regNo);
        SelectBy_Name_VisibleText("samityTypeId", sType);
        SelectBy_Name_VisibleText("enterprisingOrg", enterprise);
        SelectBy_Name_VisibleText("projectId", project);
        SelectBy_Name_Radiobox("samityEffectiveness", effectiveness);

        SelectBy_Name_VisibleText("uniThanaPawIdType", union);
        FindElementByName_Details("villageArea", address);

        Scroll_Down();

        SelectBy_Name_VisibleText("memberAreaType","ইউনিয়ন/পৌরসভা/থানা");
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[1]", election_area);

        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[2]", work_area);
        FindElementByXpath_Details("(.//*[@name='detailsAddress'])[2]", work_address);

        //SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']");

        Scroll_Down();

        FindElementByName_Details("authorizedPersonName", apName);
        FindElementByName_Details("authorizedPersonNid", nid);
        FindElementByName_Details("authorizedPersonMobileNo", mobile);

        Scroll_Down();

        FindElementByCssSelector_Click("button.Sanction_btnOne__Bx6G8");

        SelectBy_Xpath_VisibleText("(.//*[@name='documentType'])[1]","উপ আইন");
        SelectBy_Xpath_VisibleText("(.//*[@name='documentType'])[2]","সমিতির প্রত্যয়ন পত্র");

        Scroll_Down();

        FindElementByXpath_Details("(.//*[@name='documentNumber'])[1]","5656");
        FindElementByXpath_Details("(.//*[@name='documentNumber'])[2]","4587");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-md-5:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-md-5:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\certificate.exe");

        Scroll_Down();

        SelectBy_Name_Radiobox("ownOrOthers", ownOther);

        if(ownOther.equalsIgnoreCase("own")){
            SelectBy_Name_VisibleText("officerId", person);
        }
        else if(ownOther.equalsIgnoreCase("others")){
            WebElement officeType = driver.findElement(By.xpath("(.//*[@role='combobox'])[1]"));
            officeType.click();
            officeType.sendKeys(Keys.ARROW_UP, Keys.ENTER);

            if(union.equalsIgnoreCase("দাকোপ")){
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[2]","উপজেলা সমবায় অফিস, মণিরামপুর, যশোর");
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[3]","মো. তারিকুল ইসলাম - উপজেলা সমবায় অফিসার");
            }
            else if(union.equalsIgnoreCase("মণিরামপুর")){
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[2]","উপজেলা সমবায় অফিস, দাকোপ, খুলনা");
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[3]","প্রনয় রঞ্জন মন্ডল - উপজেলা সমবায় অফিসার");
            }
            else if(union.equalsIgnoreCase("অষ্টধার")){
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[2]","উপজেলা সমবায় অফিস, দাকোপ, খুলনা");
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[3]","প্রনয় রঞ্জন মন্ডল - উপজেলা সমবায় অফিসার");
            }
        }

        //SmallWait();
        //FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for shomiti online scenario",dataProvider = "Samity_Manual", dataProviderClass = DataProviderClass.class, priority = 2, enabled = true)
    public static void Central_Shomiti_Online(String sLevel,String sName,String cDate,String rDate,String regNo,String sType,String enterprise,String project,String effectiveness,String union,String address,String election_area,String work_area,String work_address,String samity,String apName,String ownOther,String person) throws InterruptedException, IOException {
        Menu_AssociationManagement("//span[text()='সমিতি অনলাইনকরন']");

        SmallWait();

        SelectBy_Name_Radiobox("samityLevel", sLevel);

        FindElementByName_Details("samityName", sName);

        FindElementByXpath_Details("(.//*[@type='tel'])[1]", cDate);
        FindElementByXpath_Details("(.//*[@type='tel'])[2]", rDate);
        FindElementByName_Details("samityCode", "টেস্ট ম্যানুয়াল - "+regNo);
        SelectBy_Name_VisibleText("samityTypeId", sType);
        SelectBy_Name_VisibleText("enterprisingOrg", enterprise);
        SelectBy_Name_VisibleText("projectId", project);
        SelectBy_Name_Radiobox("samityEffectiveness", effectiveness);

        SelectBy_Name_VisibleText("uniThanaPawIdType", union);
        FindElementByName_Details("villageArea", address);

        Scroll_Down();

        SelectBy_Name_VisibleText("memberAreaType","ইউনিয়ন/পৌরসভা/থানা");
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[1]", election_area);

        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[2]", work_area);
        FindElementByXpath_Details("(.//*[@name='detailsAddress'])[2]", work_address);

        //SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']");

        Scroll_Down();

        SelectBy_Xpath_VisibleText("(.//*[@name='samityName'])[2]", samity);
        SelectBy_Name_VisibleText("newAuth", apName);

        Scroll_Down();

        FindElementByCssSelector_Click("button.Sanction_btnOne__Bx6G8");

        SelectBy_Xpath_VisibleText("(.//*[@name='documentType'])[1]","উপ আইন");
        SelectBy_Xpath_VisibleText("(.//*[@name='documentType'])[2]","সমিতির প্রত্যয়ন পত্র");

        Scroll_Down();

        FindElementByXpath_Details("(.//*[@name='documentNumber'])[1]","5656");
        FindElementByXpath_Details("(.//*[@name='documentNumber'])[2]","4587");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-md-5:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\chalan.exe");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-md-5:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\certificate.exe");

        Scroll_Down();

        SelectBy_Name_Radiobox("ownOrOthers", ownOther);

        if(ownOther.equalsIgnoreCase("own")){
            SelectBy_Name_VisibleText("officerId", person);
        }
        else if(ownOther.equalsIgnoreCase("others")){
            WebElement officeType = driver.findElement(By.xpath("(.//*[@role='combobox'])[1]"));
            officeType.click();
            officeType.sendKeys(Keys.ARROW_UP, Keys.ENTER);

            if(union.equalsIgnoreCase("দাকোপ")){
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[2]","উপজেলা সমবায় অফিস, মণিরামপুর, যশোর");
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[3]","মো. তারিকুল ইসলাম - উপজেলা সমবায় অফিসার");
            }
            else if(union.equalsIgnoreCase("মণিরামপুর")){
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[2]","উপজেলা সমবায় অফিস, দাকোপ, খুলনা");
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[3]","প্রনয় রঞ্জন মন্ডল - উপজেলা সমবায় অফিসার");
            }
            else if(union.equalsIgnoreCase("অষ্টধার")){
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[2]","উপজেলা সমবায় অফিস, দাকোপ, খুলনা");
                SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[3]","প্রনয় রঞ্জন মন্ডল - উপজেলা সমবায় অফিসার");
            }
        }

        //SmallWait();
        //FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }

    @Test(description = "This is for shomiti online approve scenario", priority = 3, enabled = false)
    public static void Approve_Shomiti_Online() throws InterruptedException, IOException {

        SmallWait();
        FindElementByXpath_Click("//span[text()='অনুমোদন']");

        SmallWait();
        SelectBy_Name_VisibleText("serviceId","সমিতি অনলাইনকরন");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String sname = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();

            if(sname.equalsIgnoreCase(sname)){
                SmallWait();
                FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[4]/button");
            }
            else{
                System.out.println("Shomiti does not exist");
            }
        }

        Scroll_Down_FindElement("serviceActionId");

        //SmallWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for shomiti online approve scenario", dataProvider = "Sodossho_Nibondhon", dataProviderClass = DataProviderClass.class, priority = 4, enabled = false)
    public static void Add_Member_Shomiti_Online(String nidorbrn, String nidorbrnValue, String dob, String name, String nameBangla, String fatherName, String motherName, String mobile, String gender, String email, String eduLevel, String jobType, String religion, String maritalStatus, String district, String upazila, String thana, String address) throws InterruptedException, IOException {
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/login/")){
            Organizer_Login();

            SmallWait();
            SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি");

            Menu_AssociationManagement("//span[text()='সদস্যের তথ্য সংশোধন']");
        }
        //========================================================================================//
                                            //Add New Members//
        //========================================================================================//
        SmallWait();

        int level = driver.findElements(By.xpath("//div[contains(text(),'আপনি কোন সদস্য যোগ করেননি ! নতুন সদস্য যোগ করতে')]")).size();
        if(level > 0){
            FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");
        }

        SmallWait();
        SelectBy_Name_Radiobox("NidOrBrn", nidorbrn); //Nid or BirthRegNo

        SmallWait();
        if(driver.findElement(By.xpath("//input[@name='NidOrBrn' and @value='1']")).isSelected()){
            FindElementByName_Details("nid", nidorbrnValue);
        }
        else if(driver.findElement(By.xpath("//input[@name='NidOrBrn' and @value='2']")).isSelected()){
            FindElementByName_Details("brn", nidorbrnValue);
        }

        FindElementByXpath_Details("//*[@type='tel']", dob);

        FindElementByName_Details("memberName", name);

        FindElementByName_Details("memberNameBangla", nameBangla);

        FindElementByName_Details("fatherName", fatherName);

        FindElementByName_Details("motherName", motherName);

        FindElementByName_Details("mobileNo", mobile);

        SmallWait();
        SelectBy_Name_Radiobox("genderId", gender);

        FindElementByName_Details("emailId", email);

        SelectBy_Name_VisibleText("educationLevelId", eduLevel);

        SelectBy_Name_VisibleText("occupationId", jobType);

        SelectBy_Name_VisibleText("religionId", religion);

        SmallWait();
        WebElement mstatus =driver.findElement(By.name("maritalStatusId"));
        Select select = new Select(mstatus);
        select.selectByVisibleText(maritalStatus);

        WebElement value = select.getFirstSelectedOption();
        String text = value.getText();

        SmallWait();
        if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='1']")).isSelected()){ //If বিবাহিত and gender is male
            FindElementByName_Details("spouseName","Mrs.Xyz"); //Only for Married
        }
        else if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='2']")).isSelected()){ //If বিবাহিত and gender is female
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

        FindElementByName_Details("villageArea", address);

        Scroll_Down();

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe"); //Sodossho_Picture

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe"); //Sign_Picture

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();

        SmallWait();
        int size = driver.findElements(By.xpath("(.//*[@aria-label='a dense table'])[2]")).size();

        if(size > 0){
            int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();

            if(tr < 6){
                Scroll_Up();
                FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");
            }
        }
    }

    @Test(description = "This is for shomiti online approve scenario", dataProvider = "Update_Authorize_Member_Info", dataProviderClass = DataProviderClass.class, priority = 5, enabled = false)
    public static void Update_Authorize_Member_Shomiti_Online(String dob, String name, String fatherName, String motherName, String gender, String email, String eduLevel, String jobType, String religion, String maritalStatus, String district, String upazila, String thana, String address) throws InterruptedException, IOException {
        if(!driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/samity-management/member-details-correction")){
            FindElementByXpath_Click("//span[text()='ড্যাশবোর্ড']");

            SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি");

            Menu_AssociationManagement("//span[text()='সদস্যের তথ্য সংশোধন']");
        }
        //========================================================================================//
                                     //Update Authorized Member Info//
        //========================================================================================//
        SmallWait();
        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();

            String mobile_num = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[9]")).getText();

            if(memberName.equalsIgnoreCase("মোস্তাফিজুর রহমান")){         //---------------------//
                if(mobile_num.equalsIgnoreCase("০১৬৮৬০২৬০৩৭")){       //---------------------//
                    SmallWait();
                    FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[11]/button[1]");

                    break;
                }
                else{
                    System.out.println("Mobile number does not match");
                }
            }
            else{
                System.out.println("Member does not exist");
            }
        }

        Scroll_Up();

        FindElementByXpath_Details("//*[@type='tel']", dob); //DOB

        FindElementByName_Details("memberName", name); //MemberName

        FindElementByName_Details("fatherName", fatherName); //Father Name

        FindElementByName_Details("motherName", motherName); //Mother Name

        SmallWait();
        SelectBy_Name_Radiobox("genderId", gender); //Gender

        FindElementByName_Details("emailId", email); //Email

        SelectBy_Name_VisibleText("educationLevelId", eduLevel);

        SelectBy_Name_VisibleText("occupationId", jobType);

        SelectBy_Name_VisibleText("religionId", religion);

        SmallWait();
        WebElement mstatus =driver.findElement(By.name("maritalStatusId"));
        Select select = new Select(mstatus);
        select.selectByVisibleText(maritalStatus);

        WebElement value = select.getFirstSelectedOption();
        String text = value.getText();

        SmallWait();
        if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='1']")).isSelected()){ //If বিবাহিত and gender is male
            FindElementByName_Details("spouseName","Mrs.Xyz"); //Only for Married
        }
        else if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='2']")).isSelected()){ //If বিবাহিত and gender is female
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

        //SmallWait();
        FindElementByName_Details("villageArea", address);

        Scroll_Down();

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe"); //Sodossho_Picture

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(2) > .MuiPaper-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe"); //Sign_Picture

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='হালনাগাদ করুন']");

        SmallWait();

        SmallWait();
        int size1 = driver.findElements(By.xpath("(.//*[@aria-label='a dense table'])[2]")).size();

        if(size1 > 0){
            int tr1 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();

            if(tr1 == 7){
                LongScroll_Up();
                FindElementByXpath_Click("//*[@type='button' and @aria-label='আবেদনটি জমা দিন']");
            }
        }

        SmallWait();

        Logout_Coop();

        LongWait();
    }

    @Test(description = "This is for shomiti online approve scenario", priority = 6, enabled = false)
    public static void Approve_Members_Shomiti_Online() throws InterruptedException, IOException {
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/login")){
            Khulna_Admin_Login();
        }

        SmallWait();
        FindElementByXpath_Click("//span[text()='অনুমোদন']");

        SmallWait();
        SelectBy_Name_VisibleText("serviceId","সদস্যের তথ্য সংযোজন / সংশোধন");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String sname = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();

            if(sname.equalsIgnoreCase(sname)){
                SmallWait();
                FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[4]/button");
            }
            else{
                System.out.println("Shomiti does not exist");
            }
        }

        Scroll_Down_FindElement("serviceActionId");

        SmallWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
    }






















































































    //=====================================================================================================//
    @Test(description = "This is for staff management(podobi boraddhokoron) scenario", priority = 4, enabled = false)
    public static void Staff_Management_AssignTitle() throws InterruptedException {
        Menu_StaffManagement("//span[text()='পদবী বরাদ্দকরন']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/employee-management/assignment");

        SmallWait();
        SelectBy_Name_VisibleText("samityId", sname);

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
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[1]", sname);

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
        SelectBy_Xpath_VisibleText_Diff("(.//*[@role='combobox'])[1]", sname);

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