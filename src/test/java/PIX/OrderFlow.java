package PIX;

import Commons.BaseTest;
import Commons.CommonConstants;
import Commons.RandomValue;
import PiXAdminPageObject.DashboardObject.PiXITMonitorObject;
import PiXAdminPageObject.LoginPageObject.PiXLoginPageObject;
import PiXAdminPageObject.MasterDataObject.*;
import PiXAdminPageObject.TradingDataObject.PiXOrderHistoryObject;
import PiXAdminPageObject.TradingDataObject.PiXPendingOrderObject;
import TraderUITool.ToolObject;
import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reportConfig.ExtentTestManager;

import static Commons.BasePage.sleepInSecond;
import static Commons.GlobalConstants.*;
import static org.testng.Assert.*;

import java.lang.reflect.Method;

public class OrderFlow extends BaseTest {
    private ToolObject traderUI;
    private PiXLoginPageObject loginPage;
    private PiXITMonitorObject itMonitorPage;
    private PiXOrderHistoryObject orderHistoryPage;
    private PiXPendingOrderObject pendingOrderPage;
    private PiXParticipantObject participantPage;
    private PiXTickerObject tickerPage;
    private String account = CommonConstants.PIX_ACCOUNT;
    private String passWord = CommonConstants.PIX_PASSWORD;

    @BeforeMethod
    public void beforeClass() {
        traderUI = new ToolObject(TRADER_UI);
    }
    @Test
    public void OF_01_PlaceSellOrder(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "C";
        String side = "2";
        int qty = 10;
        String price = "65.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Go to admin account");

        itMonitorPage = loginPage.login(account, passWord);
        orderHistoryPage = itMonitorPage.clickTradingData();
        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Trading Data > Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID),price);
        assertEquals(pendingOrderPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Check Order information");
    }

    @Test
    public void OF_02_Sell_ModifyQtyUp(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "C";
        String side = "2";
        int qty = 10;
        String price = "60.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        int newQty = 35;
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,newQty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Up");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step5: Check Order information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),price);
        assertEquals(pendingOrderPage.getQuantity(clOrderID),qty);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),newQty-qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_03_Sell_ModifyQtyDown(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify OrderFlow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "C";
        String side = "2";
        int qty = 10;
        String price = "60.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        int newQty = 5;
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,newQty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Up");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "5: Check Order information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),price);
        //assertEquals(pendingOrder.getQuantity(clOrderID),qty);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),qty-newQty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_04_Sell_ModifyPriceUp(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "C";
        String side = "2";
        int qty = 10;
        String price = "60.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        String newPrice = "61.00";
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,qty,newPrice);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Up");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step5: Check Order Information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),newPrice);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_05_Sell_ModifyPriceDown(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "C";
        String side = "2";
        int qty = 10;
        String price = "60.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        String newPrice = "59.00";
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,qty,newPrice);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Down");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step5: Check Order information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),newPrice);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_06_Sell_CancelOrder(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "C";
        String side = "2";
        int qty = 10;
        String price = "60.00";
        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        sleepInSecond(3);
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        traderUI.cancelOrderByFIXMessage(clOrderID2, clOrderID, accountID, symbol);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Cancel Order");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID2),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID2),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID2),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID2),"Withdrawn");
        assertEquals(orderHistoryPage.getPrice(clOrderID2),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID2),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Order information");
    }
    @Test
    public void OF_07_PlaceBuyOrder(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "CCL";
        String side = "1";
        int qty = 10;
        String price = "11.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Go to admin account");
        itMonitorPage = loginPage.login(account, passWord);
        orderHistoryPage = itMonitorPage.clickTradingData();
        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Trading Data > Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID),price);
        assertEquals(pendingOrderPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Check Order information");
    }
    @Test
    public void OF_08_Buy_ModifyQtyUp(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "CCL";
        String side = "1";
        int qty = 10;
        String price = "10.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        int newQty = 35;
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,newQty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Up");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step5: Check Order information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),price);
        assertEquals(pendingOrderPage.getQuantity(clOrderID),qty);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),newQty-qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_09_Buy_ModifyQtyDown(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify OrderFlow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "CCL";
        String side = "1";
        int qty = 10;
        String price = "11.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        int newQty = 5;
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,newQty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Up");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "5: Check Order information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),price);
        //assertEquals(pendingOrder.getQuantity(clOrderID),qty);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),qty-newQty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_10_Buy_ModifyPriceUp(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "CCL";
        String side = "1";
        int qty = 10;
        String price = "10.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        String newPrice = "11.01";
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,qty,newPrice);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Up");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step5: Check Order Information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),newPrice);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_11_Buy_ModifyPriceDown(Method method){
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "CCL";
        String side = "1";
        int qty = 10;
        String price = "11.00";
        traderUI.placeOrderByFIXMessage(clOrderID,accountID,side,symbol,qty,price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        String newPrice = "10.99";
        sleepInSecond(3);
        traderUI.modifyOrderByFIXMessage(clOrderID2,clOrderID,accountID,side,symbol,qty,newPrice);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Modify Price Down");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID),"Amended");
        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step5: Check Order information");

        pendingOrderPage = orderHistoryPage.clickPendingOrdder();
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Go to Pending Order");
        sleepInSecond(3);
        assertEquals(pendingOrderPage.getAccountID(clOrderID2),accountID);
        assertEquals(pendingOrderPage.getTickerCode(clOrderID2),symbol);
        assertEquals(pendingOrderPage.getSide(clOrderID2),side);
        assertEquals(pendingOrderPage.getStatus(clOrderID2),"Placed");
        assertEquals(pendingOrderPage.getPrice(clOrderID2),newPrice);
        assertEquals(pendingOrderPage.getQuantity(clOrderID2),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step7: Check Order information");
    }
    @Test
    public void OF_12_Buy_CancelOrder(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID = "23072307";
        String symbol = "CCL";
        String side = "1";
        int qty = 10;
        String price = "11.00";
        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place a New Order");
        sleepInSecond(3);
        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        traderUI.cancelOrderByFIXMessage(clOrderID2, clOrderID, accountID, symbol);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Cancel Order");
        sleepInSecond(3);
        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
        itMonitorPage = loginPage.login(account, passWord);

        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);
        assertEquals(orderHistoryPage.getAccountID(clOrderID2),accountID);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID2),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID2),side);
        assertEquals(orderHistoryPage.getStatus(clOrderID2),"Withdrawn");
        assertEquals(orderHistoryPage.getPrice(clOrderID2),price);
        assertEquals(orderHistoryPage.getQuantity(clOrderID2),qty);
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Order information");
    }
    @Test
    public void OF_13_MatchFully(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID1 = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID1 = "23072307";
        String symbol = "LVS";
        String side1 = "1";
        int qty1 = 10;
        String price1 = "40.00";
        traderUI.placeOrderByFIXMessage(clOrderID1, accountID1, side1, symbol, qty1, price1);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place Buy Order");
        sleepInSecond(3);

        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID2 = "28282828";
        String side2 = "2";
        int qty2 = 10;
        String price2 = "40.00";
        traderUI.clearInput();
        traderUI.placeOrderByFIXMessage(clOrderID2, accountID2, side2, symbol, qty2, price2);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Place Sell Order");
        sleepInSecond(3);

        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
        itMonitorPage = loginPage.login(account, passWord);
        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);

        assertEquals(orderHistoryPage.getAccountID(clOrderID2),accountID2);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID2),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID2),side2);
        assertEquals(orderHistoryPage.getStatus(clOrderID2),"Filled");
        assertEquals(orderHistoryPage.getPrice(clOrderID2),price2);
        assertEquals(orderHistoryPage.getQuantity(clOrderID2),qty2);
        assertEquals(orderHistoryPage.getExecPrice(clOrderID2),price1);
        assertEquals(orderHistoryPage.getExecQuantity(clOrderID2), Math.min(qty2, qty1));
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Sell Order information");

        assertEquals(orderHistoryPage.getAccountID(clOrderID1),accountID1);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID1),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID1),side1);
        assertEquals(orderHistoryPage.getStatus(clOrderID1),"Filled");
        assertEquals(orderHistoryPage.getPrice(clOrderID1),price1);
        assertEquals(orderHistoryPage.getQuantity(clOrderID1),qty1);
        assertEquals(orderHistoryPage.getExecPrice(clOrderID1),price1);
        assertEquals(orderHistoryPage.getExecQuantity(clOrderID1), Math.min(qty2, qty1));
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Buy Order information");
    }
    @Test
    public void OF_14_MatchPartially(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
        String clOrderID1 = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID1 = "23072307";
        String symbol = "LVS";
        String side1 = "1";
        int qty1 = 10;
        String price1 = "40.00";
        traderUI.placeOrderByFIXMessage(clOrderID1, accountID1, side1, symbol, qty1, price1);
        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place Buy Order");
        sleepInSecond(3);

        String clOrderID2 = String.valueOf(RandomValue.genRandomClOrderID());
        String accountID2 = "28282828";
        String side2 = "2";
        int qty2 = 30;
        String price2 = "40.00";
        traderUI.clearInput();
        traderUI.placeOrderByFIXMessage(clOrderID2, accountID2, side2, symbol, qty2, price2);
        ExtentTestManager.getTest().log(Status.INFO, "Step2: Place Sell Order");
        sleepInSecond(3);

        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
        itMonitorPage = loginPage.login(account, passWord);
        orderHistoryPage = itMonitorPage.clickTradingData();
        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
        sleepInSecond(3);

        assertEquals(orderHistoryPage.getAccountID(clOrderID2),accountID2);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID2),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID2),side2);
        assertEquals(orderHistoryPage.getStatus(clOrderID2),"Filled");
        assertEquals(orderHistoryPage.getPrice(clOrderID2),price2);
        assertEquals(orderHistoryPage.getQuantity(clOrderID2),qty2);
        assertEquals(orderHistoryPage.getExecPrice(clOrderID2),price1);
        assertEquals(orderHistoryPage.getExecQuantity(clOrderID2), Math.min(qty2, qty1));
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Sell Order information");

        assertEquals(orderHistoryPage.getAccountID(clOrderID1),accountID1);
        assertEquals(orderHistoryPage.getTickerCode(clOrderID1),symbol);
        assertEquals(orderHistoryPage.getSide(clOrderID1),side1);
        assertEquals(orderHistoryPage.getStatus(clOrderID1),"Filled");
        assertEquals(orderHistoryPage.getPrice(clOrderID1),price1);
        assertEquals(orderHistoryPage.getQuantity(clOrderID1),qty1);
        assertEquals(orderHistoryPage.getExecPrice(clOrderID1),price1);
        assertEquals(orderHistoryPage.getExecQuantity(clOrderID1), Math.min(qty2, qty1));
        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Buy Order information");
    }
//    @Test
//    public void OF_15_RejectByInvalidLotSize(Method method) {
//        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
//        ExtentTestManager.getTest().log(Status.INFO, "Step0: Change Data");
//        itMonitorPage = loginPage.login(account, passWord);
//        participantPage = itMonitorPage.clickMasterData();
//        tickerPage = participantPage.clickOnTicker();
//        sleepInSecond(4);
//        String symbol = "LVS";
//        tickerPage.searchTicker(symbol);
//        tickerPage.selectTicker(symbol);
//        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
//        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
//
//
//
//
////        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
////        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
////        String accountID = "23072307";
////        String symbol = "LVS";
////        String side = "1";
////        int qty = 1;
////        String price = "40.00";
////        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
////        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place Buy Order");
////
////        sleepInSecond(3);
////        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
////        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
////        itMonitorPage = loginPage.login(account, passWord);
////
////        orderHistoryPage = itMonitorPage.clickTradingData();
////        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
////        sleepInSecond(3);
////        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
////        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
////        assertEquals(orderHistoryPage.getSide(clOrderID),side);
////        assertEquals(orderHistoryPage.getStatus(clOrderID),"Rejected");
////        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
////        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
////        assertEquals(orderHistoryPage.getComment(clOrderID),"Invalid lot size");
////        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Order information");
//    }
//    @Test
//    public void OF_16_RejectByInvalidMinLot(Method method) {
//        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
//        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
//        String accountID = "23072307";
//        String symbol = "LVS";
//        String side = "1";
//        int qty = 1;
//        String price = "40.00";
//        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
//        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place Buy Order");
//
//        sleepInSecond(3);
//        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
//        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
//        itMonitorPage = loginPage.login(account, passWord);
//
//        orderHistoryPage = itMonitorPage.clickTradingData();
//        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
//        sleepInSecond(3);
//        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
//        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
//        assertEquals(orderHistoryPage.getSide(clOrderID),side);
//        assertEquals(orderHistoryPage.getStatus(clOrderID),"Rejected");
//        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
//        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
//        assertEquals(orderHistoryPage.getComment(clOrderID),"Invalid min lot");
//        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Order information");
//    }
//    @Test
//    public void OF_17_RejectByInvalidTickSize(Method method) {
//        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
//        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
//        String accountID = "23072307";
//        String symbol = "LVS";
//        String side = "1";
//        int qty = 10;
//        String price = "40.01";
//        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
//        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place Buy Order");
//
//        sleepInSecond(3);
//        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
//        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
//        itMonitorPage = loginPage.login(account, passWord);
//
//        orderHistoryPage = itMonitorPage.clickTradingData();
//        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
//        sleepInSecond(3);
//        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
//        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
//        assertEquals(orderHistoryPage.getSide(clOrderID),side);
//        assertEquals(orderHistoryPage.getStatus(clOrderID),"Rejected");
//        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
//        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
//        assertEquals(orderHistoryPage.getComment(clOrderID),"Invalid tick size");
//        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Order information");
//    }
//    @Test
//    public void OF_18_RejectByInvalidAccountID(Method method) {
//        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
//        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
//        String accountID = "2307230733";
//        String symbol = "LVS";
//        String side = "1";
//        int qty = 10;
//        String price = "40.00";
//        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
//        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place Buy Order");
//
//        sleepInSecond(3);
//        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
//        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
//        itMonitorPage = loginPage.login(account, passWord);
//
//        orderHistoryPage = itMonitorPage.clickTradingData();
//        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
//        sleepInSecond(3);
//        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
//        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
//        assertEquals(orderHistoryPage.getSide(clOrderID),side);
//        assertEquals(orderHistoryPage.getStatus(clOrderID),"Rejected");
//        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
//        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
//        assertEquals(orderHistoryPage.getComment(clOrderID),"Account invalid");
//        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Order information");
//    }
//    @Test
//    public void OF_19_RejectByDuplicateClOrderID(Method method) {
//        ExtentTestManager.startTest(method.getName(), "Verify Order Flow");
//        String clOrderID = String.valueOf(RandomValue.genRandomClOrderID());
//        String accountID = "23072307";
//        String symbol = "LVS";
//        String side = "1";
//        int qty = 10;
//        String price = "40.01";
//        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
//        traderUI.clearInput();
//        sleepInSecond(3);
//        traderUI.placeOrderByFIXMessage(clOrderID, accountID, side, symbol, qty, price);
//        ExtentTestManager.getTest().log(Status.INFO, "Step1: Place Buy Order");
//
//        sleepInSecond(3);
//        loginPage = new PiXLoginPageObject(PIX_PROD_ENV);
//        ExtentTestManager.getTest().log(Status.INFO, "Step3: Go to Admin account");
//        itMonitorPage = loginPage.login(account, passWord);
//
//        orderHistoryPage = itMonitorPage.clickTradingData();
//        ExtentTestManager.getTest().log(Status.INFO, "Step4: Go to Trading Data > Order History");
//        sleepInSecond(3);
//        assertEquals(orderHistoryPage.getAccountID(clOrderID),accountID);
//        assertEquals(orderHistoryPage.getTickerCode(clOrderID),symbol);
//        assertEquals(orderHistoryPage.getSide(clOrderID),side);
//        assertEquals(orderHistoryPage.getStatus(clOrderID),"Rejected");
//        assertEquals(orderHistoryPage.getPrice(clOrderID),price);
//        assertEquals(orderHistoryPage.getQuantity(clOrderID),qty);
//        assertEquals(orderHistoryPage.getComment(clOrderID),"Duplicate external order id");
//        ExtentTestManager.getTest().log(Status.INFO, "Step6: Check Order information");
//    }

    @AfterMethod
    public void afterMethod(){
        DriverManager.getDriver().quit();
    }

}
