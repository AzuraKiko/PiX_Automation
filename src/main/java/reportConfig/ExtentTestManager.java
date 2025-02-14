package reportConfig;

//import java.util.HashMap;
//import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

//public class ExtentTestManager {
//    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
//    static ExtentReports extent = ExtentManager.createExtentReports();
//
//    public static synchronized ExtentTest getTest() {
//        return extentTestMap.get((int) Thread.currentThread().getId());
//    }
//
//    public static synchronized ExtentTest startTest(String testName, String desc) {
//        ExtentTest test = extent.createTest(testName, desc);
//        extentTestMap.put((int) Thread.currentThread().getId(), test);
//        return test;
//    }
//}

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ExtentTestManager {
    static ConcurrentMap<Integer, ExtentTest> extentTestMap = new ConcurrentHashMap<>();
    static ExtentReports extent = ExtentManager.createExtentReports();

    public static ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
}
