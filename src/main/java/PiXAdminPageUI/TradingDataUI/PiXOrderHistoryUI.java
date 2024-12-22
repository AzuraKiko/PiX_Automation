package PiXAdminPageUI.TradingDataUI;

public class PiXOrderHistoryUI {
    public static final String ORDER_ID_FIELD = "//input[@id='order_id']";
    public static final String ACCOUNT_ID_FIELD = "//input[@id='account_id']";
    public static final String TICKER_CODE_FIELD = "//input[@id='symbol_code']";
    public static final String ORDER_TYPE_FIELD = "";
    public static final String ORDER_SIDE_FIELD = "";
    public static final String STATUS_FIELD = "";
    public static final String ORDER_DATE_FROM_FIELD = "";
    public static final String ORDER_DATE_TO_FIELD = "";
    public static final String SEARCH_BUTTON = "//button[@class='btn btn-primary btn-sm o_button_search_order_history']";
    public static final String PENDING_ORDER_TAG = "//span[text()='Pending Order']";
    public static final String TRADE_HISTORY_TAG = "//span[text()='Trade History']";
    public static final String ACCOUNT_ID = "//td[text()='%s']//preceding-sibling::td[1]";
    public static final String TICKER_CODE = "//td[text()='%s']//following-sibling::td[4]";
    public static final String SIDE = "//td[text()='%s']//following-sibling::td[6]";
    public static final String STATUS = "//td[text()='%s']//following-sibling::td[7]";
    public static final String PRICE = "//td[text()='%s']//following-sibling::td[8]";
    public static final String QUANTITY = "//td[text()='%s']//following-sibling::td[9]";
    public static final String EXEC_PRICE = "//td[text()='%s']//following-sibling::td[10]";
    public static final String EXEC_QUANTITY = "//td[text()='%s']//following-sibling::td[11]";
    public static final String COMMENT = "//td[text()='%s']//following-sibling::td[16]";
}
