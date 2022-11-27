package com.RDCD_Coop.Central_Samity;

import com.BaseClass.BaseClass;
import com.RDCD_Coop.DataProvider.DataProviderClass;
import com.RetryScenario.Screenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Listeners(Screenshot.class)
public class Central_Individual_Methods extends BaseClass {

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

    @Test(description = "This is for name clearance scenario", dataProvider = "Name_Clearance", dataProviderClass = DataProviderClass.class, priority =2, enabled = false)
    public static void NameClearance(String division, String district, String upazilla, String sType) throws InterruptedException {
        Menu_AssociationManagement("//span[text()='নেম ক্লিয়ারেন্স']");

        SmallWait();

        List<WebElement> user = driver.findElements(By.name("samityLevel"));
        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase("C")){
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

    @Test(description = "This is shomiti name clearance approve scenario", priority =3, enabled = false)
    public static void NameClearance_Approve() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/login/")){
            Khulna_Admin_Login();
        }

        SmallWait();
        Menu_Approve("//span[text()='অনুমোদন']");

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

        SmallWait();
        Approve_Text("Name Clearance approved for " + "'"+sname+"'");

        SmallWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for shomiti create(Prathomik Tottho) scenario",dataProvider = "Initial_Info", dataProviderClass = DataProviderClass.class, priority =4, enabled = false)
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

    @Test(description = "This is for shomiti create(Lokkho o Uddessho) scenario", priority =5, enabled = false)
    public static void Lokkho_Uddessho() throws InterruptedException {

        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        //SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(Sodossho nibondhon) scenario", dataProvider = "Sodossho_Nibondhon_Central", dataProviderClass = DataProviderClass.class, priority =6, enabled = false)
    public static void Sodossho_Nibondhon(String samity, String date) throws InterruptedException, IOException {
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
            //driver.navigate().to("http://10.11.200.30:5001/samity-management/coop/member-registration/");
        }
        //---------------------------------------------------------------------------------------------------//
        SmallWait();

        SelectBy_Name_VisibleText("samityName", samity);

        FindElementByXpath_Date("//*[@type='tel']", date);

        SmallWait();
        UploadPicture(".MuiGrid-root:nth-child(1) > .MuiPaper-root > .MuiBox-root .MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\resulation.exe");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        int size = driver.findElements(By.xpath("(.//*[@aria-label='a dense table'])")).size();

        if(size > 0){
            int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div[2]/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
            System.out.println(tr);

            if(tr < 6){
                Scroll_Up();
            }
            else{
                FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");
            }
        }
    }

    @Test(description = "This is for shomiti create(committee bebosthapona) scenario", dataProvider = "Committee_Bebosthapona_Central", dataProviderClass = DataProviderClass.class, priority =7, enabled = true)
    public static void Committee_Bebosthapona(String name1, String name2, String name3, String name4, String name5, String name6) throws InterruptedException {
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //---------------------------------------------------------------------------------------------------//
        SmallWait();
        SelectBy_Name_VisibleText("organizerp",name1); //Shongothok

        SelectBy_Name_VisibleText("communicationP",name2); //Jogajoger Bekti

        SelectBy_Name_VisibleText("signingp",name1); //Kndriyo Shomir Pokkhe Sakkhorito Bekti

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

    @Test(description = "This is for shomiti create(arthik totthadi) scenario", priority =8, enabled = true)
    public static void Arthik_Totthadi() throws InterruptedException {
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //---------------------------------------------------------------------------------------------------//

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='হালনাগাদ করুন']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(shomitir budget) scenario", priority =9, enabled = true)
    public static void Shomiti_Budget() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //---------------------------------------------------------------------------------------------------//
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
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //---------------------------------------------------------------------------------------------------//
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
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //---------------------------------------------------------------------------------------------------//

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

        //SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='পরবর্তী পাতা']");

        SmallWait();
    }

    @Test(description = "This is for shomiti create(churanto data somuho) scenario", priority =12, enabled = true)
    public static void Churanto_Data_Somuho() throws InterruptedException {
        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+link+"/dashboard/")){
            IncompleteApplication(sname);
        }
        //---------------------------------------------------------------------------------------------------//

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
        LongWait();
        Approve_Text("Samity approval request for " + "'"+sname+"'");

        SelectBy_Name_VisibleText("serviceActionId","বিভাগ থেকে সুপারিশ ও অগ্রায়ন");

        //বিভাগীয় সমবায় অফিস
        WebElement officeType = driver.findElement(By.xpath("(.//*[@role='combobox'])[1]"));
        officeType.click();
        officeType.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN, Keys.ENTER);

        //বিভাগীয় সমবায় কার্যালয়, খুলনা
        WebElement office = driver.findElement(By.xpath("(.//*[@role='combobox'])[2]"));
        office.click();
        office.sendKeys(Keys.ARROW_UP,Keys.ARROW_UP,Keys.ARROW_UP, Keys.ENTER);

        //মো. মিজানুর রহমান - বিভাগীয় যুগ্ম-নিবন্ধক
        WebElement person = driver.findElement(By.xpath("(.//*[@role='combobox'])[3]"));
        person.click();
        person.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);


        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        Logout_Coop();

        JR_Login();

        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        Scroll_Down();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LargeWait();

        Logout_Coop();

        SmallWait();
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
