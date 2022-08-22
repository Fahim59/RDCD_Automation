package com.RDCD_Coop;

public class DataProviderClass{
    @org.testng.annotations.DataProvider(name = "Member_Add")
    public Object[][] getdata(){

        Object[][] data = {{"1","4655155904","03011999","Asad Haq","আসাদ হক","Mr. Abc","Mrs. Abc","01968956730","1","asad@erainfotechbd.com","স্নাতকোত্তর","শিক্ষক","বিবাহিত","খুলনা","দাকোপ","দাকোপ","বাড়ি নং-৩২, রাস্তা-০৯"}};
        return data;
    }
}
