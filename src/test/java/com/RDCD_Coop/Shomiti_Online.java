package com.RDCD_Coop;

import com.BaseClass.*;
import com.RetryScenario.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

@Listeners(Screenshot.class)
public class Shomiti_Online extends BaseClass {
    public static String ShomitiName = "Shomobay Shomiti";

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

        Admin_Login();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        //CheckNextUrl("http://10.11.200.30:5001/dashboard");
    }

    @Test(description = "This is for shomiti online scenario", priority = 2, enabled = true)
    public static void Shomiti_Online() throws InterruptedException {
        Menu_ShomitiOnline();

        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/samity-management/manual-samity");
        //CheckCurrentUrl("http://10.11.200.30:5001/samity-management/manual-samity");

        

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
        //CheckNextUrl("http://10.11.200.30:5001/dashboard");
    }

    @AfterSuite
    public static void Close(){
        //FirefoxQuit();
        //SendEmail();
    }
}
