package seleniumallstuff.search;

import allstuffs.pageobjects.search.HomePage;
import seleniumallstuff.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void checkPasswordNotice() {
        //1.Open new log in page
        HomePage homePage = new HomePage();
        homePage.fillTextToFirstName("Lien");
        homePage.fillTextToLastName("Nguyen");
        homePage.fillTextToPassword("123456");
        Assert.assertTrue(homePage.isCHar8_25PasswordNoticeDisplayed());
        Assert.assertTrue(homePage.checkAttributeValueOfShowCheckbox("type","checkbox"));
        homePage.checkSHowCheckBox();
        homePage.sleepInSecond(2);
        homePage.clickToCreateAccount();
        homePage.sleepInSecond(2);

    }

}
