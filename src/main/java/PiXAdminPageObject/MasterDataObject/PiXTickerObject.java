package PiXAdminPageObject.MasterDataObject;

import Commons.BasePage;
import PiXAdminPageUI.MasterDataUI.PiXTickerUI;

import java.util.List;

public class PiXTickerObject extends BasePage {

    public List<String> getListRecord() {
        List<String> listFirstPage = getListElementsText(PiXTickerUI.LAST_UPDATED_TIME_COLUMN);
        clickToElement(PiXTickerUI.NEXT_BUTTON);
        sleepInSecond(2);
        List<String> listSecondPage = getListElementsText(PiXTickerUI.LAST_UPDATED_TIME_COLUMN);
        listSecondPage.addAll(listFirstPage);
        return listSecondPage;
    }
    public int countElementConatin() {
        getCurrentDate();
        List<String> list = getListRecord();
        int count = 0;
        for(String element : list){
            if(element.contains(getCurrentDate())){
                count++;
            }
        }
        return count;
    }
    public void goToPreviousPage(){
        clickToElement(PiXTickerUI.PREVIOUS_BUTTON);
    }
    public boolean getListRateCompare() {
        List<String> listFirstPage = getListElementsText(PiXTickerUI.RATE_COLUMN);
        clickToElement(PiXTickerUI.NEXT_BUTTON);
        sleepInSecond(2);
        List<String> listSecondPage = getListElementsText(PiXTickerUI.RATE_COLUMN);
        listSecondPage.addAll(listFirstPage);
        List<String> list = listSecondPage;
        for(String element : list){
            if(!element.equals("0.50")){
                return false;
            }
        }
        return true;
    }
    public boolean getListLotSizeCompare() {
        List<String> listFirstPage = getListElementsText(PiXTickerUI.LOT_SIZE_COLUMN);
        clickToElement(PiXTickerUI.NEXT_BUTTON);
        sleepInSecond(2);
        List<String> listSecondPage = getListElementsText(PiXTickerUI.LOT_SIZE_COLUMN);
        listSecondPage.addAll(listFirstPage);
        List<String> list = listSecondPage;
        for(String element : list){
            if(!element.equals("1")){
                return false;
            }
        }
        return true;
    }
    public boolean getListTickSizeCompare() {
        List<String> listFirstPage = getListElementsText(PiXTickerUI.TICK_SIZE_COLUMN);
        clickToElement(PiXTickerUI.NEXT_BUTTON);
        sleepInSecond(2);
        List<String> listSecondPage = getListElementsText(PiXTickerUI.TICK_SIZE_COLUMN);
        listSecondPage.addAll(listFirstPage);
        List<String> list = listSecondPage;
        for(String element : list){
            if(!element.equals("0.01")){
                return false;
            }
        }
        return true;
    }
    public boolean getListMinLotCompare() {
        List<String> listFirstPage = getListElementsText(PiXTickerUI.MIN_LOT_COLUMN);
        clickToElement(PiXTickerUI.NEXT_BUTTON);
        sleepInSecond(2);
        List<String> listSecondPage = getListElementsText(PiXTickerUI.MIN_LOT_COLUMN);
        listSecondPage.addAll(listFirstPage);
        List<String> list = listSecondPage;
        for(String element : list){
            if(!element.equals("1")){
                return false;
            }
        }
        return true;
    }


    public void searchTicker(String symbol){
        sendKeysToElements(PiXTickerUI.TICKER_CODE_FIELD, symbol);
        clickToElement(PiXTickerUI.SEARCH_BUTTON);
        sleepInSecond(5);
    }
    public void selectTicker(String symbol){
        clickToElement(PiXTickerUI.TICKER_RECORD, symbol);
    }
    public void disableTicker(){
        clickToElement(PiXTickerUI.DISABLE_BUTTON);
    }
}