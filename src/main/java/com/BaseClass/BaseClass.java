package com.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;

    //ActionAid, Kingshuk Bahumukhi Shamabay Samity, Peoples Credit Co-Op-Society Ltd, চিরন্তন - Chironton
    //CHANGE foundation, Nielsen Bangladesh Ltd. ,Helping Hand

    //ফুলতলা বিত্তহীন সমবায় সমিতি, রূপসা বিত্তহীন সমবায় সমিতি, ডুমুরিয়া বিত্তহীন সমবায় সমিতি, পাইকগাছা বিত্তহীন সমবায় সমিতি
    //দিঘলিয়া বিত্তহীন সমবায় সমিতি, বটিয়াঘাটা বিত্তহীন সমবায় সমিতি

    //বাংলাদেশ সমবায় ব্যাংক লিঃ, বাংলাদেশ জাতীয় বিত্তহীন সমবায় সমিতি লিঃ, বাংলাদেশ জাতীয় সমবায় শিল্প সমিতি লিঃ, বাংলাদেশ সমবায় মার্কেটিং সোসাইটি লিঃ
    //বাংলাদেশ জাতীয় মহিলা সমবায় সমিতি লিঃ, বাংলাদেশ জাতীয় পল্লী উন্নয়ন সমবায় ফেডারেশন লিঃ, বাংলাদেশ জাতীয় সমবায় ইউনিয়ন, বাংলাদেশ মুক্তিযোদ্ধা সমবায় ফেডারেশন লিঃ

    public static String sname = "CHANGE foundation";

    public static String link = "stage-coop.rdcd.gov.bd";
    //public static String link = "10.11.200.30:5001";

    public static void main(String[] args) throws NotFoundException {}

    public static void FirefoxLaunch(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
    }
    public static void FirefoxQuit(){driver.quit();}
    //--------------------------------------------------------------------------------------------------------//
    public static void SmallWait() throws InterruptedException {Thread.sleep(2000);}
    public static void LongWait() throws InterruptedException {Thread.sleep(4000);}
    public static void LargeWait() throws InterruptedException {Thread.sleep(8000);}
    //--------------------------------------------------------------------------------------------------------//
    public static void OpenWebsite(String Url){driver.get(Url);}
    //--------------------------------------------------------------------------------------------------------//
    public static void Screenshot(String methodName) throws IOException {

        File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(image,new File("./Screenshot/" + methodName + "-" + ".jpg"));
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    @Test
    public static void CheckCurrentUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        if(!currentUrl.equals(url)){
            System.out.println("Current Url Mismatched, Test Failed");
            //System.exit(0);
            Assert.fail();
        }
        else{
            System.out.println("Current Url Matched, Test Passed");
        }
    }
    @Test
    public static void CheckNextUrl(String url){
        String nextUrl = driver.getCurrentUrl();
        System.out.println(nextUrl);
        if(!nextUrl.equals(url)){
            System.out.println("Next Url Mismatched, Test Failed");
            //System.exit(0);
            Assert.fail();
        }
        else{
            System.out.println("Next Url Matched, Test Passed");
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void Menu_AssociationManagement(String xpath) throws InterruptedException {
        SmallWait();

        FindElementByXpath_Click("//span[text()='সমিতি ব্যবস্থাপনা']");
        FindElementByXpath_Click(xpath);
    }
    public static void Menu_StaffManagement(String xpath) throws InterruptedException {
        SmallWait();

        FindElementByXpath_Click("//span[text()='কর্মকর্তা/কর্মচারী ব্যবস্থপনা']");
        FindElementByXpath_Click(xpath);
    }
    public static void Menu_Approve(String xpath) throws InterruptedException {
        SmallWait();

        FindElementByXpath_Click(xpath);
    }
    public static void Project_Setup() throws InterruptedException {
        SmallWait();

        FindElementByXpath_Click("//span[text()='প্রশাসনিক সেটআপ']");
        FindElementByXpath_Click("//span[text()='প্রকল্প/কর্মসূচি']");
        FindElementByXpath_Click("//span[text()='প্রকল্প/কর্মসূচি তৈরি']");
    }
    public static void IncompleteApplication(String sname) throws InterruptedException {
        Menu_AssociationManagement("//span[text()='সমিতি নিবন্ধনের আবেদন']");

        SmallWait();
        SelectBy_Name_Radiobox("samityLevel","2"); //Abedon Type (Incomplete)

        SmallWait();
        SelectBy_Name_VisibleText("projectId", sname);
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByID_Details(String id, String details){
        WebElement element = driver.findElement(By.id(id));
        String text = element.getAttribute("value");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByName_Click(String name){driver.findElement(By.name(name)).click();}
    public static void FindElementByName_Details(String name, String details){
        WebElement element = driver.findElement(By.name(name));
        String text = element.getAttribute("value");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByXpath_Click(String xpath){driver.findElement(By.xpath(xpath)).click();}
    public static void FindElementByXpath_Details(String xpath, String details){
        WebElement element = driver.findElement(By.xpath(xpath));
        String text = element.getAttribute("value");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }
    public static void FindElementByXpath(String xpath){
        driver.findElement(By.xpath(xpath));
    }
    public static void FindElementByXpath_Date(String xpath, String details){

        driver.findElement(By.xpath(xpath));
        WebElement date = driver.findElement(By.xpath(xpath));
        date.clear();
        date.sendKeys(details);
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void FindElementByCssSelector_Click(String cssSelector){driver.findElement(By.cssSelector(cssSelector)).click();}
    public static void FindElementByCssSelector_Details(String cssSelector, String details){
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        String text = element.getAttribute("value");

        if(text.isEmpty()){element.sendKeys(details);}
        else{element.clear();element.sendKeys(details);}
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void SelectBy_Name_VisibleText(String name, String text){
        WebElement element = driver.findElement(By.name(name));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public static void SelectBy_Name_Checkbox(String name){
        WebElement checkbox = driver.findElement(By.name(name));
        if(!checkbox.isSelected()){
            checkbox.click();
        }
    }
    public static void SelectBy_Name_Radiobox(String name, String value){
        List<WebElement> user = driver.findElements(By.name(name));
        for(WebElement option : user){

            if(option.getAttribute("value").equalsIgnoreCase(value)){
                if(!option.isSelected()){
                    option.click();
                }
            }
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void SelectBy_Xpath_VisibleText(String xpath, String text){
        WebElement element = driver.findElement(By.xpath(xpath));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public static void SelectBy_Xpath_VisibleText_Diff(String xpath, String text) throws InterruptedException {
        SmallWait();
        WebElement combo = driver.findElement(By.xpath(xpath));
        String value = combo.getAttribute("value");

        if(value.isEmpty()){combo.sendKeys(text);}
        else{combo.clear();combo.sendKeys(text);}

        //combo.sendKeys(Keys.DOWN);
        //combo.sendKeys(Keys.ENTER);
        combo.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }
    public static void SelectBy_Xpath_Checkbox(String xpath){
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        if(!checkbox.isSelected()){
            checkbox.click();
        }
    }
    //----------------------------Loan-----------------------------------------------------------------------//
    public static void Login_Admin() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("email","sfdf_admin");
        FindElementByID_Details("password","123");
        FindElementByCssSelector_Click(".MuiButtonBase-root");
    }
    public static void Login_Fo() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("email","sfdf_fo");
        FindElementByID_Details("password","123");
        FindElementByCssSelector_Click(".MuiButtonBase-root");
    }
    public static void Login_Um() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("email","sfdf_um");
        FindElementByID_Details("password","123");
        FindElementByCssSelector_Click(".MuiButtonBase-root");
    }
    //----------------------------Coop------------------------------//
    public static void Khulna_Admin_Login() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("username","200000071880");
        FindElementByID_Details("password","1234");
        SelectBy_Name_Radiobox("isAdmin","2");
        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");
    }
    public static void Jessore_Admin_Login() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("username","200000071781");
        FindElementByID_Details("password","1234");
        SelectBy_Name_Radiobox("isAdmin","2");
        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");
    }
    public static void Mymensingh_Admin_Login() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("username","200000069328");
        FindElementByID_Details("password","1234");
        SelectBy_Name_Radiobox("isAdmin","2");
        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");
    }
    public static void JR_Login() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("username","300000016684");
        FindElementByID_Details("password","1234");
        SelectBy_Name_Radiobox("isAdmin","2");
        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");

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
        SmallWait();
        Approve_Text("Samity approved named " + "'"+sname+"'");
    }
    public static void R_Login() throws InterruptedException {
        SmallWait();
        FindElementByID_Details("username","100000006028");
        FindElementByID_Details("password","1234");
        SelectBy_Name_Radiobox("isAdmin","2");
        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");

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
        SmallWait();
        Approve_Text("Samity approved named " + "'"+sname+"'");
    }
    public static void Organizer_Login() throws InterruptedException {
        SmallWait();

        FindElementByID_Details("username","fahim");
        FindElementByID_Details("password","12345");
        SelectBy_Name_Radiobox("isAdmin", "1");

        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");
    }
    public static void SSO_Organizer_Login() throws InterruptedException {
        SmallWait();

        driver.navigate().to("https://idp-v2.live.mygov.bd/login");

        FindElementByID_Details("username","fahim");
        FindElementByID_Details("password","12345");
        SelectBy_Name_Radiobox("isAdmin", "1");

        FindElementByCssSelector_Click("button.MuiButton-root:nth-child(4)");
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void Logout() throws InterruptedException {
        LargeWait();
        FindElementByXpath_Click("/html/body/div[1]/div[2]/header/div/div/div[2]/div/div[5]/button/div/span[1]");

        SmallWait();
        FindElementByXpath_Click("/html/body/div[3]/div[3]/ul/li[3]"); //Logout
    }
    public static void Logout_Coop() throws InterruptedException {
        LargeWait();
        FindElementByXpath_Click("(.//*[@class='MuiChip-label MuiChip-labelMedium css-9iedg7'])[1]");

        SmallWait();
        FindElementByXpath_Click("(.//*[@data-testid='LogoutOutlinedIcon'])[2]");

        driver.navigate().to("http://stage-coop.rdcd.gov.bd/login");
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void SSO_Admin_Login() throws InterruptedException {
        LongWait();

        FindElementByID_Details("field-username-47710426ea714568b41b89e5d45324cc52f33a30","admin@admin.com");
        FindElementByID_Details("field-password-b1af080f02df41ceea006c250bcbad69558155ae","admin123");

        FindElementByXpath_Click("//button[@id='button-login']");
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void UploadPicture(String cssSelector, String path) throws InterruptedException, IOException {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.cssSelector(cssSelector))).click().build().perform();
        LongWait();
        Runtime.getRuntime().exec(path);
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void Scroll_Down_FindElement(String name) throws InterruptedException {
        SmallWait();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.name(name));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static void Scroll_Down_Xpath_FindElement(String xpath) throws InterruptedException {
        SmallWait();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(xpath));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
    public static void Scroll_Down() throws InterruptedException {
        SmallWait();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }
    public static void Long_Scroll_Down() throws InterruptedException {
        SmallWait();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
    }
    public static void Scroll_Up() throws InterruptedException {
        SmallWait();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-250)", "");
    }
    public static void LongScroll_Up() throws InterruptedException {
        SmallWait();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-700)", "");
    }
    //--------------------------------------------------------------------------------------------------------//
    public  static void SendEmail(){
        String decode_pass = "aWl1bXJmdHRmd3VldGdjdQ==";
        String password = new String (Base64.getDecoder().decode(decode_pass.getBytes()));

        final String from = "testmustafizur@gmail.com"; //For Yahoo, it should be a yahoo mail

        //final String p1 = "tauhid@erainfotechbd.com";
        final String p2 = "sbappy88@gmail.com";
        //final String p3 = "parul@erainfotechbd.com";
        final String p4 = "mrahaman59@yahoo.com";

        String host = "smtp.gmail.com"; //smtp.mail.yahoo.com
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("testmustafizur", password);
            }
        });

        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            Multipart multipartObject = new MimeMultipart();

            message.setFrom(new InternetAddress(from));

            //message.addRecipient(Message.RecipientType.BCC, new InternetAddress(p1));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(p2));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(p3));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(p4));

            message.setSubject("Test Execution Result Report"); //Mail Subject

            BodyPart emailBody = new MimeBodyPart();
            emailBody.setText("Dear Sir/Ma'am, " + "\n" + "Here is test result execution report." + "\n" + "\n" + "Test Executed By-" + "\n" + "Mustafizur Rahman");

            BodyPart attachment = new MimeBodyPart();
            String filename = "D:\\Intellij Files\\RDCD_Automation\\test-output\\emailable-report.html";
            DataSource source = new FileDataSource(filename);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(filename);

            multipartObject.addBodyPart(emailBody); //Mail Body
            multipartObject.addBodyPart(attachment); // Attachment

            message.setContent(multipartObject);

            System.out.println("Sending............");
            Transport.send(message);
            System.out.println("Email Sent Successfully....");
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("Email Sent Failed....");
        }
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void Application_for_formation_of_election_committee(String memberName, String designation) throws InterruptedException {
        SelectBy_Name_VisibleText("memberName", memberName);
        SelectBy_Name_VisibleText("committeeDesignation", designation);
        FindElementByXpath_Click("//button[text()='সদস্য সংরক্ষন করুন']");
    }
    public static void Approval_for_formation_of_election_committee(String shomiti, String option) throws InterruptedException {
        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String service = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[1]")).getText();
            String shomitiName = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[2]/div/div/div[2]/table/tbody/tr["+l+"]/td[2]")).getText();

            if(service.equalsIgnoreCase(option)){
                if(shomitiName.equalsIgnoreCase(shomiti)){
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
    }
    public static void Approve_Text(String text) throws InterruptedException {
        SmallWait();
        driver.findElement(By.cssSelector("div.tox-icon > svg:nth-child(1) > path:nth-child(1)")).click();
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("#tinymce")).sendKeys(text);
        driver.switchTo().defaultContent();
    }
    //--------------------------------------------------------------------------------------------------------//
    public static void Website_Setup_Table(String contentName) throws InterruptedException {
        LongWait();
        int tr = driver.findElements(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr")).size();
        for(int l = 1; l<= tr; l++){

            String element = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/div/div/div/div/div/div[3]/div/div[2]/table/tbody/tr["+l+"]/th[3]")).getText();

            if(element.equalsIgnoreCase(contentName)){
                System.out.println(element + " added Successfully...");
            }
            else{
                System.out.println(element +" addition Failed...");
            }
        }
    }

    public static void JOptionpane(){
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int num = Integer.parseInt(JOptionPane.showInputDialog(frame,"Choose Any Operation- " + "\n" + "1. Application for formation of election committee" + "\n" + "2. Application for addition of selected committee"+ "\n" + "3. Application for formation of Interim Committee" + "\n\n" + "Enter Your Choice: (1/2/3)"));

        switch (num) {
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            default:
                JOptionPane.showMessageDialog(frame,"You have entered wrong input");
        }
    }
}
