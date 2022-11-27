package com.RDCD_Coop.Primary_Samity;

import com.BaseClass.*;
import com.RDCD_Coop.DataProvider.DataProviderClass;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Listeners(Screenshot.class)
public class Create_Primary_Shomiti extends BaseClass {

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://"+link+"/login");
    }

    @Test(description = "This is for SSO login scenario", priority = 1, enabled = false)
    public static void SSO_Login() throws InterruptedException {
        SSO_Admin_Login();

        FindElementByXpath_Click("//a[contains(@href,'http://dashboard.rdcd.orangebd.com/admin/users')]");

        SmallWait();
        WebElement uco = driver.findElement(By.xpath("//a[@href='http://dashboard.rdcd.orangebd.com/admin/users/67/edit']"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click();", uco);

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-action='button#confirm'])[1]");

        SmallWait();
        FindElementByXpath_Click("(.//*[@class='btn btn-default'])[3]");

        SmallWait();
        FindElementByXpath_Click("//a[contains(@href,'http://dashboard.rdcd.orangebd.com/redirectTo/6')]");
    }

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true, enabled = true)
    public static void Login() throws InterruptedException {
        SmallWait();

        Organizer_Login();

        SmallWait();
    }

    @Test(description = "This is for name clearance scenario", dataProvider = "Name_Clearance", dataProviderClass = DataProviderClass.class, priority =2, enabled = true)
    public static void NameClearance(String division, String district, String upazilla, String sType) throws InterruptedException {
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

        SelectBy_Name_VisibleText("division", division);
        SelectBy_Name_VisibleText("district", district);
        SelectBy_Name_VisibleText("upazila","উপজেলা সমবায় অফিস, "+upazilla+", "+district);
        SelectBy_Name_VisibleText("samityTypeId", sType);

        WebElement name = driver.findElement(By.name("samityName"));
        String text = name.getAttribute("value");

        if(text.isEmpty()){
            name.sendKeys(sname);
        }
        else{
            name.clear();
            name.sendKeys(sname);
        }

        FindElementByCssSelector_Click(".MuiButton-sizeMedium"); //Submit Button

        SmallWait();

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is shomiti name clearance approve scenario", priority =3, enabled = true)
    public static void NameClearance_Approve() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/login/")){
            Khulna_Admin_Login();
        }

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

                    Scroll_Down_FindElement("serviceActionId");
                    //Scroll_Down();

                    break;
                }
                else{
                    System.out.println("Shomiti does not exist");
                }
            }
            else{
                System.out.println("Service not available");
            }
        }

        Approve_Text("Name Clearance approved for " + "'"+sname+"'");

        SmallWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout_Coop();

        LongWait();
    }

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario",dataProvider = "Initial_Info", dataProviderClass = DataProviderClass.class, priority =4, enabled = true)
    public static void PrathomikTottho(String union,String address,String election_area,String work_area,String work_address,String date,String fee,String share,String sharePrice,String sellShare,String phone,String mobile,String email,String enterprise,String project,String website) throws InterruptedException {

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

        SelectBy_Name_VisibleText("samityUniThanaPawIdType", union);
        FindElementByName_Details("samityDetailsAddress", address);

        SelectBy_Name_VisibleText("memberAreaType","ইউনিয়ন/পৌরসভা/থানা");
        Scroll_Down();
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[2]", election_area);

        //SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Work Place
        SelectBy_Name_VisibleText("workingAreaType","গ্রাম/মহল্লা");
        SelectBy_Xpath_VisibleText("(.//*[@name='samityUniThanaPawIdType'])[3]", work_area);
        FindElementByXpath_Details("(.//*[@name='detailsAddress'])[2]", work_address);

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']");

        FindElementByXpath_Date("//*[@type='tel']",date);
        FindElementByName_Details("memberAdmissionFee", fee);
        FindElementByName_Details("noOfShare",share);
        FindElementByName_Details("sharePrice", sharePrice);
        FindElementByName_Details("soldShare",sellShare);
        FindElementByName_Details("phoneNo",phone);
        FindElementByName_Details("mobileNo",mobile);
        FindElementByName_Details("emailId",email);
        SelectBy_Name_VisibleText("enterprisingId",enterprise);
        SelectBy_Xpath_VisibleText("//select[@name='projectId']",project);
        FindElementByName_Details("website",website);

        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন ও পরবর্তী পাতায়']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =5, enabled = true)
    public static void Lokkho_Uddessho() throws InterruptedException {

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        //SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", dataProvider = "Sodossho_Nibondhon", dataProviderClass = DataProviderClass.class, priority =6, enabled = true)
    public static void Sodossho_Nibondhon(String nidorbrn, String nidorbrnValue, String dob, String name, String nameBangla, String fatherName, String motherName, String mobile, String gender, String email, String eduLevel, String jobType, String religion, String maritalStatus, String district, String upazila, String thana, String address) throws InterruptedException, IOException {

        SmallWait();

        int level = driver.findElements(By.xpath("//div[contains(text(),'আপনি কোন সদস্য যোগ করেননি ! নতুন সদস্য যোগ করতে')]")).size();
        if(level > 0){
            FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");
        }

        SmallWait();
        SelectBy_Name_Radiobox("NidOrBrn", nidorbrn);

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
        SelectBy_Name_Radiobox("genderId", gender);
        FindElementByName_Details("email", email);
        SelectBy_Name_VisibleText("educationLevelId", eduLevel);
        SelectBy_Name_VisibleText("occupationId", jobType);
        SelectBy_Name_VisibleText("religionId", religion);

        WebElement mstatus =driver.findElement(By.name("maritalStatusId"));
        Select select = new Select(mstatus);
        select.selectByVisibleText(maritalStatus);

        WebElement value = select.getFirstSelectedOption();
        String text = value.getText();

        SmallWait();
        if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='1']")).isSelected()){ //If বিবাহিত and gender is male
            FindElementByName_Details("spouseName","Mrs. Xyz");
        }
        else if(text.equalsIgnoreCase("বিবাহিত") && driver.findElement(By.xpath("//input[@name='genderId' and @value='2']")).isSelected()){ //If বিবাহিত and gender is female
            FindElementByName_Details("spouseName","Mr. Xyz");
        }

        Scroll_Down();

        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']"); //Present Address

        SmallWait();
        SelectBy_Name_VisibleText("district", district);

        SmallWait();
        SelectBy_Name_VisibleText("upazila", upazila);

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

    @Test(description = "This is for shomiti create(committee bebosthapona) scenario", dataProvider = "Committee_Bebosthapona", dataProviderClass = DataProviderClass.class, priority =7, enabled = true)
    public static void Committee_Bebosthapona(String name1, String name2, String name3, String name4, String name5, String name6) throws InterruptedException {

        SmallWait();
        SelectBy_Name_VisibleText("organizerp", name1);

        SelectBy_Name_VisibleText("communicationP", name2);

        SelectBy_Xpath_Checkbox("//*[@class='PrivateSwitchBase-input MuiSwitch-input css-1m9pwf3' and @type='checkbox']");

        SelectBy_Name_VisibleText("signingp", name1);

        SmallWait();
        SelectBy_Xpath_VisibleText("//select[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u' and @name='']","৬ জন"); //Member //৯ জন //১২ জন
        //================================================================================================//
        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[1]", name1);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[1]","সভাপতি");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[2]", name2);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[2]","সহ-সভাপতি");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[3]", name3);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[3]","সাধারন সম্পাদক");

        Scroll_Down();

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[4]", name4);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[4]","যুগ্ম সম্পাদক");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[5]", name5);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[5]","প্রচার সম্পাদক");

        SelectBy_Xpath_VisibleText("(.//*[@name='selectedId'])[6]", name6);
        SelectBy_Xpath_VisibleText("(.//*[@name='role'])[6]","কোষাধ্যক্ষ");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(arthik totthadi) scenario", dataProvider = "Arthik_Totthadi", dataProviderClass = DataProviderClass.class, priority =8, enabled = true)
    public static void Arthik_Totthadi(String sh1, String sv1, String lo1,String sh2, String sv2, String lo2,String sh3, String sv3, String lo3,String sh4, String sv4, String lo4,String sh5, String sv5, String lo5,String sh6, String sv6, String lo6) throws InterruptedException {

        SmallWait();

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

    @Test(description = "This is for shomiti create(shomitir budget) scenario", priority =9, enabled = true)
    public static void Shomiti_Budget() throws InterruptedException {

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

    @Test(description = "This is for shomiti create(shomitir aay_beey) scenario", priority =10, enabled = true)
    public static void Shomitir_Aay_Beey() throws InterruptedException {
        SmallWait();

        //---------------------- আয় ----------------------// (Be Careful with Xpath, when increase or decrease)
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

    @Test(description = "This is for shomiti create(kagoj potradi) scenario", priority =11, enabled = true)
    public static void Kagoj_Potradi() throws InterruptedException, IOException {

        //SmallWait();

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

        //SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(churanto data somuho) scenario", priority =12, enabled = true)
    public static void Churanto_Data_Somuho() throws InterruptedException {

        SmallWait();

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='আবেদনের চূড়ান্ত জমা']");

        FindElementByName_Details("invoiceNo","0012456");
        FindElementByXpath_Details("(.//*[@type='tel'])[1]","01082020");
        SelectBy_Name_Radiobox("viaDocuments","e"); //Upojela Office(u), Email(e)
        SelectBy_Name_Checkbox("declaration");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='আবেদনের চূড়ান্ত জমা']");

        LongWait();

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for shomiti approval scenario", priority =13, enabled = true)
    public static void Shomiti_Approval() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/login/")){
            Khulna_Admin_Login();
        }

        SmallWait();
        Menu_Approve("//span[text()='অনুমোদন']");

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

                    Scroll_Down_FindElement("serviceActionId");

                    break;
                }
                else{
                    System.out.println("Shomiti does not exist");
                }
            }
            else{
                System.out.println("Service not available");
            }
        }

        Approve_Text("Samity approved named " + "'"+sname+"'");

        SmallWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        SmallWait();
        Scroll_Down();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LargeWait();
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
