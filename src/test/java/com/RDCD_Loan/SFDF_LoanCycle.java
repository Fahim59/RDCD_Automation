package com.RDCD_Loan;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;
import java.io.IOException;
import java.time.Duration;

@Listeners(Screenshot.class)
public class SFDF_LoanCycle extends BaseClass {
    public static String pName = "ডিজিটাল নারায়ণগঞ্জ সিটি";

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is for login scenario, actor = sfdf_admin", priority = 1, enabled = true)
    public static void Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/login");

        //Login_Admin();
        //Login_Um();
        Login_Fo();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/samity-management");
    }

    @Test(description = "This is project setup scenario, actor = sfdf_admin", priority =2, enabled = false)
    public static void Setup_Project() throws InterruptedException {
        Project_Setup();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/adminstration/project-setup/create-project");

        SmallWait();
        FindElementByName_Details("projectName","Digital Narayanganj City"); //প্রকল্পের নাম/কর্মসূচী

        SmallWait();
        FindElementByName_Details("projectNameBangla", pName); //প্রকল্পের নাম/কর্মসূচী বাংলা

        SmallWait();
        SelectBy_Name_Radiobox("projectPhase","P"); //প্রকল্প
        //SelectBy_Name_Radiobox("projectPhase","K"); //কর্মসূচি

        SmallWait();
        FindElementByName_Details("projectCode","DNC_01"); //প্রকল্পের কোড/কর্মসূচী কোড

        SmallWait();
        FindElementByName_Details("projectDirector","Mustafizur Rahman"); //প্রকল্প পরিচালক

        SmallWait();
        FindElementByName_Details("projectDuration","24"); //প্রকল্পের/ কর্মসূচীর মেয়াদ(একক-মাস)

        SmallWait();
        FindElementByName_Details("fundSource","Self Funding"); //প্রকল্পের/ কর্মসূচীর অর্থের উৎস

        SmallWait();
        FindElementByName_Details("estimatedExp","150000"); //প্রাক্কলিত ব্যয়/ কর্মসূচীর ব্যয়

        SmallWait();
        WebElement date = driver.findElement(By.xpath("//*[@type='tel']")); //মেয়াদ উত্তীর্ণের তারিখ*
        date.clear();
        date.sendKeys("08122024");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='description'])[2]","তথ্য প্রযুক্তি ব্যবহারের মাধ্যমে নারায়ণগঞ্জবাসীদের জীবন যাত্রার মান উন্নয়ন জনগণের দোর গোড়ায় সরকারি সেবাসমূহ পৌঁছে দেয়া ২০বিশটি সরকারি স্কুল ও মহাবিদ্যালয়ের মধ্যে আন্তঃসংযোগ স্থাপন নারায়ণগঞ্জ সিটি কর্পোরেশনের ১০টি ই-সেবা চালুকরণ");

        SmallWait();
        SelectBy_Name_VisibleText("samityType","সমিতি"); //সমিতি টাইপ

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সফল ভাবে তৈরী হয়েছে')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সফল ভাবে তৈরী হয়েছে",text);
    }

    @Test(description = "This is project allocation scenario, actor = sfdf_admin", priority =3, enabled = false)
    public static void Project_Allocation() throws InterruptedException {
        SmallWait();
        FindElementByXpath_Click("//span[text()='প্রকল্প বরাদ্দ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/adminstration/project-setup/project-assign-toUser");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='serviceId'])[1]","গোপাল চন্দ্র - ফিল্ড অফিসার"); //ব্যবহারকারী

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("//*[@role='combobox']","এসএফডিএফ, উপজেলা অফিস, কোটালীপাড়া গোপালগঞ্জ"); //কার্যালয়

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='serviceId'])[2]","বিপ্লব কুমার বৈদ্য - উপজেলা ব্যবস্থাপক"); //পর্যবেক্ষক/অনুমোদনকারী

        SmallWait();
        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/table/tbody/tr")).size();
        System.out.println("value is:" +tr);

        for(int l = 1; l<= tr; l++){

            String projectName = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();
            //System.out.println(projectName);
            //System.out.println("Value of l is " +l);

            if(projectName.equals(pName)){
                FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/table/tbody/tr["+l+"]/td[3]/span/span/input");
            }
            else{
                System.out.println("Project does not exist");
                //FindElementByXpath_Click("//*[@data-testid='KeyboardArrowRightIcon']");
            }
        }

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'আবেদনটি সফলভাবে প্রেরণ করা হয়েছে')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("আবেদনটি সফলভাবে প্রেরণ করা হয়েছে",text);

        LongWait();
        FindElementByXpath_Click("//span[text()='মোঃ জাকির হোসেন আকন্দ ']");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[3]/div[3]/ul/li[3]"); //Logout

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is project allocation approve scenario, actor = sfdf_um", priority =4, enabled = false)
    public static void Project_Allocation_Approve() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        Login_Um();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='অনুমোদন ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/approval");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr")).size();
        //System.out.println(tr);
        for(int l = 1; l<= tr; l++){

            String service = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr["+l+"]/td[2]")).getText();
            String service_Name = "ব্যবহারকারীকে প্রকল্প বরাদ্দ";

            if(service.equalsIgnoreCase(service_Name)){

                SmallWait();
                FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr["+l+"]/td[6]/button");

                SmallWait();
                Scroll_Down_FindElement("serviceActionId");

                LongWait();
                SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

                LongWait();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='button' and @aria-label='সংরক্ষন করুন']"))).click();
            }
            else{
                System.out.println("Project does not exist");
            }
        }

        SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'আপনার আবেদনটি অনুমোদন করা হয়েছে')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("আপনার আবেদনটি অনুমোদন করা হয়েছে",text);

        LongWait();
        FindElementByXpath_Click("//span[text()='বিপ্লব কুমার বৈদ্য']");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[3]/div[3]/ul/li[3]"); //Logout

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is shamiti create scenario, actor = sfdf_fo", priority =5, enabled = false)
    public static void Shamiti_Create() throws InterruptedException {
        Login_Fo();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        FindElementByXpath_Click("//span[text()='সমিতি তৈরি']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","4");

        SmallWait();
        SelectBy_Name_VisibleText("projectName","ডিজিটাল নারায়ণগঞ্জ সিটি");

        SmallWait();
        FindElementByName_Details("samityName","গুচ্ছ গ্রাম");

        SmallWait();
        SelectBy_Name_VisibleText("foCode","গোপাল চন্দ্র");

        SmallWait();
        SelectBy_Name_VisibleText("unionId","আমতলী");

        SmallWait();
        FindElementByName_Details("address","বাড়ি নং-৩২, রাস্তা-০৯");

        SmallWait();
        SelectBy_Name_Radiobox("radioValue","B");

        SmallWait();
        SelectBy_Name_VisibleText("meetingType","মাসিক");

        SmallWait();
        SelectBy_Name_VisibleText("weeklyType","দ্বিতীয় সপ্তাহ");

        SmallWait();
        SelectBy_Name_VisibleText("meetingDay","মঙ্গলবার");

        SmallWait();
        FindElementByName_Details("memberMinAge","20");

        SmallWait();
        FindElementByName_Details("memberMaxAge","50");

        SmallWait();
        FindElementByName_Details("samityMinMember","3");

        SmallWait();
        FindElementByName_Details("samityMaxMember","20");

        SmallWait();
        FindElementByName_Details("memberAdmissionFee","100");

        SmallWait();
        FindElementByName_Details("groupMinMember","2");

        SmallWait();
        FindElementByName_Details("groupMaxMember","5");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সফল ভাবে তৈরী হয়েছে। অনুমোদনের জন্য অপেক্ষা করুন')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সফল ভাবে তৈরী হয়েছে। অনুমোদনের জন্য অপেক্ষা করুন",text);
    }

    @Test(description = "This is shamiti member add scenario, actor = sfdf_fo", priority =6, enabled = false)
    public static void Member_Add() throws InterruptedException, IOException {

        SmallWait();
        //FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        FindElementByXpath_Click("//span[text()='সদস্য ভর্তি']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management/member-registration");

        SmallWait();
        SelectBy_Name_VisibleText("projectName","ডিজিটাল নারায়ণগঞ্জ সিটি");

        SmallWait();
        SelectBy_Name_Radiobox("samityTypeValue","2");

        SmallWait();
        SelectBy_Name_VisibleText("samityName","গুচ্ছ গ্রাম");

        //SmallWait();
        FindElementByName_Details("memberNameB","হাসিব হাসান");

        //SmallWait();
        FindElementByName_Details("memberNameE","Hasib Hasan");

        //SmallWait();
        FindElementByXpath_Date("//*[@type='tel']","08162000");

        //SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='docType'])[1]","জাতীয় পরিচয়পত্র");

        //SmallWait();
        FindElementByXpath_Details("(.//*[@name='docNumber'])[1]","4688155904");

        //SmallWait();
        SelectBy_Name_VisibleText("classType","স্নাতক");

        //SmallWait();
        FindElementByName_Details("fatherName","Abdul Kalam");

        //SmallWait();
        FindElementByName_Details("fatherNid","4688155904");

        //SmallWait();
        FindElementByName_Details("motherName","Hasina Akter");

        //SmallWait();
        FindElementByName_Details("motherNid","4688155904");

        //SmallWait();
        SelectBy_Name_VisibleText("religion","ইসলাম");

        //SmallWait();
        SelectBy_Name_VisibleText("gender","পুরুষ");

        //SmallWait();
        SelectBy_Name_VisibleText("occupation","বেসরকারী কর্মচারী");

        //SmallWait();
        FindElementByName_Details("annualIncome","50000");

        //SmallWait();
        SelectBy_Name_VisibleText("maritalStatus","অবিবাহিত");

        //SmallWait();
        FindElementByName_Details("mobile","01325478963");

        SmallWait();
        Scroll_Down_Xpath_FindElement("(.//*[@name='acc'])[1]");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='district_id'])[1]","বরিশাল");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='upaCityIdType'])[1]","বরিশাল সদর (কোতোয়ালী)");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='uniThanaPawIdType'])[1]","সায়েস্তাবাদ");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='postOffice'])[1]","1200");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='village'])[1]","বাড়ি নং-৩২, রাস্তা-০৯");

        LongWait();
        SelectBy_Xpath_Checkbox("//input[@class='PrivateSwitchBase-input MuiSwitch-input css-1m9pwf3' and @type='checkbox']");

        SmallWait();
        SelectBy_Name_VisibleText("transactionType","বিকাশ");

        SmallWait();
        FindElementByName_Details("accName","Hasib Hasan");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='acc'])[1]","01325478963");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='docType'])[2]","জাতীয় পরিচয়পত্র");

        SmallWait();
        FindElementByXpath_Details("(.//*[@name='docNumber'])[2]","4688289904");

        SmallWait();
        FindElementByName_Details("nomineeName","Ahsan Kabir");

        SmallWait();
        FindElementByName_Details("percentage","100");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='relation'])[1]","ভাই");

        LongWait();
        //UploadPicture(".css-1t62lt9 > label:nth-child(1) > span:nth-child(2)","D:\\Intellij Files\\RDCD_Automation\\Picture\\nominee.exe");
        UploadPicture(".css-1t62lt9 > label:nth-child(1) > span:nth-child(2)","F:\\RDCD_Automation\\Picture\\nominee.exe");

        SmallWait();
        //UploadPicture(".css-1abzdwk > label:nth-child(1) > span:nth-child(2)","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe");
        UploadPicture(".css-1abzdwk > label:nth-child(1) > span:nth-child(2)","F:\\RDCD_Automation\\Picture\\sign.exe");

        SmallWait();
        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        LongWait();
        UploadPicture("div.hvr-float-shadow:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","D:\\Intellij Files\\RDCD_Automation\\Picture\\nid_front.exe");
        //UploadPicture("div.hvr-float-shadow:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","F:\\RDCD_Automation\\Picture\\nid_front.exe");

        SmallWait();
        UploadPicture("div.hvr-float-shadow:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","D:\\Intellij Files\\RDCD_Automation\\Picture\\nid_back.exe");
        //UploadPicture("div.hvr-float-shadow:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","F:\\RDCD_Automation\\Picture\\nid_back.exe");

        SmallWait();
        UploadPicture("div.hvr-float-shadow:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","D:\\Intellij Files\\RDCD_Automation\\Picture\\sodossho.exe");
        //UploadPicture("div.hvr-float-shadow:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","F:\\RDCD_Automation\\Picture\\sodossho.exe");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-sm-12:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","D:\\Intellij Files\\RDCD_Automation\\Picture\\sign.exe");
        //UploadPicture("div.MuiGrid-grid-sm-12:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1)","F:\\RDCD_Automation\\Picture\\sign.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        /*SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সফল ভাবে তৈরী হয়েছে। অনুমোদনের জন্য অপেক্ষা করুন')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সফল ভাবে তৈরী হয়েছে। অনুমোদনের জন্য অপেক্ষা করুন",text);*/

        SmallWait();
        FindElementByXpath_Click("/html/body/div[1]/div[2]/header/div/div/div[2]/div/div[5]/button/div/span[1]");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[3]/div[3]/ul/li[3]"); //Logout

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is shamiti approve scenario, actor = sfdf_um", priority =7, enabled = false)
    public static void Shomiti_Approve() throws InterruptedException, IOException {

        //Login_Um();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        FindElementByXpath_Click("//span[text()='সমিতি ও সদস্যের অনুমোদন']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management/samity-member-approval");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[1]/div/div/div[3]/button");

        LargeWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr")).size();
        //System.out.println(tr);
        for(int l = 1; l<= tr; l++){

            String shomiti = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr["+l+"]/td[1]")).getText();
            System.out.println(shomiti);
            if(shomiti.equalsIgnoreCase("গুচ্ছ গ্রাম")){
                //SmallWait();
                //FindElementByXpath_Details("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr["+l+"]/td[3]","Approved");

                SmallWait();
                FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr["+l+"]/td[4]/span/input");
            }
            else{
                System.out.println("Shomiti does not exist");
            }
        }

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        /*SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সফলভাবে সর্বমোট 1টি সমিতি/সদস্য অনুমোদন করা হয়েছে')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সফলভাবে সর্বমোট 1টি সমিতি/সদস্য অনুমোদন করা হয়েছে",text);

        SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সেভিংস ও শেয়ার প্রোডাক্ট এর তথ্য পাওয়া যায়নি')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সেভিংস ও শেয়ার প্রোডাক্ট এর তথ্য পাওয়া যায়নি",text);*/

        LargeWait();
        FindElementByXpath_Click("/html/body/div[1]/div[2]/header/div/div/div[2]/div/div[5]/button/div/span[1]");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[3]/div[3]/ul/li[3]"); //Logout

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is shamiti member approve scenario, actor = sfdf_fo, sfdf_um", priority =8, enabled = false)
    public static void Member_Approve() throws InterruptedException, IOException {

        Login_Fo();

        SmallWait();
        FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        Member_Add();

        Login_Um();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        FindElementByXpath_Click("//span[text()='সমিতি ও সদস্যের অনুমোদন']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management/samity-member-approval");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[1]/div/div/div[3]/button");

        LargeWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div[2]/div/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String shomiti = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div[2]/div/table/tbody/tr["+l+"]/td[1]")).getText();
            System.out.println(shomiti);

            String member = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div[2]/div/table/tbody/tr["+l+"]/td[2]")).getText();
            System.out.println(member);

            if(shomiti.equalsIgnoreCase("Test_Create")){ //----------CHANGE------------//
                if(member.equalsIgnoreCase("sad")){     //----------CHANGE------------//
                    SmallWait();
                    FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div[2]/div/table/tbody/tr["+l+"]/td[5]/span/input");
                }
                else{
                    System.out.println("Member does not exist");
                }
            }
            else{
                System.out.println("Shomiti does not exist");
            }
        }

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        /*SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সফলভাবে সর্বমোট 1টি সমিতি/সদস্য অনুমোদন করা হয়েছে')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সফলভাবে সর্বমোট 1টি সমিতি/সদস্য অনুমোদন করা হয়েছে",text);

        SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সেভিংস ও শেয়ার প্রোডাক্ট এর তথ্য পাওয়া যায়নি')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সেভিংস ও শেয়ার প্রোডাক্ট এর তথ্য পাওয়া যায়নি",text);*/

        LargeWait();
        FindElementByXpath_Click("/html/body/div[1]/div[2]/header/div/div/div[2]/div/div[5]/button/div/span[1]");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[3]/div[3]/ul/li[3]"); //Logout

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is dol create scenario, actor = sfdf_fo", priority =9, enabled = false)
    public static void Dol_Create() throws InterruptedException {
        //Login_Fo();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        FindElementByXpath_Click("//span[text()='দল']");
        FindElementByXpath_Click("//span[text()='দল তৈরি']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management/group-registration");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]","এসএফডিএফ ডিফল্ট প্রকল্প"); //-----CHANGE-----//ডিজিটাল নারায়ণগঞ্জ সিটি

        SmallWait();
        SelectBy_Name_VisibleText("samityName","Test_Create"); //-----CHANGE-----//গুচ্ছ গ্রাম

        SmallWait();
        FindElementByName_Details("dolName","Team_Technology");

        LongWait();
        SelectBy_Xpath_Checkbox("//input[@class='PrivateSwitchBase-input MuiSwitch-input css-1m9pwf3' and @type='checkbox']");

        SmallWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/div/div[2]/div/div/div/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String member = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/div/div[2]/div/div/div/table/tbody/tr["+l+"]/td[3]")).getText();
            System.out.println(member);

            if(member.equalsIgnoreCase("df")){     //----------CHANGE------------//
                SmallWait();
                FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/div/div[2]/div/div/div/table/tbody/tr["+l+"]/td[5]/span/input");
            }
            else{
                System.out.println("Member does not exist");
            }
        }

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        /*SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সফল ভাবে তৈরী হয়েছে। অনুমোদনের জন্য অপেক্ষা করুন')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সফল ভাবে তৈরী হয়েছে। অনুমোদনের জন্য অপেক্ষা করুন",text);*/

        Logout();

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is dol approve scenario, actor = sfdf_um", priority =10, enabled = false)
    public static void Dol_Approve() throws InterruptedException {
        Login_Um();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        FindElementByXpath_Click("//span[text()='দল']");
        FindElementByXpath_Click("//span[text()='দল অনুমোদন']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management/group-approval");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]","এসএফডিএফ ডিফল্ট প্রকল্প"); //-----CHANGE-----//ডিজিটাল নারায়ণগঞ্জ সিটি

        SmallWait();
        SelectBy_Name_VisibleText("upazila","এসএফডিএফ, উপজেলা অফিস, কোটালীপাড়া গোপালগঞ্জ");

        SmallWait();
        SelectBy_Name_VisibleText("samityName","Test_Create"); //-----CHANGE-----//গুচ্ছ গ্রাম

        SmallWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String shomiti = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();
            System.out.println(shomiti);

            String dol = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[3]")).getText();
            System.out.println(dol);

            if(shomiti.equalsIgnoreCase("Test_Create")){ //----------CHANGE------------//
                if(dol.equalsIgnoreCase("Team_Technology")){     //----------CHANGE------------//
                    SmallWait();
                    FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div[2]/table/tbody/tr["+l+"]/td[5]/span/input");
                }
                else{
                    System.out.println("Dol does not exist");
                }
            }
            else{
                System.out.println("Shomiti does not exist");
            }
        }

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        /*SmallWait();
        String text = driver.findElement(By.xpath("//div[@class='message' and contains(text(),'সফলভাবে সর্বমোট 1টি দল অনুমোদন করা হয়েছে')]")).getText();
        System.out.println("Message is "+text);
        Assert.assertEquals("সফলভাবে সর্বমোট 1টি দল অনুমোদন করা হয়েছে",text);*/

        Logout();

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is loan apply scenario, actor = sfdf_fo", priority =11, enabled = false)
    public static void Loan_Apply() throws InterruptedException, IOException {
        Login_Fo();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='ঋণ ব্যাবস্থাপনা ']");
        FindElementByXpath_Click("//span[text()='ঋণের আবেদন ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/loan-management/loan-application/sanction");

        SmallWait();
        SelectBy_Name_VisibleText("projectName","এসএফডিএফ ডিফল্ট প্রকল্প"); //-----CHANGE-----

        SmallWait();
        SelectBy_Name_VisibleText("samityName","Test_Create"); //-----CHANGE-----

        SmallWait();
        SelectBy_Name_VisibleText("memberName","df"); //-----CHANGE-----

        //SmallWait();
        FindElementByName_Details("loanAmount","5000");

        //SmallWait();
        FindElementByName_Details("loanPeriod","14");

        //SmallWait();
        SelectBy_Name_VisibleText("frequency","মাসিক");

        //SmallWait();
        SelectBy_Name_VisibleText("loanPurpose","মৎস্য খামার");

        SmallWait();
        SelectBy_Xpath_VisibleText_Diff("//*[@role='combobox']","এসএফডিএফ, উপজেলা অফিস, কোটালীপাড়া গোপালগঞ্জ");

        SmallWait();
        SelectBy_Name_VisibleText("deskId","বিপ্লব কুমার বৈদ্য - উপজেলা ব্যবস্থাপক");

        //SmallWait();
        FindElementByName_Details("remarks","Approve");

        SmallWait();
        SelectBy_Name_Radiobox("grantorOrWitness","J");
        //SelectBy_Name_Radiobox("grantorOrWitness","S");

        SmallWait();
        //SelectBy_Name_Radiobox("personType","M");
        SelectBy_Name_Radiobox("personType","N");

        //SmallWait();
        FindElementByName_Details("nidNumber","4644150805");

        //SmallWait();
        FindElementByXpath_Details("//*[@type='tel']","08/18/2002");

        //SmallWait();
        FindElementByName_Details("grantorName","Akib Uddin");

        //SmallWait();
        FindElementByName_Details("fatherName","Aslam Uddin");

        //SmallWait();
        FindElementByName_Details("motherName","Amina Khatun");

        //SmallWait();
        FindElementByName_Details("mobileNumber","01569874590");

        SmallWait();
        SelectBy_Name_VisibleText("occupation","বেসরকারী কর্মচারী");

        SmallWait();
        SelectBy_Name_VisibleText("relation","বন্ধু");

        //SmallWait();
        FindElementByName_Details("preAddress","বাড়ি নং-৩২, রাস্তা-০৯");

        //SmallWait();
        FindElementByName_Details("perAddress","বাড়ি নং-৩২, রাস্তা-০৯");

        SmallWait();
        Scroll_Down_Xpath_FindElement("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        SmallWait();
        SelectBy_Name_VisibleText("documentType","জাতীয় পরিচয়পত্র");

        SmallWait();
        FindElementByName_Details("documentNumber","4578963214");

        SmallWait();
        UploadPicture("label.MuiButtonBase-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\nid_front.exe");
        //UploadPicture("label.MuiButtonBase-root","F:\\RDCD_Automation\\Picture\\nid_front.exe");

        LargeWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout();

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is loan approve scenario, actor = sfdf_um", priority =12, enabled = false)
    public static void Loan_Approve() throws InterruptedException {
        Login_Um();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='অনুমোদন ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/approval");

        SmallWait();
        SelectBy_Name_VisibleText("projectName","এসএফডিএফ ডিফল্ট প্রকল্প");

        SmallWait();
        SelectBy_Name_VisibleText("serviceId","ঋণের অনুমোদন");

        SmallWait();

        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String project = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr["+l+"]/td[1]")).getText();
            System.out.println(project);

            String service = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr["+l+"]/td[2]")).getText();
            System.out.println(service);

            String shomiti = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr["+l+"]/td[3]")).getText();
            System.out.println(shomiti);

            if(project.equalsIgnoreCase("এসএফডিএফ ডিফল্ট প্রকল্প")){ //----------CHANGE------------//
                if(shomiti.equalsIgnoreCase("Test_Create")){ //----------CHANGE------------//
                    if(service.equalsIgnoreCase("ঋণের অনুমোদন")){
                        SmallWait();
                        FindElementByXpath_Click("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[3]/div/table/tbody/tr["+l+"]/td[6]/button");
                    }
                    else{
                        System.out.println("Service does not exist");
                    }
                }
                else{
                    System.out.println("Shomiti does not exist");
                }
            }
            else{
                System.out.println("Project does not exist");
            }
        }

        SmallWait();
        Scroll_Down_FindElement("serviceActionId");

        LongWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout();

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is loan disberse scenario, actor = sfdf_fo", priority =13, enabled = false)
    public static void Loan_Disberse() throws InterruptedException {
        Login_Fo();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='ঋণ ব্যাবস্থাপনা ']");
        FindElementByXpath_Click("//span[text()='ঋণ বিতরণ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/loan-management/loan-disversment");

        SmallWait();
        SelectBy_Name_VisibleText("projectName","এসএফডিএফ ডিফল্ট প্রকল্প"); //-----CHANGE-----

        SmallWait();
        SelectBy_Name_VisibleText("samityName","Test_Create"); //-----CHANGE-----

        SmallWait();
        SelectBy_Name_VisibleText("memberName","df"); //-----CHANGE-----

        SmallWait();
        SelectBy_Name_VisibleText("cashOrCheck","নগদ প্রদান");

        SmallWait();
        FindElementByName_Details("discription","Cash Given");


        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout();

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is loan disberse scenario, actor = sfdf_fo", priority =14, enabled = false)
    public static void Loan_Repayment() throws InterruptedException {
        //Login_Fo();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/samity-management");

        SmallWait();
        FindElementByXpath_Click("//span[text()='লেনদেন']");
        FindElementByXpath_Click("//span[text()='সঞ্চয় ও ঋণ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/loan-management/loan-transaction/transaction");

        SmallWait();
        SelectBy_Name_VisibleText("projectName","এসএফডিএফ ডিফল্ট প্রকল্প"); //-----CHANGE-----

        SmallWait();
        SelectBy_Name_VisibleText("samityName","Test_Create"); //-----CHANGE-----

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout();

        SmallWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/login");
    }
}
