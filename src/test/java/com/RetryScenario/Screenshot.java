package com.RetryScenario;

import com.BaseClass.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Screenshot extends BaseClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");

        try{
            Screenshot(result.getMethod().getMethodName()); //Taken from BaseClass
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped");

        try{
            Screenshot(result.getMethod().getMethodName()); //Taken from BaseClass
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
