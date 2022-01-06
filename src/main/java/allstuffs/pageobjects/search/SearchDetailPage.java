package allstuffs.pageobjects.search;

import allstuffs.helpers.MethodHelper;
import allstuffs.pageobjects.AbstractPage;
import org.openqa.selenium.WebElement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SearchDetailPage extends AbstractPage {
    public static final String SEARCH_RESULT_DATE = "//span[@class='orange-text']";
    public static final String CITY_NAME_DETAIL = "//span[@class='orange-text']/following-sibling::h2";
    public static final String TEMPERATURE = "//span[@class='heading']";

    public String getDateTimeFromSearchResult() {
        waitToElementVisible(SEARCH_RESULT_DATE);
        return getTextElement(SEARCH_RESULT_DATE);
    }

    public String getCityFromSearchResult() {
        waitToElementVisible(CITY_NAME_DETAIL);
        String getDate = getTextElement(CITY_NAME_DETAIL);
        return getDate;
    }

    public boolean checkDateTimeOfResult(String searchText) {
        String timeStampCurrentDate = new SimpleDateFormat("MMM d, hh:mmaa").format(Calendar.getInstance().getTime());
        String timeStampCurrentDateFormat = timeStampCurrentDate.replace("AM", "am").replace("PM", "pm");
        // due to the second is sometimes different between app and system, we will ignore the second number at this case
        // to make sure automation test stability
        String firstSub1 = timeStampCurrentDateFormat.substring(0, 12);
        String lastSub1 = timeStampCurrentDateFormat.substring(14, 15);
        String firstSub2 = getDateTimeFromSearchResult().substring(0, 12);
        String lastSub2 = getDateTimeFromSearchResult().substring(14, 15);

        if (firstSub1.equals(firstSub2) && lastSub1.equals(lastSub2)) {
            return true;
        } else {
            System.out.println("Date time get is " + firstSub1 + lastSub1 + " while date time system is " + firstSub2 + lastSub2);
            return false;
        }
    }

    public boolean checkCityNameDisplay(String cityName) {
        HashMap<String, String> listCountryToCheck;
        MethodHelper methodHelper = new MethodHelper();
        listCountryToCheck = methodHelper.readCSV("cityname");
        String CITY_NAME_DETAIL_FORMAT = String.format(CITY_NAME_DETAIL, cityName);
        waitToElementVisible(CITY_NAME_DETAIL_FORMAT);
        if (getTextElement(CITY_NAME_DETAIL_FORMAT).equals(listCountryToCheck.get(cityName))) {
            return true;
        } else {
            return false;
        }
    }

    public String getTemperature() {
        waitToElementVisible(TEMPERATURE);
        return getTextElement(TEMPERATURE);
    }

    public boolean checkTemperatureDisplay() {
        MethodHelper methodHelper = new MethodHelper();
        String[] arStr = getTemperature().split("°");
        if (methodHelper.isStringInteger(arStr[0]) == true && arStr[1].equals("C")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkAllItemOfListResultDisplay(String searchText) {
        Boolean flag = false;
        waitToElementVisible(SearchListPage.SEARCH_LIST_RESULT);
        List<WebElement> webElementList = getListElements(SearchListPage.SEARCH_LIST_RESULT);
        for (int i = 1; i <= webElementList.size(); i++) {
            String SEARCH_ITEM_RESULT_FORMAT = String.format(SearchListPage.SEARCH_ITEM_RESULT, i);
            waitToElementVisible(SEARCH_ITEM_RESULT_FORMAT);
            clickToElement(SEARCH_ITEM_RESULT_FORMAT);
            if (checkCityNameDisplay(searchText) == true && checkDateTimeOfResult(searchText) == true && checkTemperatureDisplay() == true) {
                flag = true;
            } else {
                flag = false;
                System.out.println("check city name display of element:  " + SEARCH_ITEM_RESULT_FORMAT + " is " + checkCityNameDisplay(searchText));
                System.out.println("check date time display of element " + SEARCH_ITEM_RESULT_FORMAT + " is " + checkDateTimeOfResult(searchText));
                System.out.println("check temperature display of element " + SEARCH_ITEM_RESULT_FORMAT + " is " + checkTemperatureDisplay());
                break;
            }
            backToPage();
        }
        return flag;
    }

}
