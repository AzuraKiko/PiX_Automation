package PiXAdminPageObject.DashboardObject;

import Commons.BasePage;
import PiXAdminPageObject.ITRiskHeartBeatObject.PiXITObject;
import PiXAdminPageObject.MasterDataObject.PiXParticipantObject;
import PiXAdminPageObject.TradeSurveillanceObject.*;
import PiXAdminPageObject.TradingDataObject.PiXOrderHistoryObject;
import PiXAdminPageUI.DashboardUI.*;
import PiXAdminPageUI.ITRiskHeartBeatUI.PiXITUI;
import org.openqa.selenium.JavascriptExecutor;


public class PiXITMonitorObject extends BasePage {
    public void switchToIFrame() {
        switchToFrameIframe(PiXITMonitorUI.IT_MONITOR_IFRAME);
    }
    public double compareOMSLatency () {
        waitForAllElementVisible(PiXITMonitorUI.OMS_LATENCY_VALUE);
        double omsLatency = Double.parseDouble(getElementText(PiXITMonitorUI.OMS_LATENCY_VALUE));
        return omsLatency;
    }
    public double compareMDLatency () {
        //switchToFrameIframe(IT_MONITOR_IFRAME);
        waitForAllElementVisible(PiXITMonitorUI.MD_LATENCY_VALUE);
        double mdLatency = Double.parseDouble(getElementText(PiXITMonitorUI.MD_LATENCY_VALUE));
        return mdLatency;
    }
    public double AccumulatedPendingOrder () {
        scrollToElement(PiXITMonitorUI.ACCUMULATED_PENDING_ORDERS);
        double pending = Double.parseDouble(getElementText(PiXITMonitorUI.ACCUMULATED_PENDING_ORDERS));
        return pending;
    }
    public double AccumulatedMatchOrder () {
        scrollToElement(PiXITMonitorUI.ACCUMULATED_MATCHED_ORDERS);
        double match = Double.parseDouble(getElementText(PiXITMonitorUI.ACCUMULATED_MATCHED_ORDERS));
        return match;
    }
    public double AccumulatedCancelOrder () {
        scrollToElement(PiXITMonitorUI.ACCUMULATED_CANCEL_ORDERS);
        double cancel = Double.parseDouble(getElementText(PiXITMonitorUI.ACCUMULATED_CANCEL_ORDERS));
        return cancel;
    }
    public String compareMatching () {
        //switchToFrameIframe(PiXITMonitorUI.IT_MONITOR_IFRAME);
        scrollToBottomPage();
        String matching = getElementText(PiXITMonitorUI.MATCHING);
        return matching;
    }
    public PiXRiskTradeObject clickRiskTrade(){
        clickToElement(PiXITMonitorUI.RISK_TRADE_TAG);
        return new PiXRiskTradeObject();
    }
    public PiXParametersObject clickParameters(){
        clickToElement(PiXITMonitorUI.PARAMTERS_TAG);
        return new PiXParametersObject();
    }
    public PiXITObject clickIT(){
        clickToElement(PiXITMonitorUI.IT_RISK_HEARTBEAT);
        return new PiXITObject();
    }
    public PiXParticipantObject clickMasterData(){
        clickToElement(PiXITMonitorUI.MASTER_DATA);
        return new PiXParticipantObject();
    }
    public PiXOrderHistoryObject clickTradingData(){
        clickToElement(PiXITMonitorUI.TRADING_DATA);
        return new PiXOrderHistoryObject();
    }
    public PiXCancelRatioObject clickTradeSurveillance(){
        clickToElement(PiXITMonitorUI.TRADE_SURVEILLANCE);
        return new PiXCancelRatioObject();
    }



}
