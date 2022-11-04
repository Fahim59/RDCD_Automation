package com.RDCD_Coop.Primary_Samity;

import com.BaseClass.BaseClass;
import com.RDCD_Coop.DataProvider.DataProviderClass;
import com.RetryScenario.Screenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;

@Listeners(Screenshot.class)
public class Approved_Shomiti_Activities extends BaseClass {

    public static String sname = "কুশাব গ্রাম উন্নয়ন প্রকল্প";
    public static String AuthorizePerson = "মোস্তাফিজুর রহমান - ৩";
    public static int option = 1;

    public static String address = "stage-coop.rdcd.gov.bd";
    //public static String address = "10.11.200.30:5001";

    @BeforeClass(enabled = true)
    public static void LaunchBrowser() throws InterruptedException {
        FirefoxLaunch();
        OpenWebsite("http://"+address+"/login");
    }

    @Test(description = "This is for selecting authorize person scenario", priority = 1, enabled = false) //Address
    public static void Shomiti_AuthorizePersonSelect() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+address+"/login")){
            Admin_Login();
        }

        Menu_AssociationManagement("//span[text()='সমিতির অথরাইজড পারসন']");

        SmallWait();
        SelectBy_Name_VisibleText("samityName", sname);

        SmallWait();
        SelectBy_Name_VisibleText("newAuth", AuthorizePerson);

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for approved shomiti's website creation scenario", priority = 6, enabled = true) //Address
    public static void Website_Setup() throws InterruptedException, IOException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+address+"/login")){
            Organizer_Login();

            SmallWait();
            SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি");

            FindElementByXpath_Click("//span[text()='ওয়েব সাইট সেটআপ']");
        }
        //===================================================================//
        SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");
        SelectBy_Name_VisibleText("contentId","লোগো");

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\logo.exe"); //Logo

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("লোগো");
        //---------------------------------------------------------------------//
        //SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");
        SelectBy_Name_VisibleText("contentId","ব্যানার");

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\banner.exe");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("ব্যানার");
        //-------------------------------------------------------------------//
        //SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");
        SelectBy_Name_VisibleText("contentId","নোটিশ");

        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("সমবায় অধিদপ্তরের আওতাধীন সকল সমিতির অনুমোদন সংক্রান্ত কমিটির সভা “জুম ক্লাউড মিটিং” এ জনাব সাইফুর রহমান, পরিচালক, সমবায় অধিদপ্তর, ঢাকা এর সভাপতিত্বে আগামী ১৭/০৯/২০২০ খ্রি: বৃহস্পতিবার বেলা ১১.৩০ টায় অনুষ্ঠিত হবে।");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("নোটিশ");
        //--------------------------------------------------------------------//
        //SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");
        SelectBy_Name_VisibleText("contentId","সামাজিক লিংক");

        FindElementByName_Details("facebook","facebook.com");
        FindElementByName_Details("twitter","twitter.com");
        FindElementByName_Details("skype","skype.com");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //--------------------------------------------------------------------//
        //SmallWait();
        SelectBy_Name_VisibleText("pageId","প্রচ্ছদ পাতা");
        SelectBy_Name_VisibleText("contentId","ছবিসমূহ");

        SmallWait();
        FindElementByXpath_Click("//*[text()=' ডকুমেন্ট / ছবি যোগ করুন']");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-md-5:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\first.exe");

        SmallWait();
        UploadPicture("div.MuiGrid-grid-md-5:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label","D:\\Intellij Files\\RDCD_Automation\\Picture\\last.exe");

        Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("ছবিসমূহ");
        //============================================================================================//
        //SmallWait();
        SelectBy_Name_VisibleText("pageId","আমাদের সম্পর্কে");
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("রূপকল্প (Vision):" +Keys.ENTER+ "টেকসই সমবায়, টেকসই উন্নয়ন।" +Keys.ENTER+ "অভিলক্ষ্য (Mission):" +Keys.ENTER+ "সমবায়ীদের সক্ষমতা বৃদ্ধি এবং উদ্যোক্তা সৃষ্টির মাধ্যমে কৃষি, অকৃষি, আর্থিক ও সেবা খাতে টেকসই সমবায় গড়ে তোলা।" +Keys.ENTER+ "কৌশল (Strategy):" +Keys.ENTER+ "সমবায় উদ্যোক্তা সৃষ্টির মাধ্যমে টেকসই উন্নয়ন এবং সরকার কর্তৃক ঘোষিত ডিজিটাল বাংলাদেশ গড়া।");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //===========================================================================================//
        SelectBy_Name_VisibleText("pageId","সেবাসমূহ");
        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("নাগরিক সেবা: " +Keys.ENTER+ "১. সমবায় সমিতির নিবন্ধন" +Keys.ENTER+ "২. সমিতি পরিচর্যা" +Keys.ENTER+ "৩. সমিতির অডিট সম্পাদন" +Keys.ENTER+ "৪. সমিতির নির্বাচন/অন্তর্বর্তী কমিটি নিয়োগ" +Keys.ENTER+ "৫. তথ্য সরবরাহ");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //==========================================================================================//
        SelectBy_Name_VisibleText("pageId","প্রকল্প / কর্মসূচী");
        SelectBy_Name_VisibleText("contentId","ছবি প্রথমে");

        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("ছবি প্রথমে");

        Scroll_Down();

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\pic1.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("ছবি প্রথমে");

        Scroll_Up();
        //-----------------------------------------------------------------------//
        SelectBy_Name_VisibleText("pageId","প্রকল্প / কর্মসূচী");
        SelectBy_Name_VisibleText("contentId","ছবি শেষে");

        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("ছবি শেষে");

        Scroll_Down();

        SmallWait();
        UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\pic2.exe");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Website_Setup_Table("ছবি শেষে");
        //==========================================================================================//
        SelectBy_Name_VisibleText("pageId","সচরাচর জিঞ্জাসা");

        driver.findElement(By.cssSelector(".ql-editor")).sendKeys("১. জাতীয় তথ্য বাতায়ন কী?" +Keys.ENTER+ "জাতীয় তথ্য বাতায়ন বিশ্বের অন্যতম বৃহত্তম তথ্য বাতায়ন যা বাংলাদেশের সকল সরকারি অফিসের তথ্য দ্বারা সমৃদ্ধ ।" +Keys.ENTER+ "২. জাতীয় তথ্য বাতায়ন কেন?" +Keys.ENTER+ "দেশের ইতিহাস-ঐতিহ্যকে তুলে ধরা এবং জনগণের চাহিদামাফিক সহজে তথ্য প্রাপ্তি নিশ্চিত করার জন্যই জাতীয় তথ্য বাতায়ন তৈরি করা হয়েছে।" +Keys.ENTER+ "৩. জাতীয় তথ্য বাতায়ন ওয়েব পোর্টালের বিশেষ বৈশিষ্ট্য কি?" +Keys.ENTER+ "এই ওয়েব পোর্টালকে বাংলাদেশের প্রবেশদ্বার বলা হয় কেননা এই পোর্টালের মাধ্যমে দেশের সরকারি সকল অফিসের সঙ্গে সহজে যোগাযোগ করা যায় এবং সেই সকল অফিস হতে প্রদত্ত সেব সম্পর্কে সম্যক ধারণা লাভ করা যায়।");

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");
        //==========================================================================================//
        SmallWait();
        FindElementByXpath_Click("//span[text()='ড্যাশবোর্ড']");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি");

        String oldTab = driver.getWindowHandle();

        SmallWait();
        FindElementByXpath_Click("//button[text()='ওয়েব সাইট']");

        LongWait();
        driver.switchTo().window(oldTab);
    }

    @Test(description = "This is for shomiti management(update member info) scenario", dataProvider = "Update_Member_Info", dataProviderClass = DataProviderClass.class, priority = 2, enabled = false)
    public static void Update_Member_Info(String nidorbrn, String nidorbrnValue, String dob, String name, String nameBangla, String fatherName, String motherName, String mobile, String gender, String email, String eduLevel, String jobType, String maritalStatus, String district, String upazila, String thana, String address) throws InterruptedException, IOException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+address+"/login")){
            Organizer_Login();
        }

        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি");

        Menu_AssociationManagement("//span[text()='সদস্যের তথ্য সংশোধন']");

        //========================================================================================//
                                            //New Member Addition//
        //========================================================================================//
        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='নতুন সদস্য যোগ করুন']");

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

        FindElementByXpath_Details("//*[@type='tel']", dob); //DOB

        FindElementByName_Details("memberName", name); //MemberName

        FindElementByName_Details("memberNameBangla", nameBangla); //MemberNameBangla

        FindElementByName_Details("fatherName", fatherName); //Father Name

        FindElementByName_Details("motherName", motherName); //Mother Name

        FindElementByName_Details("mobileNo", mobile); //Mobile

        SmallWait();
        SelectBy_Name_Radiobox("genderId", gender); //Gender

        FindElementByName_Details("emailId", email); //Email

        SelectBy_Name_VisibleText("educationLevelId", eduLevel); //Education Level //স্নাতকোত্তর, এইচ.এস.সি, স্নাতক, স্নাতকোত্তর, পঞ্চম শ্রেণী, অষ্টম শ্রেণী

        SelectBy_Name_VisibleText("occupationId", jobType); //Job Type //শিক্ষক, সরকারি চাকুরীজীবি, কৃষক, বেসরকারী চাকুরীজীবি, গৃহিনী, স্বনির্ভর, অন্যান্য

        SmallWait();
        WebElement mstatus =driver.findElement(By.name("maritalStatusId"));
        Select select = new Select(mstatus);
        select.selectByVisibleText(maritalStatus); //Marital Status //বিবাহিত, অবিবাহিত, তালাকপ্রাপ্ত,বিপত্নীক, অন্যান্য

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
        int tr5 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr5; l++){

            String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr["+l+"]/td[5]")).getText();
            String mobile_num = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr["+l+"]/td[10]")).getText();

            if(memberName.equalsIgnoreCase(nameBangla)){
                if(mobile_num.equalsIgnoreCase(mobile)){
                    System.out.println("Member Added Successfully");
                }
                else{
                    System.out.println("Mobile number does not match, Member Addition Failed");
                }
            }
            else{
                System.out.println("Member does not exist, Member Addition Failed");
            }
        }

        Scroll_Down();
        Scroll_Up();
        //========================================================================================//
                                     //Update Existing Member Info//
        //========================================================================================//
        SmallWait();
        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();
            System.out.println(memberName);

            String mobile_num = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[9]")).getText();
            System.out.println(mobile_num);

            if(memberName.equalsIgnoreCase("হাসান উদ্দিন")){         //---------------------//
                if(mobile_num.equalsIgnoreCase("০১৮৫৮৪২৬৩২০")){  //---------------------//
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

        SmallWait();
        FindElementByName_Details("motherName","Mrs. Halima"); //---------------------//

        Long_Scroll_Down();

        SmallWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='হালনাগাদ করুন']");

        SmallWait();
        int tr1 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr1; l++){

            String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr["+l+"]/td[5]")).getText();
            String mobile_num = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr["+l+"]/td[10]")).getText();
            String mName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr["+l+"]/td[7]")).getText();

            if(memberName.equalsIgnoreCase("হাসান উদ্দিন")){              //---------------------//
                if(mobile_num.equalsIgnoreCase("০১৮৫৮৪২৬৩২০")){       //---------------------//
                    if(mName.equalsIgnoreCase("Mrs. Halima")){        //---------------------//
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

        Scroll_Down();
        Scroll_Up();
        //========================================================================================//
                                       //Delete Existing Member//
        //========================================================================================//
        SmallWait();
        int tr2 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr2; l++){

            String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[4]")).getText();
            String mobile_num = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[9]")).getText();

            if(memberName.equalsIgnoreCase("হালিমা আক্তার")){         //---------------------//
                if(mobile_num.equalsIgnoreCase("০১৭৯৮৫৬৬৭২০")){    //---------------------//
                    SmallWait();
                    FindElementByXpath_Click("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/td[11]/button[2]");
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
        int tr4 = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr4; l++){

            String memberName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr["+l+"]/td[5]")).getText();
            String mobile_num = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[4]/div/div[2]/table/tbody/tr["+l+"]/td[10]")).getText();

            if(memberName.equalsIgnoreCase("হালিমা আক্তার")){       //---------------------//
                if(mobile_num.equalsIgnoreCase("০১৭৯৮৫৬৬৭২০")){  //---------------------//
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

        Long_Scroll_Down();
        LongScroll_Up();
        //========================================================================================//

        SmallWait();
        FindElementByXpath_Click("//button[text()='আবেদনটি জমা দিন']");

        SmallWait();

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for shomiti management (update member approval scenario)", priority =3, enabled = false)
    public static void Update_Approval() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+address+"/login")){
            Admin_Login();
        }

        Menu_Approve("//span[text()='অনুমোদন']");

        SmallWait();
        SelectBy_Name_VisibleText("serviceId","সদস্যের তথ্য সংযোজন / সংশোধন");

        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String service = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[1]")).getText();
            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();

            if(service.equalsIgnoreCase("সদস্যের তথ্য সংযোজন / সংশোধন")){
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
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        LongWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for Committee Management(committee gothoner abedon) scenario", priority = 4, enabled = false)
    public static void Apply_for_the_formation_of_the_Committee() throws InterruptedException, IOException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+address+"/login")){
            Organizer_Login();
        }

        SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[1]",sname +" - অনুমোদিত সমিতি");

        SmallWait();
        FindElementByXpath_Click("//span[text()='কমিটি গঠনের আবেদন']");

        SmallWait();
        FindElementByXpath_Click("//button[text()='   কমিটি গঠনের আবেদন করুন']");

        switch (option) {
            case 1:
                //========================================================================================//
                                                   //নির্বাচন কমিটি গঠনের আবেদন//
                //========================================================================================//

                SelectBy_Name_VisibleText("servicesName","নির্বাচন কমিটি গঠনের আবেদন");

                //SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[1]","31082022");

                //SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[2]","05092022");

                //SmallWait();
                SelectBy_Name_VisibleText("documentType","সাংগঠনিক সভার রেজুলেশন"); //নির্বাচনী নোটিশ

                //SmallWait();
                FindElementByName_Details("documentNumber","২৬৩৫");

                Scroll_Down();

                SmallWait();
                UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\resulation.exe");

                Scroll_Down();

                SmallWait();
                SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[3]","৩ জন");

                //SmallWait();
                Application_for_formation_of_election_committee("মোস্তাফিজুর রহমান","সভাপতি");

                //SmallWait();
                Application_for_formation_of_election_committee("আসাদ হক","সহ-সভাপতি");

                Scroll_Down();

                //SmallWait();
                Application_for_formation_of_election_committee("হালিমা আক্তার","সাধারন সম্পাদক");

                Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//button[text()='আবেদন করুন']");

                break;

            case 2:
                //========================================================================================//
                                               //নির্বাচিত  কমিটি সংযোজনের আবেদন//
                //========================================================================================//
                SelectBy_Name_VisibleText("servicesName","নির্বাচিত কমিটি সংযোজনের আবেদন");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[1]","31082022");

                SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[2]","05092022");

                SmallWait();
                SelectBy_Name_VisibleText("documentType","সাংগঠনিক সভার রেজুলেশন"); // নির্বাচনী নোটিশ

                SmallWait();
                FindElementByName_Details("documentNumber","৪৬৪৫");

                Scroll_Down();

                SmallWait();
                UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\resulation.exe");

                Scroll_Down();

                SmallWait();
                SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[3]","৬ জন");

                //SmallWait();
                Application_for_formation_of_election_committee("মোস্তাফিজুর রহমান","সভাপতি");

                //SmallWait();
                Application_for_formation_of_election_committee("আসাদ হক","সহ-সভাপতি");

                Scroll_Down();

                //SmallWait();
                Application_for_formation_of_election_committee("হালিমা আক্তার","সাধারন সম্পাদক");

                //SmallWait();
                Application_for_formation_of_election_committee("হাসান উদ্দিন","যুগ্ম সম্পাদক");

                Scroll_Down();

                //SmallWait();
                Application_for_formation_of_election_committee("হাসিনা খাতুন","দপ্তর/প্রকশনা সম্পাদক");

                //SmallWait();
                Application_for_formation_of_election_committee("কবির উদ্দিন","সদস্য- ব্যবস্থপনা কমিটি");

                Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//button[text()='আবেদন করুন']");

                break;

            case 3:
                //========================================================================================//
                                                  //অন্তর্বর্তী কমিটি গঠনের আবেদন//
                //========================================================================================//
                SelectBy_Name_VisibleText("servicesName","অন্তর্বর্তী কমিটি গঠনের আবেদন");

                //SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[1]","31082022");

                //SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[2]","05102022");

                //SmallWait();
                SelectBy_Name_VisibleText("documentType","সাংগঠনিক সভার রেজুলেশন"); //নির্বাচনী নোটিশ

                //SmallWait();
                FindElementByName_Details("documentNumber","৭৬০৫");

                Scroll_Down();

                SmallWait();
                UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\resulation.exe");

                Scroll_Down();

                SmallWait();
                SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[3]","৬ জন");

                //SmallWait();
                Application_for_formation_of_election_committee("মোস্তাফিজুর রহমান","সভাপতি");

                //SmallWait();
                Application_for_formation_of_election_committee("আসাদ হক","সহ-সভাপতি");

                Scroll_Down();

                //SmallWait();
                Application_for_formation_of_election_committee("হালিমা আক্তার","সাধারন সম্পাদক");

                //SmallWait();
                Application_for_formation_of_election_committee("হাসান উদ্দিন","যুগ্ম সম্পাদক");

                Scroll_Down();

                //SmallWait();
                Application_for_formation_of_election_committee("হাসিনা খাতুন","দপ্তর/প্রকশনা সম্পাদক");

                //SmallWait();
                Application_for_formation_of_election_committee("কবির উদ্দিন","সদস্য- ব্যবস্থপনা কমিটি");

                Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//button[text()='আবেদন করুন']");

                break;

            case 4:
                //========================================================================================//
                                                        //নিয়োগকৃত প্রথম কমিটি//
                //========================================================================================//
                SelectBy_Name_VisibleText("servicesName","নিয়োগকৃত প্রথম কমিটি");

                //SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[1]","31082022");

                //SmallWait();
                FindElementByXpath_Date("(.//*[@type='tel'])[2]","05102022");

                //SmallWait();
                SelectBy_Name_VisibleText("documentType","সাংগঠনিক সভার রেজুলেশন"); //নির্বাচনী নোটিশ

                //SmallWait();
                FindElementByName_Details("documentNumber","৭৬০৫");

                Scroll_Down();

                SmallWait();
                UploadPicture("label.MuiButton-root","D:\\Intellij Files\\RDCD_Automation\\Picture\\resulation.exe");

                Scroll_Down();

                SmallWait();
                SelectBy_Xpath_VisibleText("(.//*[@class='MuiNativeSelect-select MuiNativeSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-ciw10u'])[3]","৬ জন");

                //SmallWait();
                Application_for_formation_of_election_committee("মোস্তাফিজুর রহমান","সভাপতি");

                //SmallWait();
                Application_for_formation_of_election_committee("আসাদ হক","সহ-সভাপতি");

                Scroll_Down();

                //SmallWait();
                Application_for_formation_of_election_committee("হালিমা আক্তার","সাধারন সম্পাদক");

                //SmallWait();
                Application_for_formation_of_election_committee("হাসান উদ্দিন","যুগ্ম সম্পাদক");

                Scroll_Down();

                //SmallWait();
                Application_for_formation_of_election_committee("হাসিনা খাতুন","দপ্তর/প্রকশনা সম্পাদক");

                //SmallWait();
                Application_for_formation_of_election_committee("কবির উদ্দিন","সদস্য- ব্যবস্থপনা কমিটি");

                Scroll_Down();

                SmallWait();
                FindElementByXpath_Click("//button[text()='আবেদন করুন']");

                break;

            default:
                System.out.println("Wrong Input");
        }

        SmallWait();

        Logout_Coop();

        SmallWait();
    }

    @Test(description = "This is for Committee management (approval scenario)", priority =5, enabled = false)
    public static void Committee_Approval() throws InterruptedException {

        if(driver.getCurrentUrl().equalsIgnoreCase("http://"+address+"/login")){
            Admin_Login();
        }

        Menu_Approve("//span[text()='অনুমোদন']");

        if(option == 1){
            SmallWait();
            SelectBy_Name_VisibleText("serviceId","নির্বাচন কমিটি গঠনের আবেদন");

            //Approval_for_formation_of_election_committee(ShomitiName, "নির্বাচন কমিটি গঠনের আবেদন");
        }
        else if(option == 2){
            SmallWait();
            SelectBy_Name_VisibleText("serviceId","নির্বাচিত কমিটি সংযোজনের আবেদন");

            //Approval_for_formation_of_election_committee(ShomitiName, "নির্বাচিত কমিটি সংযোজনের আবেদন");
        }
        else if(option == 3){
            SmallWait();
            SelectBy_Name_VisibleText("serviceId","অন্তর্বর্তী কমিটি গঠনের আবেদন");

            //Approval_for_formation_of_election_committee(ShomitiName, "অন্তর্বর্তী কমিটি গঠনের আবেদন");
        }

        /*Long_Scroll_Down();
        Scroll_Down_FindElement("serviceActionId");

        LongWait();
        SelectBy_Name_VisibleText("serviceActionId","অনুমোদন");

        LongWait();
        FindElementByXpath_Click("//*[@type='button' and @aria-label='সংরক্ষন করুন']");*/

        Logout_Coop();

        SmallWait();
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
