package com.RDCD_Loan;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.*;
import org.testng.annotations.*;

@Listeners(Screenshot.class)
public class SFDF_LoanCycle extends BaseClass {
    public static String pName = "ডিজিটাল নারায়ণগঞ্জ সিটি";

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://rdcd.erainfotechbd.com:3095/login");
    }

    @Test(description = "This is for login scenario, actor = sfdf_admin", priority = 1)
    public static void Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/login");

        FindElementByID_Details("email","sfdf_admin");
        FindElementByID_Details("password","123");

        FindElementByCssSelector_Click(".MuiButtonBase-root"); //Login_Button

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:3095/samity-management");
    }

    @Test(description = "This is project setup scenario, actor = sfdf_admin", priority =2, enabled = true)
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

    @Test(description = "This is project setup scenario, actor = sfdf_admin", priority =3, enabled = true)
    public static void Project_Allocation() throws InterruptedException {
        SmallWait();
        //FindElementByXpath_Click("//span[text()='প্রশাসনিক সেটআপ']");
        //FindElementByXpath_Click("//span[text()='প্রকল্প/কর্মসূচি']");
        FindElementByXpath_Click("//span[text()='প্রকল্প বরাদ্দ']");

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:3095/adminstration/project-setup/project-assign-toUser");

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='serviceId'])[1]","গোপাল চন্দ্র - ফিল্ড অফিসার"); //ব্যবহারকারী

        SmallWait();
        WebElement combo = driver.findElement(By.xpath("//*[@role='combobox']")); //কার্যালয়
        String value = combo.getAttribute("value");

        if(value.isEmpty()){combo.sendKeys("এসএফডিএফ, উপজেলা অফিস, কোটালীপাড়া গোপালগঞ্জ");}
        else{combo.clear();combo.sendKeys("এসএফডিএফ, উপজেলা অফিস, কোটালীপাড়া গোপালগঞ্জ");}

        combo.sendKeys(Keys.DOWN);
        combo.sendKeys(Keys.ENTER);

        SmallWait();
        SelectBy_Xpath_VisibleText("(.//*[@name='serviceId'])[2]","বিপ্লব কুমার বৈদ্য - উপজেলা ব্যবস্থাপক"); //পর্যবেক্ষক/অনুমোদনকারী

        SmallWait();
        int tr = driver.findElements(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/table/tbody/tr")).size();
        System.out.println("value is:" +tr);

        for(int l = 1; l<= tr; l++){

            String projectName = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();
            System.out.println(projectName);
            System.out.println("Value of l is " +l);

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
    }
}
