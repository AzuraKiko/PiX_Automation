package PIX;

import Commons.BaseTest;
import Commons.CommonConstants;
import Commons.RandomValue;
import PiXAdminPageObject.DashboardObject.PiXITMonitorObject;
import PiXAdminPageObject.DashboardObject.PiXParametersObject;
import PiXAdminPageObject.DashboardObject.PiXRiskTradeObject;
import PiXAdminPageObject.ITRiskHeartBeatObject.PiXITObject;
import PiXAdminPageObject.LoginPageObject.PiXLoginPageObject;
import PiXAdminPageObject.MasterDataObject.PiXParticipantObject;
import driver.DriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;

import static Commons.BasePage.sleepInSecond;
import static Commons.GlobalConstants.*;
import static org.testng.Assert.*;

import java.lang.reflect.Method;

public class PiXAdmin extends BaseTest {
    private PiXLoginPageObject loginPage;
    private PiXITMonitorObject itMonitorPage;
    private PiXRiskTradeObject riskTradePage;
    private PiXParametersObject parametersPage;
    private PiXITObject itPage;
    private PiXParticipantObject participantPage;
    private String account, passWord;

    @BeforeMethod
    public void beforeClass(){
        loginPage = new PiXLoginPageObject(PIX_STG_ENV);
        account = CommonConstants.PIX_ACCOUNT;
        passWord = CommonConstants.PIX_PASSWORD;
        itMonitorPage = loginPage.login(account, passWord);
    }

    //Dashboard screen
    @Test(alwaysRun = false)
    public void PIX_01_ITMonitor (Method method) throws InterruptedException {
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to IT Monitor");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Matching Core");
        sleepInSecond(5);
        //assertTrue(itMonitorObject.compareOMSLatency(),"OMS latency exceeds 10ms");
        //assertTrue(itMonitorObject.compareMDLatency(),"MD latency exceeds 10ms");

        assertEquals(itMonitorPage.compareMatching(),"100%","MEC error");
    }
    @Test(alwaysRun = false)
    public void PIX_02_RiskTrade (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Risk & Trade Surveillance");
        riskTradePage = itMonitorPage.clickRiskTrade();
    }

    @Test(alwaysRun = false)
    public void PIX_03_Parameters (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        parametersPage = itMonitorPage.clickParameters();
    }
    
    
    
    //Place Order
    @Test
    public void Order_01_Place_Success_Buy (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        itPage = itMonitorPage.clickIT();
        itPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 02: Place a new order");
        itPage.placeOrder("23072307","Buy","AAL","10","14");
        assertEquals(itPage.orderAlert(),"Create order successfully.","Can not place order");
    }
    @Test
    public void Order_02_Place_Success_Sell (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        itPage = itMonitorPage.clickIT();
        itPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 02: Place a new order");
        itPage.placeOrder("23072307","Sell","AAL","10","14");
        assertEquals(itPage.orderAlert(),"Create order successfully.","Can not place order");
    }
    @Test
    public void Order_03_Place_OutOfPriceRange_LimitUp (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        itPage = itMonitorPage.clickIT();
        itPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 02: Place a new order");
        itPage.placeOrder("23072307","Sell","AAL","10","50");
        assertEquals(itPage.orderAlert(),"Out of price range","Can not place order");
    }
    @Test
    public void Order_04_Place_OutOfPriceRange_LimitDown (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        itPage = itMonitorPage.clickIT();
        itPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 02: Place a new order");
        itPage.placeOrder("23072307","Sell","AAL","10","1");
        assertEquals(itPage.orderAlert(),"Out of price range","Can not place order");
    }
    @Test(alwaysRun = true)
    public void Order_05_Place_Exceed_Max_Value (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        itPage = itMonitorPage.clickIT();
        itPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 02: Place a new order");
        itPage.placeOrder("23072307","Sell","AAL","5000000","14");
        assertEquals(itPage.orderAlert(),"Order exceed max order value","Can not place order");
    }
    //@Test(alwaysRun = false)
    public void Order_06_Market_Close (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        itPage = itMonitorPage.clickIT();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 2: Kill Switch");
        itPage.switchToIFrame();
        itPage.killSwitch();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 03: Place a new order");
        itPage.placeOrder("23072307","Sell","AAL","10","1");
        assertEquals(itPage.orderAlert(),"Market closed","Can not place order");
    }

    //IT screen
    @Test
    public void PIX_IT_01_ForwardMessage (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        itPage = itMonitorPage.clickIT();
        itPage.switchToIFrame();
        sleepInSecond(5);
        itPage.forWardMessageFlow("A Hoai", "this is note");

    }



    //@AfterMethod
    public void afterMethod(){
        DriverManager.getDriver().quit();
    }
}
