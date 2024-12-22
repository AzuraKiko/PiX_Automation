package PiXAdminPageObject.TradeSurveillanceObject;

import Commons.BasePage;
import PiXAdminPageUI.DashboardUI.PiXParametersUI;
import PiXAdminPageUI.TradeSurveillanceUI.PiXCancelRatioSettingUI;
import PiXAdminPageUI.TradeSurveillanceUI.PiXSelfMatchSettingUI;

public class PiXSelfMatchSettingObject extends BasePage {
    public void switchToIFrame() {
        switchToFrameIframe(PiXSelfMatchSettingUI.SELFMATCH_IFRAME);
    }

    public String getSelfMatchTimeInterval(){
        String value = getElementAttributeByJS(PiXSelfMatchSettingUI.TIME_INTERVAL_FIELD, "value");
        return value;
    }
    public String getSelfMatchRatio(){
        String value = getElementAttributeByJS(PiXSelfMatchSettingUI.SELF_MATCH_RATIO_FIELD, "value");
        return value;
    }
    public void switchToDefault() {
        switchToDefaultContent();
    }
    public void clickITRiskHearbeatTag(){
        clickToElement(PiXSelfMatchSettingUI.IT_RISK_HEARTBEAT_TAG);
    }

}
