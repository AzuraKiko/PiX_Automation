package PiXAdminPageUI.TradingDataUI;

public class PiXPendingOrderUI {
    public static final String ACCOUNT_ID_FIELD = "//input[@id='account_id']";
    public static final String TICKER_CODE_FIELD = "//input[@id='symbol_code']";
    public static final String ORDER_TYPE_FIELD = "";
    public static final String ORDER_SIDE_FIELD = "//select[@id='order_side']";
    public static final String SEARCH_BUTTON = "//button[@class='btn btn-primary btn-sm o_button_search_pending_orders']";
    public static final String DATA_TABLE = "//div[@class='table-responsive']//table";
    public static final String BLANK_TABLE = "//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped o_empty_list']";
    public static final String ACCOUNT_ID = "//td[text()='%s']//preceding-sibling::td[1]";
    public static final String TICKER_CODE = "//td[text()='%s']//following-sibling::td[2]";
    public static final String SIDE = "//td[text()='%s']//following-sibling::td[4]";
    public static final String STATUS = "//td[text()='%s']//following-sibling::td[5]";
    public static final String PRICE = "//td[text()='%s']//following-sibling::td[6]";
    public static final String QUANTITY = "//td[text()='%s']//following-sibling::td[7]";
    public static final String CHOOSE_ALL_RECORDS_CHECKBOX = "//th[@title='Account ID']//preceding-sibling::th//div//input";
    public static final String ALL_ORDERS = "//a[@class='o_list_select_domain']";
    public static final String ACTION_BUTTON = "//span[text()='Action']";
    public static final String CANCEL_ORDER_BUTTON ="//a[text()='Cancel Orders']";
    public static final String CONFIRM_BUTTON = "//span[text()='Confirm']";
    public static final String ORDER_NO = "//th[@title='Order Time']//ancestor::thead//following-sibling::tbody//tr//td[text()='%s']//preceding-sibling::td[10]";

}