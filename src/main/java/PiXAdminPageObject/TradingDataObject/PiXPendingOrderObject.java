package PiXAdminPageObject.TradingDataObject;

import ApiHelper.RestAssuredHelper;
import Commons.BasePage;
import Commons.OrderDateInfor;
import PiXAdminPageUI.TradingDataUI.PiXPendingOrderUI;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PiXPendingOrderObject extends BasePage {

    public boolean blankTable(){
        boolean result = isElementPresent(PiXPendingOrderUI.BLANK_TABLE);
        return result;
    }
    public void searchOrder(String account, String symbol, String side){
        sendKeysToElements(PiXPendingOrderUI.ACCOUNT_ID_FIELD, account);
        sendKeysToElements(PiXPendingOrderUI.TICKER_CODE_FIELD, symbol);
        selecItemInDefaultDropdown(PiXPendingOrderUI.ORDER_SIDE_FIELD, side);
        clickToElement(PiXPendingOrderUI.SEARCH_BUTTON);
    }
    public String getAccountID(String clOrderID){
        getElementTextByJS(PiXPendingOrderUI.ACCOUNT_ID,clOrderID);
        return getElementTextByJS(PiXPendingOrderUI.ACCOUNT_ID, clOrderID);
    }
    public String getTickerCode(String clOrderID){
        getElementText(PiXPendingOrderUI.TICKER_CODE,clOrderID);
        return getElementText(PiXPendingOrderUI.TICKER_CODE, clOrderID);
    }
    public String getSide(String clOrderID) {
        String side = getElementText(PiXPendingOrderUI.SIDE, clOrderID);
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
        getElementText(PiXPendingOrderUI.STATUS,clOrderID);
        return getElementText(PiXPendingOrderUI.STATUS, clOrderID);
    }
    public String getPrice(String clOrderID){
        getElementText(PiXPendingOrderUI.PRICE,clOrderID);
        return getElementText(PiXPendingOrderUI.PRICE, clOrderID);
    }
    public int getQuantity(String clOrderID){
        getElementText(PiXPendingOrderUI.QUANTITY,clOrderID);
        return Integer.parseInt(getElementText(PiXPendingOrderUI.QUANTITY, clOrderID));
    }

    public void cancelAllOrder(){
        checkToDefaultCheckBoxOrRadio(PiXPendingOrderUI.CHOOSE_ALL_RECORDS_CHECKBOX);
        if(isElementPresent(PiXPendingOrderUI.ALL_ORDERS)){
            clickToElement(PiXPendingOrderUI.ALL_ORDERS);
        }
        clickToElement(PiXPendingOrderUI.ACTION_BUTTON);
        clickToElement(PiXPendingOrderUI.CANCEL_ORDER_BUTTON);
        clickToElement(PiXPendingOrderUI.CONFIRM_BUTTON);
    }
    public void reloadPage(){
        refreshCurrentPage();
    }

    public List<Long> extractExecTimes() {
        List<Long> extractExecTimes = RestAssuredHelper.extractExecTimes(RestAssuredHelper.getPendingOrder(getSessionId()));
        return extractExecTimes;
    }
    public long findNearestTimestamp( long givenTimestamp, List<Long> timestamps) {
        long nearestTimestamp = timestamps.get(0);
        long minDifference = Math.abs(nearestTimestamp - givenTimestamp);
        for (long timestamp : timestamps) {
            long difference = Math.abs(timestamp - givenTimestamp);
            if (difference < minDifference) {
                minDifference = difference;
                nearestTimestamp = timestamp;
            }
        }
        System.out.println("Nearest timestamp: " + nearestTimestamp);
        return nearestTimestamp;
    }

    public static String timestampToDateTime(long timestamp) {
        LocalDateTime dateTime;

        // Check if the timestamp is in milliseconds (typically more than 10 digits)
        if (String.valueOf(timestamp).length() > 10) {
            dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        } else {
            dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        }

        // Format LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
        OrderDateInfor.FORMATTED_DATE_TIME = dateTime.format(formatter);
        System.out.println("Formatted date time: " + OrderDateInfor.FORMATTED_DATE_TIME);
        return dateTime.format(formatter);
    }
    public String getOrderNo() {
        System.out.println("Order No: " + getElementText(PiXPendingOrderUI.ORDER_NO, OrderDateInfor.FORMATTED_DATE_TIME));
        return getElementText(PiXPendingOrderUI.ORDER_NO, OrderDateInfor.FORMATTED_DATE_TIME);
    }
}
