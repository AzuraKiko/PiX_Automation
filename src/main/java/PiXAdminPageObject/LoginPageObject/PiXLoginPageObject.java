package PiXAdminPageObject.LoginPageObject;

import Commons.BasePage;
import PiXAdminPageObject.DashboardObject.PiXITMonitorObject;
import PiXAdminPageUI.LoginPageUI.PiXAdminLoginPageUI;
import static Commons.GlobalConstants.*;

public class PiXLoginPageObject extends BasePage {
    public PiXLoginPageObject (String loginUrl){
        openPageUrl(loginUrl);
    }
    public void goToAdmin(){
        openPageUrl(PIX_PROD_ENV);
    }

    public PiXITMonitorObject login(String account, String passWord){
        insertAccountNo(account);
        insertPassWord(passWord);
        clickLoginButton();
        return new PiXITMonitorObject();
    }
    private void insertAccountNo (String account){
        waitForAllElementVisible(PiXAdminLoginPageUI.ACCOUNT_NO_FIELD);
        sendKeysToElements(PiXAdminLoginPageUI.ACCOUNT_NO_FIELD, account);
    }
    private void insertPassWord (String passWord){
        waitForAllElementVisible(PiXAdminLoginPageUI.PASSWORD_FIELD);
        sendKeysToElements(PiXAdminLoginPageUI.PASSWORD_FIELD, passWord);
    }
    private void clickLoginButton (){
        waitForAllElementVisible(PiXAdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(PiXAdminLoginPageUI.LOGIN_BUTTON);
    }


}
