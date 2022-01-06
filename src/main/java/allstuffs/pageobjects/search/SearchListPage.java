package allstuffs.pageobjects.search;


import allstuffs.pageobjects.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchListPage extends AbstractPage {

    public static final String SEARCH_RESULT = "//table[@class='table']//td[2]/b/a";
    public static final String SEARCH_LIST_RESULT = "//table[@class='table']//td[2]/b/a";
    public static final String SEARCH_ITEM_RESULT = "(//table[@class='table']//td[2]/b/a)[%s]";

    public void goToResultDetail() {
        waitToElementVisible(SEARCH_RESULT);
        clickToElement(SEARCH_RESULT);
    }

    public void goToEachItemOfListResult() {
        waitToElementVisible(SEARCH_LIST_RESULT);
        List<WebElement> webElementList = getListElements(SEARCH_LIST_RESULT);
        for (int i = 1; i <= webElementList.size(); i++) {
            String SEARCH_ITEM_RESULT_FORMAT = String.format(SEARCH_ITEM_RESULT, i);
            waitToElementVisible(SEARCH_ITEM_RESULT_FORMAT);
            clickToElement(SEARCH_ITEM_RESULT_FORMAT);
            backToPage();
        }
    }

    public boolean isSearchListResultDisplayed() {
        waitToElementVisible(SEARCH_LIST_RESULT);
        return isElementDisplayed(SEARCH_LIST_RESULT);
    }
}

