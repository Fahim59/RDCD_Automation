package com.RDCD_Coop.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class DataProviderClass{

    public static Object[][] testData(String path, String sheetName) throws IOException {
        Data value = new Data(path, sheetName);

        int rowCount = value.getRowCount();
        int colCount = value.getColumnCount();

        Object data[][] = new Object[rowCount - 1][colCount];

        for(int i = 1; i < rowCount; i++){
            for(int j = 0; j < colCount; j++){
                String cellData = value.getCellData(i,j);

                data[i - 1][j] = cellData;
            }
        }
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Name_Clearance")
    public Object[][] nameClearance() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Name_Clearance");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Initial_Info")
    public Object[][] initialInfo() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Initial_Info");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Sodossho_Nibondhon")
    public Object[][] pMemberAdd() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"SixMembers");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Sodossho_Nibondhon_Central")
    public Object[][] cMemberAdd() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Central_Members");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Sodossho_Nibondhon_National")
    public Object[][] nMemberAdd() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"National_Members");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Committee_Bebosthapona")
    public Object[][] pCommittee() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Primary_Committee");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Committee_Bebosthapona_Central")
    public Object[][] cCommittee() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Central_Committee");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Committee_Bebosthapona_National")
    public Object[][] nCommittee() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"National_Committee");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Arthik_Totthadi")
    public Object[][] p_financial_information() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"P_Fin_Info");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Samity_Manual")
    public Object[][] manual_Samity() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"P_Manual_Samity"); //P_Manual_Samity
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Update_Member_Info")
    public Object[][] update_member() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Update_Member");
        return data;
    }
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "Update_Authorize_Member_Info")
    public Object[][] update_authorize_person() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Update_AP");
        return data;
    }
    //========================================================================================//
    //========================================================================================//
    //========================================================================================//
    @org.testng.annotations.DataProvider(name = "testdata")
    public Object[][] test() throws IOException {
        String location = "D:\\Intellij Files\\RDCD_Automation\\DataProvider.xlsx";

        Object[][] data = testData(location,"Name_Clearance");
        return data;
    }

    @Test(dataProvider = "testdata")
    public static void Members(String nidorbrn, String nidorbrnValue, String dob, String name, String nameBangla, String fatherName, String motherName, String mobile, String gender, String email, String eduLevel, String jobType, String religion, String maritalStatus, String district, String upazila, String thana, String address){
        System.out.println(nidorbrn);
        System.out.println(nidorbrnValue);
        System.out.println(dob);
        System.out.println(name);
        System.out.println(nameBangla);
        System.out.println(fatherName);
        System.out.println(motherName);
        System.out.println(mobile);
        System.out.println(gender);
        System.out.println(email);
        System.out.println(eduLevel);
        System.out.println(jobType);
        System.out.println(religion);
        System.out.println(maritalStatus);
        System.out.println(district);
        System.out.println(upazila);
        System.out.println(thana);
        System.out.println(address);
    }

    @Test(dataProvider = "testdata")
    public static void AP(String dob, String name, String fatherName, String motherName, String gender, String email, String eduLevel, String jobType, String religion, String maritalStatus, String district, String upazila, String thana, String address){
        System.out.println(dob);
        System.out.println(name);
        System.out.println(fatherName);
        System.out.println(motherName);
        System.out.println(gender);
        System.out.println(email);
        System.out.println(eduLevel);
        System.out.println(jobType);
        System.out.println(religion);
        System.out.println(maritalStatus);
        System.out.println(district);
        System.out.println(upazila);
        System.out.println(thana);
        System.out.println(address);
    }

    @Test(dataProvider = "testdata")
    public static void Else(String a,String b, String c, String d, String e){ //, String d, String e, String f
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        //System.out.println(f);
    }
}