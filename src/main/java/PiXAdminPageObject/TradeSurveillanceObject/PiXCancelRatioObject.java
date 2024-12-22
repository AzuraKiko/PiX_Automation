package PiXAdminPageObject.TradeSurveillanceObject;

import Commons.BasePage;
import PiXAdminPageUI.DashboardUI.*;
import PiXAdminPageUI.TradeSurveillanceUI.*;

public class PiXCancelRatioObject extends BasePage {
    public PiXCancelRatioSettingObject clickCancelRatioSetting(){
        clickToElement(PiXCancelRatioUI.SETTING_TAG);
        clickToElement(PiXCancelRatioUI.CANCEL_RATIO_SETTING_TAG);
        return new PiXCancelRatioSettingObject();
    }
    public PiXSelfMatchSettingObject clickSelfMatchSettingTag(){
        clickToElement(PiXCancelRatioUI.SETTING_TAG);
        clickToElement(PiXCancelRatioUI.SELF_MATCH_SETTING_TAG);
        return new PiXSelfMatchSettingObject();
    }


}
