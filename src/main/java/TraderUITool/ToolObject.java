package TraderUITool;

import Commons.BasePage;

import static Commons.GlobalConstants.PIX_PROD_ENV;

public class ToolObject extends BasePage {
    public ToolObject(String loginUrl){
        openPageUrl(loginUrl);
    }
    public void goToTool(){
        openPageUrl(PIX_PROD_ENV);
    }
    public void placeOrder(String account, String side, String symbol, String qty, String price){
        selecItemInDefaultDropdown(ToolUI.SIDE_FIELD,side);
        sendKeysToElements(ToolUI.QUANTITY_FIELD, qty);
        selecItemInDefaultDropdown(ToolUI.TYPE_FIELD,"Limit");
        sendKeysToElements(ToolUI.ACCOUNT_FIELD, account);
        sendKeyToElement(ToolUI.SYMBOL_FIELD,symbol);
        sendKeyToElement(ToolUI.PRICE_FIELD,price);
        clickToElement(ToolUI.SUBMIT_BUTTON);
    }
    public void placeOrderByFIXMessage(String clOrderID,String account, String side, String symbol, int qty, String price){
        clickToElement(ToolUI.SUPER_FIX_TAG);
        clickToElement(ToolUI.SESSION_RADIO);
        sendKeysToElements(ToolUI.FIX_MESSAGE_INPUT, "8=FIX.4.4|9=238|35=D|34=16|49=ZANY01|52=20230829-02:16:41.383|56=PIXDEVGW|1="+account+"|11="+clOrderID+"|15=USD|21=1|38="+qty+"|40=2|44="+price+"|54="+side+"|55="+symbol+"|59=0|60=20230829-02:16:41.376|207=US|10=065|");
        clickToElement(ToolUI.SEND_BUTTON);
    }
    public void modifyOrderByFIXMessage(String clOrderID, String OrgID,String account, String side, String symbol, int qty, String price){
//        clickToElement(ToolUI.SUPER_FIX_TAG);
//        clickToElement(ToolUI.SESSION_RADIO);
        clearTextAreaByJS(ToolUI.FIX_MESSAGE_INPUT);
        sendKeysToElements(ToolUI.FIX_MESSAGE_INPUT, "8=FIX.4.4|9=238|35=G|11="+clOrderID+"|41="+OrgID+"|49=ZANY01|52=20230829-02:16:41.383|37=82391|1="+account+"|15=USD|21=1|38="+qty+"|40=2|44="+price+"|54="+side+"|55="+symbol+"|59=0|60=20230829-02:16:41.376|167=CS|207=US|10=065|");
        clickToElement(ToolUI.SEND_BUTTON);
    }
    public void cancelOrderByFIXMessage(String clOrderID, String OrgID,String account, String symbol){
//        clickToElement(ToolUI.SUPER_FIX_TAG);
//        clickToElement(ToolUI.SESSION_RADIO);
        clearTextAreaByJS(ToolUI.FIX_MESSAGE_INPUT);
        sendKeysToElements(ToolUI.FIX_MESSAGE_INPUT, "8=FIX.4.4|9=238|35=F|11="+clOrderID+"|41="+OrgID+"|49=ZANY01|56=PIXDEVGW|52=20230829-02:16:41.383|1="+account+"|38=20|54=2|55="+symbol+"|60=20230829-02:16:41.376|207=US|10=065|");
        clickToElement(ToolUI.SEND_BUTTON);
    }
    public void clearInput(){
        clearTextAreaByJS(ToolUI.FIX_MESSAGE_INPUT);
    }






}
