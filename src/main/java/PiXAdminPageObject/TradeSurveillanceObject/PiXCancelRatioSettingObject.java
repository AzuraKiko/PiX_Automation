package PiXAdminPageObject.TradeSurveillanceObject;

import Commons.BasePage;
import PiXAdminPageUI.DashboardUI.PiXParametersUI;
import PiXAdminPageUI.TradeSurveillanceUI.PiXCancelRatioSettingUI;
import PiXAdminPageUI.TradeSurveillanceUI.PiXCancelRatioUI;

public class PiXCancelRatioSettingObject extends BasePage {
    public void switchToIFrame() {
        switchToFrameIframe(PiXCancelRatioSettingUI.CANCEL_RATIO_SETTING_IFRAME);
    }
    public String getCancelRatioTimeInterval(){
        String value = getElementAttributeByJS(PiXCancelRatioSettingUI.TIME_INTERVAL_FIELD, "value");
        return value;
    }
    public String getSellCancelRatio(){
        String value = getElementAttributeByJS(PiXCancelRatioSettingUI.SELL_CANCEL_RATIO_FIELD, "value");
        return value;
    }
    public String getSellVolumeCancelRatio(){
        String value = getElementAttributeByJS(PiXCancelRatioSettingUI.SELL_VOLUME_CANCEL_RATIO_FIELD, "value");
        return value;
    }
    public String getBuyCancelRatio(){
        String value = getElementAttributeByJS(PiXCancelRatioSettingUI.BUY_CANCEL_RATIO_FIELD, "value");
        return value;
    }
    public String getBuyVolumeCancelRatio(){
        String value = getElementAttributeByJS(PiXCancelRatioSettingUI.BUY_VOLUME_CANCEL_RATIO_FIELD, "value");
        return value;
    }
    public PiXSelfMatchSettingObject clickSelfMatchSettingTag(){
        clickToElement(PiXCancelRatioSettingUI.SETTING_TAG);
        clickToElement(PiXCancelRatioSettingUI.SELF_MATCH_SETTING_TAG);
        return new PiXSelfMatchSettingObject();
    }
    public void switchToDefault() {
        switchToDefaultContent();
    }
}
