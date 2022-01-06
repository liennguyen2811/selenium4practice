package allstuffs.pageobjects;

import allstuffs.common.BrowserControl;
import allstuffs.common.TestConfig;
import org.json.JSONObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class AbstractPage {
    By by;
    Select select;
    Actions action;
    WebElement element;
    long shortTimeout = 5;
    long longTimeout = 30;
    WebDriver driver = BrowserControl.browser;
    List<WebElement> elements;
    JavascriptExecutor jsExecutor;
    WebDriverWait waitExplicit;
    public HashMap<String, String> languageElements;

    public AbstractPage() {
        jsExecutor = (JavascriptExecutor) driver;
        waitExplicit = new WebDriverWait(driver, longTimeout);
        action = new Actions(driver);
    }

    protected WebDriver getDriver() {
        return BrowserControl.browser;
    }

    public void openUrl(String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageCurrentSource() {
        return driver.getPageSource();
    }

    public void backToPage() {
        driver.navigate().back();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void forwardToPage() {
        driver.navigate().forward();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void waitToAlertPresence() {
        waitExplicit.until(ExpectedConditions.alertIsPresent());
    }

    public void sendkeyToAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public String getTextAlert() {
        return driver.switchTo().alert().getText();
    }

    public void clickToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void switchToOldTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public List<WebElement> getListElements(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public void sendkeyToElement(String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void sendKeyCodeToElement(String locator, Keys key) {
        driver.findElement(By.xpath(locator)).sendKeys(key);
    }

    public void clearTextInElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(locator);
        element.clear();
    }

    public void clearTextHandleElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(locator);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public void selectItemInMultipleDropdown(String locator, String valueItem) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        select.selectByVisibleText(valueItem);
    }

    public String getItemInDropdown(String locator) {
        element = driver.findElement(By.xpath(locator));
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void sleepInSecond(long numberInSecond) {
        try {
            Thread.sleep(numberInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectItemInDropdownJS(String parentLocator, String allItemLocator, String expectedItem) {
        element = driver.findElement(By.xpath(parentLocator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].click;", element);
        sleepInSecond(1);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
        elements = driver.findElements(By.xpath(allItemLocator));
        for (WebElement item : elements) {
            System.out.println(item.getText());
            if (item.getText().contains(expectedItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                //element = driver.findElement(By.xpath(elements));
                element.click();
                sleepInSecond(2);
                break;
            }
        }
    }

    public String getAttributeValue(String locator, String attributeName) {
        element = driver.findElement(By.xpath(locator));
        return element.getAttribute(attributeName);
    }

    public String getTextElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getText();
    }

    public String getColorElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getCssValue("color");
    }

    public String getColorBackgroundElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getCssValue("background-color");
    }

    public String getColorElementHandle(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.getCssValue("caret-color");
    }

    public int countElementNumber(String locator) {
        elements = driver.findElements(By.xpath(locator));
        return elements.size();
    }

    public void checkRadioButton(String locator) {
        element = driver.findElement(By.xpath(locator));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckRadioButton(String locator) {
        element = driver.findElement(By.xpath(locator));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(String locator) {
        try {
            //Handle element exists in DOM, display or not
            element = driver.findElement(By.xpath(locator));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            // Handle element not exist in DOM
            return false;
        }
    }

    public boolean isElementSelected(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isSelected();
    }

    public boolean isElementEnabled(String locator) {
        element = driver.findElement(By.xpath(locator));
        return element.isEnabled();
    }

    public void switchToChildWindowByID(String parent) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parent)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToChildWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public boolean closeAllWindowWithoutParent(String parentWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentWindow)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    public void switchToFrameOrIframe(String locator) {
        element = driver.findElement(By.xpath(locator));
        driver.switchTo().frame(element);
    }

    public void switchToParentPage() {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element).perform();
    }

    public void doubleClickToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        action.doubleClick(element).perform();
    }

    public void sendKeyboardToElement(String locator, String key) {
        element = driver.findElement(By.xpath(locator));
        action.sendKeys(element, key).perform();
    }

    public boolean verifyImageLoaded(String locator) {
        boolean status;
        element = driver.findElement(By.xpath(locator));
        status = (boolean) jsExecutor.executeScript("return argument[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && argument [0].naturalWidth>0", element);
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitToElementVisible(String locator) {
        by = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public void waitToElementPresence(String locator) {
        by = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForInvisibility(String locator, int maxSeconds) {
        element = driver.findElement(By.xpath(locator));
        Long startTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - startTime < maxSeconds * 1000 && element.isDisplayed()) {
            }
        } catch (StaleElementReferenceException e) {
            return;
        }
    }

    public void waitToElementInvisible(String locator) {
        by = By.xpath(locator);
        waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitToElementClickable(String locator) {
        element = driver.findElement(By.xpath(locator));
        waitExplicit.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void jsScrollToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jSClickOn(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].click()", element);
    }

    public void jSRunOnLocator(String javaScriptToRun) {
        jsExecutor.executeScript(javaScriptToRun);
    }

    public void clickElementByCoordinates(String locator, int xcord, int ycord) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element, xcord, ycord).click().build().perform();
    }

    public void highlightElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        for (int i = 0; i < 10; i++) {
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 2px solid red;");
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }


    public void clickToListElements(String locator) {
        List<WebElement> listElements = driver.findElements(By.xpath(locator));
        for (int i = 0; i < listElements.size(); i++) {
            listElements.get(i).click();
            sleepInSecond(1);
        }
    }

    public void cutTextFromElement(String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element).click().keyDown(element, Keys.SHIFT).sendKeys(element, value)
                .keyUp(element, Keys.SHIFT).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .keyDown(Keys.CONTROL).sendKeys("x").keyUp(Keys.CONTROL).build().perform();
    }

    public void pasteTextInto(String locator) {
        element = driver.findElement(By.xpath(locator));
        action.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL)
                .build().perform();
    }

    public static void setClipboardData(String pathFile) {
        // StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(pathFile);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public static void uploadFile(String fileLocation) {
        try {
            // Setting clip board with file location
            setClipboardData(fileLocation);
            // native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(3000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public ArrayList<String> getTextElementsInList(String locator) {
        List<WebElement> listElements = driver.findElements(By.xpath(locator));
        ArrayList<String> arrayList = new ArrayList<>();
        for (WebElement listElement : listElements) {
            arrayList.add(listElement.getText());
        }
        return arrayList;
    }

    public boolean isDataSortedAscending(String locator) {
        ArrayList<String> arrayList = getTextElementsInList(locator);
        ArrayList<String> sortList = new ArrayList<>(arrayList);
        /* Sort statement*/
        Collections.sort(sortList);
        return arrayList.equals(sortList);
    }

    public boolean isDataSortedDescending(String locator) {
        ArrayList<String> arrayList = getTextElementsInList(locator);
        ArrayList<String> sortList = new ArrayList<>(arrayList);
        /* Sorting in decreasing order*/
        arrayList.sort(Collections.reverseOrder());
        return arrayList.equals(sortList);
    }

    public void waitForElementDisappear(String locator, int waitingTime) {
        if (isElementDisplayed(locator)) {
            waitForInvisibility(locator, waitingTime);
        }
    }

    public void uploadToElement(String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        waitToElementPresence(locator);
        //element.clear();
        element.sendKeys(value);
    }

    public WebElement webElement(String locator, String value) {
        return driver.findElement(By.xpath(locator));
    }

    public void selectItemInMultipleDropdown(String parentLocator, String allItemLocator, String expectedItem) {
        element = driver.findElement(By.xpath(parentLocator));
        element.click();
        sleepInSecond(1);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
        elements = driver.findElements(By.xpath(allItemLocator));
        for (WebElement item : elements) {
            if (item.getText().contains(expectedItem)) {
                String Xpath = String.format("//span[text()=' %s ']//parent::mat-option", expectedItem);
                element = driver.findElement(By.xpath(Xpath));
                element.click();
                sleepInSecond(1);
                element.sendKeys(Keys.ESCAPE);
                break;
            }
        }

    }

    public void selectItemInSingleDropdown(String parentLocator, String allItemLocator, String expectedItem) {
        element = driver.findElement(By.xpath(parentLocator));
        element.click();
        sleepInSecond(1);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
        elements = driver.findElements(By.xpath(allItemLocator));
        for (WebElement item : elements) {
            if (item.getText().contains(expectedItem)) {
                String Xpath = String.format("//span[text()=' %s ']//parent::mat-option", expectedItem);
                element = driver.findElement(By.xpath(Xpath));
                element.click();
                sleepInSecond(1);
                break;
            }
        }

    }

    public void selectMultiItemInDropdown(String parentLocator, String allItemLocator, ArrayList<String> expectedListItem) {
        element = driver.findElement(By.xpath(parentLocator));
        element.click();
        sleepInSecond(1);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemLocator)));
        elements = driver.findElements(By.xpath(allItemLocator));
        for (WebElement item : elements) {
            for (String expectedItem : expectedListItem) {
                if (item.getText().contains(expectedItem)) {
                    String Xpath = String.format("//span[text()=' %s ']//parent::mat-option", expectedItem);
                    element = driver.findElement(By.xpath(Xpath));
                    element.click();
                    sleepInSecond(1);
                }
            }
            element.sendKeys(Keys.ESCAPE);
            break;
        }

    }

    public void inputDatePicker(String date, String locator) {
        //Parse from String to Date
        Date newDate = new Date();
        try {
            newDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = new SimpleDateFormat("MMM yyyy, dd").format(newDate);
        //Return data format
        String monthYear = date.substring(0, 8).toUpperCase();
        String year = date.substring(4, 8);
        String month = date.substring(0, 3).toUpperCase();
        String day = date.substring(10, 12);
        if (Objects.equals(String.valueOf(day.charAt(0)), "0")) {
            day = day.substring(1);
        }
        clickToElement(locator);
        //This is from date picker table
        WebElement calendarPicker = driver.findElement(By.xpath("//mat-calendar"));
        //Select Month Year
        List<WebElement> elementMonthYear = calendarPicker.findElements(By.tagName("span"));
        for (WebElement cell : elementMonthYear) {
            sleepInSecond(1);
            if (!(cell.getText().equals(monthYear))) {
                System.out.println(!(cell.getText().equals(monthYear)));
                sleepInSecond(1);
                cell.click();
            }
            break;
        }
        selectCellInTable("//mat-calendar", "td", year);
        selectCellInTable("//mat-calendar", "td", month);
        selectCellInTable("//mat-calendar", "td", day);
        //Wait for 4 Seconds to see date selected.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectCellInTable(String locator, String tagName, String contain) {
        WebElement calendarPicker = driver.findElement(By.xpath(locator));
        List<WebElement> elementData = calendarPicker.findElements(By.tagName("td"));
        for (WebElement cell : elementData) {
            //Select data
            if (cell.getText().equals(contain)) {
                sleepInSecond(1);
                cell.click();
                break;
            }
        }
    }

    public ArrayList<String> removeElementDuplicateInList(String locator) {
        ArrayList<String> listTextElement = getTextElementsInList(locator);
        ArrayList<String> newListTextElement = new ArrayList<>();
        for (String element : listTextElement) {
            // Check if element not exist in list, perform add element to list
            if (!newListTextElement.contains(element)) {
                newListTextElement.add(element);
            }
        }
        return newListTextElement;
    }

    public boolean isDateTimeSortedAscending(String locator) {
        ArrayList<String> arrayList = getTextElementsInList(locator);
        ArrayList<Date> dateList = listStringParseToListDate(arrayList);
        ArrayList<Date> sortDateList = new ArrayList<Date>(dateList);
        Collections.sort(sortDateList);
        return dateList.equals(sortDateList);
    }

    public ArrayList<Date> listStringParseToListDate(ArrayList<String> list) {
        ArrayList<Date> dateList = new ArrayList<Date>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");

        for (String dateString : list) {
            try {
                dateList.add(simpleDateFormat.parse(dateString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateList;
    }

    public boolean isDateTimeSortedDescending(String locator) {
        ArrayList<String> arrayList = getTextElementsInList(locator);
        ArrayList<Date> dateList = listStringParseToListDate(arrayList);
        ArrayList<Date> sortDateList = new ArrayList<>(dateList);
        /* Sorting in decreasing order*/
        sortDateList.sort(Collections.reverseOrder());
        return dateList.equals(sortDateList);
    }

    public boolean isNumberSortedDescending(String locator) {
        ArrayList<String> arrayList = getTextElementsInList(locator);
        ArrayList<BigInteger> newList = new ArrayList<BigInteger>();

        for (String element : arrayList) {
            // Check if element have text "Unlimited" in list, perform add new element to list
            if (element.equals("Unlimited")) {
                element = element.replace("Unlimited", "999999999999999");
            }
            element = element.replace(".", "");
            newList.add(BigInteger.valueOf(Long.parseLong(element)));
        }
        ArrayList<BigInteger> sortList = new ArrayList<>(newList);
        /* Sorting in decreasing order*/
        sortList.sort(Collections.reverseOrder());
        return newList.equals(sortList);
    }

    public boolean isNumberSortedAscending(String locator) {
        ArrayList<String> arrayList = getTextElementsInList(locator);
        ArrayList<BigInteger> newList = new ArrayList<BigInteger>();

        for (String element : arrayList) {
            // Check if element have text "Unlimited" in list, perform add new element to list
            if (element.equals("Unlimited")) {
                element = element.replace("Unlimited", "999999999999999");
            }
            element = element.replace(".", "");
            newList.add(BigInteger.valueOf(Long.parseLong(element)));
        }
        ArrayList<BigInteger> sortList = new ArrayList<>(newList);
        /* Sorting in decreasing order*/
        Collections.sort(sortList);
        return newList.equals(sortList);
    }

    public HashMap<String, String> getLanguageElements(String pageObject) {
        HashMap<String, String> languageElements;
        languageElements = TestConfig.existingElementLanguages.get("HomePageLocalize");
        return languageElements;
    }
}
