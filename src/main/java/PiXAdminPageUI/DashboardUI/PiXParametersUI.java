package PiXAdminPageUI.DashboardUI;

import Commons.BasePage;

public class PiXParametersUI {
    public static final String PARAMETER_IFRAME = "//iframe[@id='iframe_parameters_child']";
    public static final String CANCEL_RATIO_TIME_INTERVAL= "//div[text()='Cancel Ratio']//parent::div//following-sibling::div[1]//div[1]//div[1]//div[2]//input";
    public static final String SELL_CANCEL_RATIO = "//div[text()='Sell Cancel Ratio']//parent::div//following-sibling::div[1]//input";
    public static final String SELL_VOLUME_CANCEL_RATIO = "//div[text()='Sell volume cancel ratio']//parent::div//following-sibling::div[1]//input";
    public static final String BUY_CANCEL_RATIO = "//div[text()='Buy Cancel Ratio']//parent::div//following-sibling::div[1]//input";
    public static final String BUY_VOLUME_CANCEL_RATIO = "//div[text()='Buy Volume Cancel Ratio']//parent::div//following-sibling::div[1]//input";
    public static final String SELF_MATCH_TIME_INTERVAL = "//div[text()='Self Match']//parent::div//following-sibling::div[1]//div[1]//div[1]//div[2]//input";
    public static final String SELF_MATCH_RATIO = "//div[text()='self match ratio']//parent::div//following-sibling::div[1]//input";
    public static final String PRICE_LIMIT = "//div[text()='Price Limit (default)']//parent::div//following-sibling::div[1]//input";
    public static final String MAX_ORDER_VALUE = "//div[text()='Max Order Value (default)']//parent::div//following-sibling::div[1]//input";
    public static final String OMS_LATENCY_WARNING = "//div[text()='OMS Latency Warning']//parent::div//following-sibling::div[1]//input";
    public static final String OMS_LATENCY_ALARM = "//div[text()='OMS Latency Alarm']//parent::div//following-sibling::div[1]//input";
    public static final String OMS_TIME_INTERVAL = "//div[text()='OMS Latency Alarm']//parent::div//parent::div//following-sibling::div[1]//div[1]//div[2]//input";
    public static final String MD_LATENCY_WARNING = "//div[text()='Market Data Latency Warning']//parent::div//following-sibling::div[1]//input";
    public static final String MD_TIME_INTERVAL = "//div[text()='Market Data Latency Warning']//parent::div//parent::div//following-sibling::div[1]//div[1]//div[2]//input";

}
