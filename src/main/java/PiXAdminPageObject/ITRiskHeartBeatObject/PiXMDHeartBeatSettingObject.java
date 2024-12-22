package PiXAdminPageObject.ITRiskHeartBeatObject;

import Commons.BasePage;
import PiXAdminPageUI.DashboardUI.PiXParametersUI;
import PiXAdminPageUI.ITRiskHeartBeatUI.PiXITUI;
import PiXAdminPageUI.ITRiskHeartBeatUI.PiXMDHeartBeatSettingUI;

public class PiXMDHeartBeatSettingObject extends BasePage {
    public void switchToIFrame() {
        switchToFrameIframe(PiXMDHeartBeatSettingUI.MD_SETTING_IFRAME);
    }
    public String getMDLatencyWarning(){
        String value = getElementAttributeByJS(PiXMDHeartBeatSettingUI.MD_LATENCY_WARNING, "value");
        return value;
    }
    public String getMDTimeInternal(){
        String value = getElementAttributeByJS(PiXMDHeartBeatSettingUI.TIME_INTERVAL, "value");
        return value;
    }
    public void switchToDefault() {
        switchToDefaultContent();
    }
}
