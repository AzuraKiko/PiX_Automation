package Commons;

import java.io.File;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String REPORT_NG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
    public static final long SHOT_TIMEOUT = 1;
    public static final int THREE_SECONDS = 3;
    public static final long FIVE_SECONDS = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final int CLICK_INCREASE = 4;
    public static final String PIX_STG_ENV = "https://pixuat-admin.tdt.asia/";
    public static final String PIX_PROD_ENV = "https://admin.pi-x.sg/";
    public static final String TRADER_UI = "http://localhost:8080/";

}
