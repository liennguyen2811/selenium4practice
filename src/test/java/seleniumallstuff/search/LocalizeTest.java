package seleniumallstuff.search;

import allstuffs.pageobjects.HomePageLocalize;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import seleniumallstuff.BaseTestLocalize;

import java.util.*;

public class LocalizeTest extends BaseTestLocalize {


    @DataProvider(name = "Language")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"English"},
                {"France"}
        };
    }
    @Test(dataProvider = "Language")
    public void checkLocalization(String Language) {
        SoftAssert softAssert = new SoftAssert();
        HomePageLocalize homePageLocalize = new HomePageLocalize();
        homePageLocalize.launchWebAppLanguage(Language);
        String[] listElements = {"//a[@class='navbar-brand pull-left flip']","//a[@id='live_now_button']","//a[contains(@href,\"/contact-us-0\")]"};
        Map<String, String> getListLocatorAndText  = homePageLocalize.getListLocatorAndText(Language,listElements);
        Map<String, Boolean> resultMethodPage = homePageLocalize.getListLocatorAndResult(getListLocatorAndText);
        for (Map.Entry<String, Boolean> set :resultMethodPage.entrySet()) {
            softAssert.assertTrue(set.getValue());
            softAssert.assertTrue(set.getValue(), "language is wrong at xpath " + set.getKey());
            softAssert.assertAll();
        }
    }

}