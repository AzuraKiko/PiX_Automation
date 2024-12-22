package PiXAdminPageUI.MasterDataUI;

public class PiXParticipantUI {
    public static final String ACCOUNT_ID_FIELD = "//input[@id='account_id']";
    public static final String SEARCH_BUTTON = "//button[@class='btn btn-primary btn-sm o_button_search_participant']";
    public static final String CREATE_BUTTON = "//button[@class='btn btn-primary o_list_button_add']";
    public static final String NAME_FIELD = "//input[@name='name']";
    public static final String SAVE_BUTTON = "//button[@class='btn btn-primary o_form_button_save']";
    public static final String DISCARD_BUTTON = "//button[@class='btn btn-secondary o_form_button_cancel']";
    public static final String FIX_ACCOUNT_TAG = "//a[text()='FIX Account']";
    public static final String ADD_FIX_ACCOUNT = "//th[@title='FIX Type']//ancestor::table//tbody//tr[1]//td//a";
    public static final String  SELECT_FIX_ACCOUNT_OPTION = "//td[@title='ZANY01']";
    public static final String  SELECT_BUTTON = "//span[text()='Select']";
    public static final String WS_ACCOUNT_TAG = "//a[text()='WebSocket Account']";
    public static final String ADD_WS_ACCOUNT = "//th[@title='WebSocket Account']//ancestor::table//tbody//tr[1]//td//a";
    public static final String SELECT_WS_ACCOUNT_OPTION = "//td[@title='ws_trade_g24']";
    public static final String CONTACT_TAG = "//a[text()='Contact']";
    public static final String ADD_CONTACT = "//th[@title='Name']//ancestor::table//tbody//tr[1]//td//a";
    public static final String TICKERS = "//span[normalize-space()='Ticker']";
    public static final String ACCOUNT_HOLDER = "//td[@title='%s']//following-sibling::td[1]";
    public static final String EMAIL = "//td[@title='%s']//following-sibling::td[3]";
    public static final String PHONE_NUMBER = "//td[@title='%s']//following-sibling::td[4]";
    public static final String STATUS = "//td[@title='%s']//following-sibling::td[5]";
    public static final String SETTLEMENT_SEND_METHOD = "//td[@title='%s']//following-sibling::td[6]";
    public static final String PARTICIPANT_LINK = "//a[text()='Participants']";


}
