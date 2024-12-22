package PiXAdminPageObject.MasterDataObject;

import Commons.BasePage;
import PiXAdminPageObject.ITRiskHeartBeatObject.PiXITObject;
import PiXAdminPageUI.DashboardUI.PiXITMonitorUI;
import PiXAdminPageUI.MasterDataUI.PiXParticipantUI;
import PiXAdminPageUI.TradingDataUI.PiXOrderHistoryUI;

import static Commons.BasePageUI.*;

public class PiXParticipantObject extends BasePage {
    public void clickOnCreate(){
        waitForAllElementVisible(PiXParticipantUI.CREATE_BUTTON);
        clickToElement(PiXParticipantUI.CREATE_BUTTON);
    }
    public void insertParticipantInf(String userName, String gmail,String phone, String id) {

        sendKeyToElement(DYNAMIC_TEXTBOX_BY_TEXT,userName, "Name");
        sendKeyToElement(DYNAMIC_TEXTBOX_BY_TEXT,gmail, "Email");
        sendKeyToElement(DYNAMIC_TEXTBOX_BY_TEXT,phone, "Phone Number");
        sendKeyToElement(DYNAMIC_TEXTBOX_BY_TEXT,id, "Account ID");

    }
    public void clickOnSave(){
        waitForAllElementVisible(PiXParticipantUI.SAVE_BUTTON);
        clickToElement(PiXParticipantUI.SAVE_BUTTON);
    }
    public void assignFixAccount(){
       clickToElement(PiXParticipantUI.FIX_ACCOUNT_TAG);
       clickToElement(PiXParticipantUI.ADD_FIX_ACCOUNT);
       clickToElement(PiXParticipantUI.SELECT_FIX_ACCOUNT_OPTION);
    }
    public void assignWebSocketAccount(){
        clickToElement(PiXParticipantUI.WS_ACCOUNT_TAG);
        clickToElement(PiXParticipantUI.ADD_WS_ACCOUNT);
        clickToElement(PiXParticipantUI.SELECT_WS_ACCOUNT_OPTION);
    }
    public void selectFixAccount(String title){
        clickToCheckbox(DYNAMIC_CHECKBOX_BY_TITLE,title);
    }
    public void clickOnSelectButton(){
        clickToElement(PiXParticipantUI.SELECT_BUTTON);
    }
    public PiXTickerObject clickOnTicker(){
        clickToElement(PiXParticipantUI.TICKERS);
        return new PiXTickerObject();
    }
    public String getAccountHolder(String id){
        getElementTextByJS(PiXParticipantUI.ACCOUNT_HOLDER, id);
        return getElementTextByJS(PiXParticipantUI.ACCOUNT_HOLDER, id);
    }
    public String getEmail(String id){
        getElementTextByJS(PiXParticipantUI.EMAIL, id);
        return getElementTextByJS(PiXParticipantUI.EMAIL, id);
    }
    public String getPhoneNumber(String id){
        getElementTextByJS(PiXParticipantUI.PHONE_NUMBER, id);
        return getElementTextByJS(PiXParticipantUI.PHONE_NUMBER, id);
    }
    public String getStatus(String id){
        getElementTextByJS(PiXParticipantUI.STATUS, id);
        return getElementTextByJS(PiXParticipantUI.STATUS, id);
    }
    public String getSettlementMethod(String id){
        getElementTextByJS(PiXParticipantUI.SETTLEMENT_SEND_METHOD, id);
        return getElementTextByJS(PiXParticipantUI.SETTLEMENT_SEND_METHOD, id);
    }
    public void backToParticipants(){
        clickToElement(PiXParticipantUI.PARTICIPANT_LINK);
    }
    public void searchParticipants(String id){
        sendKeyToElement(PiXParticipantUI.ACCOUNT_ID_FIELD, id);
        clickToElement(PiXParticipantUI.SEARCH_BUTTON);
        sleepInSecond(2);
    }



}

