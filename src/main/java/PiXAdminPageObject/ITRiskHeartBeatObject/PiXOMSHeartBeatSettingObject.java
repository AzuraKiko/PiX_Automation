package PiXAdminPageObject.ITRiskHeartBeatObject;

import Commons.BasePage;
import PiXAdminPageUI.ITRiskHeartBeatUI.PiXMDHeartBeatSettingUI;
import PiXAdminPageUI.ITRiskHeartBeatUI.PiXOMSHeartBeatSettingUI;

public class PiXOMSHeartBeatSettingObject extends BasePage {
    public void switchToIFrame() {
        switchToFrameIframe(PiXOMSHeartBeatSettingUI.OMS_SETTING_IFRAME);
    }
    public String getOMSLatencyWarning(){
        String value = getElementAttributeByJS(PiXOMSHeartBeatSettingUI.OMS_LATENCY_WARNING_FIELD, "value");
        return value;
    }
    public String getOMSLatencyAlarm(){
        String value = getElementAttributeByJS(PiXOMSHeartBeatSettingUI.OMS_LATENCY_ALARM_FIELD, "value");
        return value;
    }
    public String getOMSTimeInterval(){
        String value = getElementAttributeByJS(PiXOMSHeartBeatSettingUI.TIME_INTERVAL_FIELD, "value");
        return value;
    }
}
