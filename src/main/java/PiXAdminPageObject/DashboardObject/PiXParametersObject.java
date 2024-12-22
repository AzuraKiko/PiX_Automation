package PiXAdminPageObject.DashboardObject;

import Commons.BasePage;
import PiXAdminPageUI.DashboardUI.PiXITMonitorUI;
import PiXAdminPageUI.DashboardUI.*;

public class PiXParametersObject extends BasePage {
    public void switchToIFrame() {
        switchToFrameIframe(PiXParametersUI.PARAMETER_IFRAME);
    }
    public String getCancelRatioTimeInterval(){
        String value = getElementAttributeByJS(PiXParametersUI.CANCEL_RATIO_TIME_INTERVAL, "value");
        return value;
    }
    public String getSellCancelRatio(){
        String value = getElementAttributeByJS(PiXParametersUI.SELL_CANCEL_RATIO, "value");
        return value;
    }
    public String getSellVolumeCancelRatio(){
        String value = getElementAttributeByJS(PiXParametersUI.SELL_VOLUME_CANCEL_RATIO, "value");
        return value;
    }
    public String getBuyCancelRatio(){
        String value = getElementAttributeByJS(PiXParametersUI.BUY_CANCEL_RATIO, "value");
        return value;
    }
    public String getBuyVolumeCancelRatio(){
        String value = getElementAttributeByJS(PiXParametersUI.BUY_VOLUME_CANCEL_RATIO, "value");
        return value;
    }
    public String getSelfMatchTimeInterval(){
        String value = getElementAttributeByJS(PiXParametersUI.SELF_MATCH_TIME_INTERVAL, "value");
        return value;
    }
    public String getSelfMatchRatio(){
        String value = getElementAttributeByJS(PiXParametersUI.SELF_MATCH_RATIO, "value");
        return value;
    }
    public String getPriceLimmit(){
        String value = getElementAttributeByJS(PiXParametersUI.PRICE_LIMIT, "value");
        return value;
    }
    public String getMaxOrderValue(){
        String value = getElementAttributeByJS(PiXParametersUI.MAX_ORDER_VALUE, "value");
        return value;
    }
    public String getOMSLatencyWarning(){
        String value = getElementAttributeByJS(PiXParametersUI.OMS_LATENCY_WARNING, "value");
        return value;
    }
    public String getOMSLatencyAlarm(){
        String value = getElementAttributeByJS(PiXParametersUI.OMS_LATENCY_ALARM, "value");
        return value;
    }
    public String getOMSTimeInterval(){
        String value = getElementAttributeByJS(PiXParametersUI.OMS_TIME_INTERVAL, "value");
        return value;
    }
    public String getMDLatencyWarning(){
        String value = getElementAttributeByJS(PiXParametersUI.MD_LATENCY_WARNING, "value");
        return value;
    }
    public String getMDTimeInternal(){
        String value = getElementAttributeByJS(PiXParametersUI.MD_TIME_INTERVAL, "value");
        return value;
    }



    public void switchToDefault() {
        switchToDefaultContent();
    }



}
