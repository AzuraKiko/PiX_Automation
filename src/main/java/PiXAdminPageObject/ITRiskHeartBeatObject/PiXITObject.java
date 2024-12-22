package PiXAdminPageObject.ITRiskHeartBeatObject;

import Commons.BasePage;
import PiXAdminPageObject.TradingDataObject.PiXOrderHistoryObject;
import PiXAdminPageUI.DashboardUI.PiXITMonitorUI;
import PiXAdminPageUI.ITRiskHeartBeatUI.PiXITUI;
import PiXAdminPageUI.TradingDataUI.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class PiXITObject extends BasePage {

    public void switchToIFrame() {
        switchToFrameIframe(PiXITUI.IT_IFRAME);
    }

    public void placeOrder(String accountID, String side, String ticker, String qty, String price) {

        clickToElement(PiXITUI.ADD_NEW_ORDER_BUTTON);
        sendKeyToElement(PiXITUI.ACCOUNT_ID_FIELD, accountID);
        pressKeyToElement(PiXITUI.ACCOUNT_ID_FIELD, Keys.ARROW_DOWN);
        pressKeyToElement(PiXITUI.ACCOUNT_ID_FIELD, Keys.ARROW_DOWN);
        pressKeyToElement(PiXITUI.ACCOUNT_ID_FIELD, Keys.ENTER);

        selecItemInDefaultDropdown(PiXITUI.SIDE_FIELD, side);

        sendKeysToElements(PiXITUI.TICKER_FIELD, ticker);
        pressKeyToElement(PiXITUI.TICKER_FIELD, Keys.ARROW_DOWN);
        pressKeyToElement(PiXITUI.TICKER_FIELD, Keys.ARROW_DOWN);
        pressKeyToElement(PiXITUI.TICKER_FIELD, Keys.ENTER);

        sendKeysToElements(PiXITUI.QUANTITY_FIELD, qty);

        sendKeysToElements(PiXITUI.PRICE_FIELD, price);

        clickToElement(PiXITUI.SAVE_BUTTON_ORDER);
    }

    public String orderAlert (){
        waitForAllElementVisible(PiXITUI.PLACE_ORDER_ALERT);
        String placeOrderAlert = getElementText(PiXITUI.PLACE_ORDER_ALERT);
        return placeOrderAlert;
    }
    public void killSwitch (){
        waitForAllElementVisible(PiXITUI.STOP_MATCHING_CORE_BUTTON);
        clickToElement(PiXITUI.STOP_MATCHING_CORE_BUTTON);
        clickToElement(PiXITUI.CONFIRM_KILL_SWITCH);
    }
    public void closeAddOrderPopup(){
        clickToElement(PiXITUI.CLOSE_ADD_ORDER_POPUP);
    }
    public void offKillSwitch(){
        waitForAllElementVisible(PiXITUI.START_MATCHING_CORE_BUTTON);
        clickToElement(PiXITUI.START_MATCHING_CORE_BUTTON);
        clickToElement(PiXITUI.CONFIRM_KILL_SWITCH);
    }
    public void forWardMessageFlow(String recipient, String note){
        selectFirstRecordOnTable(PiXITUI.TABLE);
        scrollToTopPage();
        //scrollToElement(PiXITUI.FORWARD_BUTTON);
        clickToElementByJS(PiXITUI.FORWARD_BUTTON);

        sendKeysToElements(PiXITUI.RECIPIENT_FIELD, recipient);
        pressKeyToElement(PiXITUI.RECIPIENT_FIELD, Keys.ARROW_DOWN);
        pressKeyToElement(PiXITUI.RECIPIENT_FIELD, Keys.ARROW_DOWN);
        pressKeyToElement(PiXITUI.RECIPIENT_FIELD, Keys.ENTER);

        sendKeysToElements(PiXITUI.NOTE_FIELD, note);

        clickToElement(PiXITUI.SAVE_BUTTON);
    }
    public void searchByKeyWord(String keyWord){
        sendKeyToElement(PiXITUI.LOG_MONITORING_SEARCH_BOX, keyWord);
        clickToElement(PiXITUI.REFRESH_BUTTON);

    }
    public PiXOMSHeartBeatSettingObject clickOMSHeartBeatSettingTag(){
        clickToElement(PiXITUI.HEARTBEAT_SETTING_TAG);
        waitForAllElementVisible(PiXITUI.OMS_SETTING_TAG);
        clickToElement(PiXITUI.OMS_SETTING_TAG);
        return new PiXOMSHeartBeatSettingObject();
    }
    public PiXMDHeartBeatSettingObject clickMDHeartBeatSettingTag(){
        clickToElement(PiXITUI.HEARTBEAT_SETTING_TAG);
        waitForAllElementVisible(PiXITUI.MD_SETTING_TAG);
        clickToElement(PiXITUI.MD_SETTING_TAG);
        return new PiXMDHeartBeatSettingObject();
    }
    public PiXOrderHistoryObject clickTradingData(){
        clickToElement(PiXITUI.TRADING_DATA);
        return new PiXOrderHistoryObject();
    }
    public void switchToDefault() {
        switchToDefaultContent();
    }


}
