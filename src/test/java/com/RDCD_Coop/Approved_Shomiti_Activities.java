package com.RDCD_Coop;

import com.BaseClass.BaseClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Approved_Shomiti_Activities extends BaseClass {
    public static String ShomitiName = "Shomobay Shomiti";
    public static String AuthorizePerson = "Mustafizur Rahman";

    @BeforeClass
    public static void LaunchBrowser(){
        FirefoxLaunch();
        OpenWebsite("http://rdcd.erainfotechbd.com:5005/login");
    }

    @Test(description = "This is for login scenario", priority = 1, alwaysRun = true)
    public static void Login() throws InterruptedException {
        LongWait();
        CheckCurrentUrl("http://rdcd.erainfotechbd.com:5005/login");

        Admin_Login();
        //Organizer_Login();

        LongWait();
        CheckNextUrl("http://rdcd.erainfotechbd.com:5005/dashboard");
    }
}
