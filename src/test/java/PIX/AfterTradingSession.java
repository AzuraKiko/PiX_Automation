package PIX;
import Commons.BaseTest;
import Commons.CommonConstants;
import PiXAdminPageObject.DashboardObject.*;
import PiXAdminPageObject.ITRiskHeartBeatObject.*;
import PiXAdminPageObject.LoginPageObject.PiXLoginPageObject;
import PiXAdminPageObject.MasterDataObject.*;
import PiXAdminPageObject.TradeSurveillanceObject.*;
import PiXAdminPageObject.TradeSurveillanceObject.PiXCancelRatioSettingObject;
import PiXAdminPageObject.TradingDataObject.*;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static Commons.BasePage.sleepInSecond;
import static Commons.GlobalConstants.*;
import static org.testng.Assert.*;

public class AfterTradingSession extends BaseTest{
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
    private PiXITObject itObjectPage;
    private PiXOMSHeartBeatSettingObject omsSettingPage;
    private PiXMDHeartBeatSettingObject mdSettingPage;
    private String account, passWord;

    @BeforeMethod
    public void beforeClass() {
        loginPage = new PiXLoginPageObject(PIX_STG_ENV);
        account = CommonConstants.PIX_ACCOUNT;
        passWord = CommonConstants.PIX_PASSWORD;
        itMonitorPage = loginPage.login(account, passWord);
    }
    @Test
    public void AT_01_PlaceOrder(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Admin Place New Order");
        ExtentTestManager.getTest().log(Status.INFO, "Step 1: Login successfully");
        itObjectPage = itMonitorPage.clickIT();
        ExtentTestManager.getTest().log(Status.INFO, "Step 2: Go to IT/Risk/HeartBeat > IT");
        itObjectPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Step 03: Place a new order");
        itObjectPage.placeOrder("23072307","Sell","AAL","10","1");
        assertEquals(itObjectPage.orderAlert(),"Market closed","Can not place order");
    }
    @Test
    public void AT_02_BlankPendingOrder(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Blank Pending Order");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully");
        orderHistoryPage = itMonitorPage.clickTradingData();
        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Go to Trading Data > Pending Order");
        assertTrue(pendingOrderPage.blankTable());
    }
    @Test
    public void AT_03_ResetAcumulate (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Summary Data");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully and go to IT Monitor");
        itMonitorPage.switchToIFrame();
        sleepInSecond(10);
        assertEquals(itMonitorPage.AccumulatedPendingOrder(),0);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Transaction Overview");
    }
}
