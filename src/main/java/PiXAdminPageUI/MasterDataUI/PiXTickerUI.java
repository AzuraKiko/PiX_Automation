package PiXAdminPageUI.MasterDataUI;

public class PiXTickerUI {
    public static final String TICKER_CODE_FIELD = "//input[@id='symbol_code']";
    public static final String STATUS_FIELD = "";
    public static final String PRODUCT_TYPE_FIELD = "";
    public static final String EXCHANGE_FIELD = "";
    public static final String SEARCH_BUTTON = "//button[@class='btn btn-primary btn-sm o_button_search_symbol']";
    public static final String CREATE_BUTTON = "//button[@class='btn btn-primary o_list_button_add']";
    public static final String IMPORT_FILE_BUTTON = "//button[@class='oe_ps_symbol_import_button btn btn btn-primary custom-style-button']";
    public static final String TICKER_TABLE = "//table[@class='o_list_table table table-sm table-hover table-striped o_list_table_ungrouped']";
    public static final String LAST_UPDATED_TIME_COLUMN = "//th[@title='Last Updated on']//ancestor::thead//following-sibling::tbody//tr/td[15]";
    public static final String RATE_COLUMN = "//th[@title='Rate']//ancestor::thead//following-sibling::tbody//tr/td[7]";
    public static final String LOT_SIZE_COLUMN = "//th[@title='Lot Size']//ancestor::thead//following-sibling::tbody//tr/td[8]";
    public static final String TICK_SIZE_COLUMN = "//th[@title='Tick Size']//ancestor::thead//following-sibling::tbody//tr/td[9]";
    public static final String MIN_LOT_COLUMN = "//th[@title='Min Lot']//ancestor::thead//following-sibling::tbody//tr/td[10]";
    public static final String NEXT_BUTTON = "//button[@title='Next']";
    public static final String PREVIOUS_BUTTON = "//button[@title='Previous']";
    public static final String TICKER_RECORD = "//td[text()='%s']";
    public static final String DISABLE_BUTTON = "//span[text()='Disable Trading']";

}
