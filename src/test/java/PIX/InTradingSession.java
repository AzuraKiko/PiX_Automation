package PIX;
import Commons.BaseTest;
import Commons.BasePage;
import Commons.CommonConstants;
import Commons.OrderDateInfor;
import Commons.RandomValue;
import PiXAdminPageObject.DashboardObject.*;
import PiXAdminPageObject.ITRiskHeartBeatObject.*;
import PiXAdminPageObject.LoginPageObject.PiXLoginPageObject;
import PiXAdminPageObject.MasterDataObject.*;
import PiXAdminPageObject.TradeSurveillanceObject.*;
import PiXAdminPageObject.TradeSurveillanceObject.PiXCancelRatioSettingObject;
import PiXAdminPageObject.TradingDataObject.*;
import TraderUITool.ToolObject;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static Commons.BasePage.sleepInSecond;
import static Commons.GlobalConstants.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InTradingSession extends BaseTest{
    private ToolObject traderUI;
    private PiXLoginPageObject loginPage;
    private PiXITMonitorObject itMonitorPage;
    private PiXRiskTradeObject riskTradePage;
    private PiXParametersObject parametersPage;
    private PiXParticipantObject participantPage;
    private PiXTickerObject tickerPage;
    private PiXOrderHistoryObject orderHistoryPage;
    private PiXPendingOrderObject pendingOrderPage;
    private PiXCancelRatioSettingObject cancelRatioSettingPage;
    private PiXSelfMatchSettingObject selfMatchSettingPage;
    private PiXCancelRatioObject cancelRatioPage;
    private PiXITObject itPage;
    private PiXOMSHeartBeatSettingObject omsSettingPage;
    private PiXMDHeartBeatSettingObject mdSettingPage;
    private String account, passWord;

    @BeforeMethod
    public void beforeClass() {
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        account = CommonConstants.PIX_ACCOUNT;
        passWord = CommonConstants.PIX_PASSWORD;
        itMonitorPage = loginPage.login(account, passWord);
    }
    @Test
    public void Order_01_Place_Success_Buy (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 01: Login successfully and go to Parameters");
        itPage = itMonitorPage.clickIT();
        itPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 02: Place a new order");
        String accountID = "23072307";
        String symbol = "AAL";
        String side = "Buy";
        String qty = "9";
        String price = "10";
        itPage.placeOrder(accountID,side,symbol,qty,price);
        assertEquals(itPage.orderAlert(),"Create order successfully.","Can not place order");
//        itPage.switchToDefault();
//        orderHistoryPage = itPage.clickTradingData();
//        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
//        sleepInSecond(3);
//        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
//        pendingOrderPage.searchOrder(accountID, symbol, side);
//        pendingOrderPage.findNearestTimestamp(OrderDateInfor.ORDER_TIME_STAMP, pendingOrderPage.extractExecTimes());
//        PiXPendingOrderObject.timestampToDateTime(pendingOrderPage.findNearestTimestamp(OrderDateInfor.ORDER_TIME_STAMP, pendingOrderPage.extractExecTimes()));
//        pendingOrderPage.reloadPage();
//        String orderNo = pendingOrderPage.getOrderNo();
//        assertEquals(pendingOrderPage.getAccountID(orderNo), accountID);
//        assertEquals(pendingOrderPage.getSide(orderNo), side);
//        assertEquals(pendingOrderPage.getTickerCode(orderNo), symbol);
//        assertEquals(pendingOrderPage.getQuantity(orderNo), qty);
//        assertEquals(pendingOrderPage.getPrice(orderNo), price);
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
    @Test
    public void Order_07_Market_Close (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        itPage = itMonitorPage.clickIT();
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 2: Kill Switch");
        itPage.switchToIFrame();
        itPage.killSwitch();
        sleepInSecond(4);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Dashboard - Step 03: Place a new order");
        itPage.placeOrder("23072307","Sell","AAL","10","1");
        assertEquals(itPage.orderAlert(),"Market closed","Can not place order");
        sleepInSecond(5);
        itPage.closeAddOrderPopup();
        sleepInSecond(3);
        itPage.offKillSwitch();
    }
    @Test
    public void Order_06_Cancel_All_Order (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Login admin account successfully");
        orderHistoryPage = itMonitorPage.clickTradingData();
        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        sleepInSecond(3);
        pendingOrderPage.cancelAllOrder();
        sleepInSecond(10);
        pendingOrderPage.reloadPage();
        sleepInSecond(2);
        assertTrue(pendingOrderPage.blankTable());
    }
    @AfterMethod
    public void afterMethod(){
        DriverManager.getDriver().quit();
    }




}
