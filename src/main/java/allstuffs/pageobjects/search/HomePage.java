package allstuffs.pageobjects.search;

import allstuffs.pageobjects.AbstractPage;

public class HomePage extends AbstractPage {
    public static final String FIRST_NAME_TEXTBOX = "//input[@id='firstName']";
    public static final String PREFERRED_FIRST_NAME_TEXTBOX = "//input[@id='firstName']";
    public static final String MIDDLE_NAME_TEXTBOX = "//input[@id='firstName']";
    public static final String LAST_NAME_TEXTBOX = "//input[@id='lastName']";
    public static final String HOME_PHONE_TEXTBOX = "//input[@id='phonePrimary']";
    public static final String CELL_PHONE_TEXTBOX = "//input[@id='phonePrimary']";
    public static final String EMAIL_TEXTBOX = "//input[@id='email']";
    public static final String CONFIRM_EMAIL_TEXTBOX = "//input[@id='emailConfirm']";
    public static final String PASS_WORD_TEXTBOX = "//input[@id='password']";
    public static final String SHOW_CHECKBOX = "//input[@id='passwordShow']";
    public static final String AGREE_CHECKBOX = " //input[@id='agreeLegal']";
    public static final String CREATE_ACCOUNT = "//button[@id='buttonSignUp']";
    public static final String CHAR_8_25 = "//li[@id='pwCond0']";



    public void inputTextToSearch(String searchText) {
        waitToElementVisible(FIRST_NAME_TEXTBOX);
        sendkeyToElement(FIRST_NAME_TEXTBOX, searchText);
    }
    public void fillTextToFirstName(String searchText) {
        waitToElementVisible(FIRST_NAME_TEXTBOX);
        sendkeyToElement(FIRST_NAME_TEXTBOX, searchText);
    }
    public void fillTextToPreferredFirstName(String searchText) {
        waitToElementVisible(PREFERRED_FIRST_NAME_TEXTBOX);
        sendkeyToElement(PREFERRED_FIRST_NAME_TEXTBOX, searchText);
    }
    public void fillTextToMiddleName(String searchText) {
        waitToElementVisible(MIDDLE_NAME_TEXTBOX);
        sendkeyToElement(MIDDLE_NAME_TEXTBOX, searchText);
    }
    public void fillTextToLastName(String searchText) {
        waitToElementVisible(LAST_NAME_TEXTBOX);
        sendkeyToElement(LAST_NAME_TEXTBOX, searchText);
    }
    public void fillTextToPassword(String searchText) {
        waitToElementVisible(PASS_WORD_TEXTBOX);
        sendkeyToElement(PASS_WORD_TEXTBOX, searchText);
    }
    public void checkSHowCheckBox() {
        waitToElementVisible(PASS_WORD_TEXTBOX);
        if ( !isElementSelected(SHOW_CHECKBOX) )
        { clickToElement(SHOW_CHECKBOX); }
    }
    public boolean isCHar8_25PasswordNoticeDisplayed() {
        waitToElementVisible(CHAR_8_25);
        return isElementDisplayed(CHAR_8_25);
    }

    public boolean checkAttributeValueOfShowCheckbox(String oAttributeName, String oValueToCompare) {
        boolean result = false;
        String objectAttributeValue = getAttributeValue(SHOW_CHECKBOX, oAttributeName);
        if (objectAttributeValue.equals(oValueToCompare)) {
            result = true;
        }
        return result;}


    public void clickToCreateAccount() {
        waitToElementVisible(CREATE_ACCOUNT);
        clickToElement(CREATE_ACCOUNT);
    }


}