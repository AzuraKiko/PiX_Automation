package PiXAdminPageObject.TradingDataObject;

import Commons.BasePage;
import PiXAdminPageUI.DashboardUI.PiXITMonitorUI;
import PiXAdminPageUI.TradingDataUI.PiXOrderHistoryUI;
import PiXAdminPageUI.TradingDataUI.PiXPendingOrderUI;

public class PiXOrderHistoryObject extends BasePage {
    public PiXPendingOrderObject clickPendingOrdder(){
        clickToElement(PiXOrderHistoryUI.PENDING_ORDER_TAG);
        return new PiXPendingOrderObject();
    }
    public String getAccountID(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.ACCOUNT_ID,clOrderID);
        return getElementTextByJS(PiXOrderHistoryUI.ACCOUNT_ID, clOrderID);
    }
    public String getTickerCode(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.TICKER_CODE,clOrderID);
        return getElementTextByJS(PiXOrderHistoryUI.TICKER_CODE, clOrderID);
    }
    public String getSide(String clOrderID) {
        String side = getElementTextByJS(PiXOrderHistoryUI.SIDE, clOrderID);
        if (side.equals("Buy")) {
            return "1";
        } else if (side.equals("Sell")) {
            return "2";
        } else {
            // Return a default value or throw an exception if unexpected input is encountered
            return "Unknown"; // or you can return null, throw an exception, etc.
        }
    }
    public String getStatus(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.STATUS,clOrderID);
        return getElementTextByJS(PiXOrderHistoryUI.STATUS, clOrderID);
    }
    public String getPrice(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.PRICE,clOrderID);
        return getElementTextByJS(PiXOrderHistoryUI.PRICE, clOrderID);
    }
    public String getExecPrice(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.EXEC_PRICE,clOrderID);
        return getElementTextByJS(PiXOrderHistoryUI.EXEC_PRICE, clOrderID);
    }
    public int getQuantity(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.QUANTITY,clOrderID);
        return Integer.parseInt(getElementTextByJS(PiXOrderHistoryUI.QUANTITY, clOrderID));
    }
    public int getExecQuantity(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.EXEC_QUANTITY,clOrderID);
        return Integer.parseInt(getElementTextByJS(PiXOrderHistoryUI.EXEC_QUANTITY, clOrderID));
    }
    public String getComment(String clOrderID){
        getElementTextByJS(PiXOrderHistoryUI.COMMENT,clOrderID);
        return getElementTextByJS(PiXOrderHistoryUI.COMMENT, clOrderID);
    }
}
