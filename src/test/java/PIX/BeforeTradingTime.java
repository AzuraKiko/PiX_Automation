package PIX;

import Commons.BaseTest;
import Commons.CommonConstants;
import Commons.RandomValue;
import PiXAdminPageObject.DashboardObject.*;
import PiXAdminPageObject.ITRiskHeartBeatObject.*;
import PiXAdminPageObject.LoginPageObject.PiXLoginPageObject;
import PiXAdminPageObject.MasterDataObject.*;
import PiXAdminPageObject.TradeSurveillanceObject.*;
import PiXAdminPageObject.TradeSurveillanceObject.PiXCancelRatioSettingObject;
import PiXAdminPageObject.TradingDataObject.*;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

import static Commons.BasePage.sleepInSecond;
import static Commons.GlobalConstants.*;
import static org.testng.Assert.*;

public class BeforeTradingTime extends BaseTest {
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
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        account = CommonConstants.PIX_ACCOUNT;
        passWord = CommonConstants.PIX_PASSWORD;
        itMonitorPage = loginPage.login(account, passWord);
    }

    @Test
    public void PIX_BF_001_VerifyClosePrice (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Closed Price");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully");
        participantPage = itMonitorPage.clickMasterData();
        tickerPage = participantPage.clickOnTicker();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Go to Master Data > Tickers");
        assertEquals(tickerPage.countElementConatin(),104);
    }
    @Test
    public void PIX_BF_002_VerifySummaryData (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Summary Data");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully and go to IT Monitor");
        itMonitorPage.switchToIFrame();
        sleepInSecond(10);
        assertEquals(itMonitorPage.compareOMSLatency(),0);
        assertEquals(itMonitorPage.compareMDLatency(),0);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Latency");
        assertEquals(itMonitorPage.AccumulatedPendingOrder(),0);
        assertEquals(itMonitorPage.AccumulatedMatchOrder(),0);
        assertEquals(itMonitorPage.AccumulatedCancelOrder(),0);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Transaction Overview");
    }
    @Test
    public void PIX_BF_003_VerifyBlankPendingOrder (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Blank Pending Order");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully");
        orderHistoryPage = itMonitorPage.clickTradingData();
        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Go to Trading Data > Pending Order");
        assertTrue(pendingOrderPage.blankTable());

    }
    @Test
    public void PIX_BF_004_VerifyDefaultHeartBeatSetting (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Default Setting");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully");
        parametersPage = itMonitorPage.clickParameters();
        parametersPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Go to Parameters");
        sleepInSecond(5);
        String OMSLatencyWarning1 = parametersPage.getOMSLatencyWarning();
        String OMSLatencyAlarm1 = parametersPage.getOMSLatencyAlarm();
        String OMSTimeInterval1 = parametersPage.getOMSTimeInterval();
        String MDLatencyWarning1 = parametersPage.getMDLatencyWarning();
        String MDTimeInterval1 = parametersPage.getMDTimeInternal();
        ExtentTestManager.getTest().log(Status.INFO, "Verify All Parameters in Parameters screen");
        parametersPage.switchToDefault();
        itObjectPage = itMonitorPage.clickIT();
        mdSettingPage = itObjectPage.clickMDHeartBeatSettingTag();
        mdSettingPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Step 03: Go to MD Latency Settings");
        sleepInSecond(3);
        String MDLatencyWarning2 = mdSettingPage.getMDLatencyWarning();
        String MDTimeInterval2 = mdSettingPage.getMDTimeInternal();
        assertEquals(MDLatencyWarning1, MDLatencyWarning2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify MD Latency Warning");
        assertEquals(MDTimeInterval1, MDTimeInterval2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify MD Time Interval");

        mdSettingPage.switchToDefault();
        omsSettingPage = itObjectPage.clickOMSHeartBeatSettingTag();
        ExtentTestManager.getTest().log(Status.INFO, "Step 04: Go to OMS Latency Settings");
        omsSettingPage.switchToIFrame();
        sleepInSecond(3);
        String OMSLatencyWarning2 = omsSettingPage.getOMSLatencyWarning();
        String OMSLatencyAlarm2 = omsSettingPage.getOMSLatencyAlarm();
        String OMSTimeInterval2 = omsSettingPage.getOMSTimeInterval();
        assertEquals(OMSLatencyWarning1, OMSLatencyWarning2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify OMS Latency Warning");
        assertEquals(OMSLatencyAlarm1, OMSLatencyAlarm2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify OMS Latency Alarm");
        assertEquals(OMSTimeInterval1, OMSTimeInterval2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify OMS time interval");

    }
    @Test
    public void PIX_BF_005_VerifyDefaultCancelRatioSetting (Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify Default Setting");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully");
        parametersPage = itMonitorPage.clickParameters();
        parametersPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Go to Parameters");
        sleepInSecond(5);
        String selfMatchTimeInterval1 = parametersPage.getSelfMatchTimeInterval();
        String selfMatchRatio1 = parametersPage.getSelfMatchRatio();
        ExtentTestManager.getTest().log(Status.INFO, "Verify All Parameters in Parameters screen");
        parametersPage.switchToDefault();
        cancelRatioPage = itMonitorPage.clickTradeSurveillance();
        selfMatchSettingPage = cancelRatioSettingPage.clickSelfMatchSettingTag();
        selfMatchSettingPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Step 03: Go to Self Match Setting");
        sleepInSecond(3);
        String selfMatchTimeInterval2 = selfMatchSettingPage.getSelfMatchTimeInterval();
        String selfMatchRatio2 = selfMatchSettingPage.getSelfMatchRatio();
        assertEquals(selfMatchTimeInterval1, selfMatchTimeInterval2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Self Match time interval");
        assertEquals(selfMatchRatio1, selfMatchRatio2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Self Match ratio");
    }

    @Test
    public void PIX_BF_006_VerifyDefaultCancelRatioSetting (Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify Default Setting");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully");
        parametersPage = itMonitorPage.clickParameters();
        parametersPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Go to Parameters");
        sleepInSecond(5);
        String cancelRatioTimeInterval1 = parametersPage.getCancelRatioTimeInterval();
        String sellCancelRatio1 = parametersPage.getSellCancelRatio();
        String sellVolumeCancelRatio1 = parametersPage.getSellVolumeCancelRatio();
        String buyCancelRatio1 = parametersPage.getBuyCancelRatio();
        String buyVolumeCancelRatio1 = parametersPage.getBuyVolumeCancelRatio();

        ExtentTestManager.getTest().log(Status.INFO, "Verify All Parameters in Parameters screen");
        parametersPage.switchToDefault();
        cancelRatioPage = itMonitorPage.clickTradeSurveillance();
        cancelRatioSettingPage = cancelRatioPage.clickCancelRatioSetting();
        cancelRatioSettingPage.switchToIFrame();
        ExtentTestManager.getTest().log(Status.INFO, "Step 03: Go to Cancel Ratio Setting");
        sleepInSecond(3);
        String cancelRatioTimeInterval2 = cancelRatioSettingPage.getCancelRatioTimeInterval();
        String sellCancelRatio2 = cancelRatioSettingPage.getSellCancelRatio();
        String sellVolumeCancelRatio2 = cancelRatioSettingPage.getSellVolumeCancelRatio();
        String buyCancelRatio2 = cancelRatioSettingPage.getBuyCancelRatio();
        String buyVolumeCancelRatio2 = cancelRatioSettingPage.getBuyVolumeCancelRatio();
        assertEquals(cancelRatioTimeInterval1, cancelRatioTimeInterval2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Cancel ratio time interval");
        assertEquals(sellCancelRatio1, sellCancelRatio2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Sell cancel ratio");
        assertEquals(sellVolumeCancelRatio1, sellVolumeCancelRatio2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Sell volume cancel ratio");
        assertEquals(buyCancelRatio1, buyCancelRatio2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Buy cancel ratio");
        assertEquals(buyVolumeCancelRatio1, buyVolumeCancelRatio2);
        ExtentTestManager.getTest().log(Status.INFO, "Verify Buy volume cancel ratio");
    }
    @Test
    public void PIX_MasterData_CreateNew_Fix_Success (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Dashboard");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully and go to Master Data");
        participantPage = itMonitorPage.clickMasterData();
        sleepInSecond(5);
        participantPage.clickOnCreate();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Create new participant");
        String userName = RandomValue.genRandomUser();
        String gmail = RandomValue.genRandomGmail();
        String id = RandomValue.genRandomID();
        String phone = RandomValue.NUMBERS;
        participantPage.insertParticipantInf(userName,gmail,phone,id);
        ExtentTestManager.getTest().log(Status.INFO, "Step 03: Fill participant information");
        participantPage.assignFixAccount();
        ExtentTestManager.getTest().log(Status.INFO, "Step 04: Assign to FIX account");
        participantPage.clickOnSave();
        ExtentTestManager.getTest().log(Status.INFO, "Step 05: Click on Save button");
        sleepInSecond(5);
        participantPage.backToParticipants();
        participantPage.searchParticipants(id);
        assertEquals(participantPage.getAccountHolder(id), userName);
        assertEquals(participantPage.getEmail(id), gmail);
        assertEquals(participantPage.getPhoneNumber(id), phone);
        assertEquals(participantPage.getStatus(id), "Active");
        assertEquals(participantPage.getSettlementMethod(id), "Auto");
    }
    @Test
    public void PIX_BF_007_VerifyPlaceNewOrder (Method method){
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
    public void PIX_BF_008_VerifyPlaceNewOrder (Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Closed Price");
        ExtentTestManager.getTest().log(Status.INFO, "Step 01: Login successfully");
        participantPage = itMonitorPage.clickMasterData();
        tickerPage = participantPage.clickOnTicker();
        ExtentTestManager.getTest().log(Status.INFO, "Step 02: Go to Master Data > Tickers");
        assertTrue(tickerPage.getListRateCompare());
        sleepInSecond(5);
        assertTrue(tickerPage.getListLotSizeCompare());
        assertTrue(tickerPage.getListTickSizeCompare());
        assertTrue(tickerPage.getListMinLotCompare());
        ExtentTestManager.getTest().log(Status.INFO, "Step 03: Verify Rate, Lot Size, Tick Size, Min Lot");
    }


    @AfterMethod
    public void afterMethod(){
        DriverManager.getDriver().quit();
    }

}
