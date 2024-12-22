package PiXAdminPageUI.DashboardUI;

import Commons.BasePage;

public class PiXITMonitorUI extends BasePage {
    public static final String IT_MONITOR_IFRAME = "//iframe[@id='iframe_summary_it_monitor_child']";
    public static final String OMS_LATENCY_VALUE = "//div[text()='OMS latency']//following-sibling::div//child::div//child::span";
    public static final String MD_LATENCY_VALUE = "//div[text()='Market data latency']//following-sibling::div//child::div//child::span";
    public static final String MATCHING = "//div[text()='Buy and Sell orders quantity (fully filled)']//following-sibling::div[1]";
    public static final String ACCUMULATED_PENDING_ORDERS = "//div[text()='Accumulated pending orders']//following-sibling::div";
    public static final String ACCUMULATED_MATCHED_ORDERS = "//div[text()='Accumulated matched orders']//following-sibling::div";
    public static final String ACCUMULATED_CANCEL_ORDERS = "//div[text()='Accumulated cancel orders']//following-sibling::div";
    public static final String RISK_TRADE_TAG = "//span[text()='Risk & Trade Surveillance']";
    public static final String PARAMTERS_TAG = "//span[text()='Parameters']";
    public static final String IT_RISK_HEARTBEAT = "//a[contains(text(),\"IT/Risk/Heartbeat\")]";
    public static final String MASTER_DATA = "//a[contains(text(),\"Master Data\")]";
    public static final String TRADING_DATA = "//a[contains(text(),\"Trading Data\")]";
    public static final String TRADE_SURVEILLANCE = "//a[contains(text(),\"Trade Surveillance\")]";
}
