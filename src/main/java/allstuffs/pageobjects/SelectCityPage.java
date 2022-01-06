package allstuffs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonActions;

import java.time.Duration;


public class SelectCityPage {
    private WebDriver localWebDriver;
    WebDriverWait wait;
    CommonActions commonActions;

    public SelectCityPage(WebDriver webDriver){
        localWebDriver = webDriver;
        commonActions = new CommonActions(localWebDriver);
        wait = new WebDriverWait(localWebDriver, Duration.ofSeconds(50));
        PageFactory.initElements(localWebDriver, this);
    }

    //<div class="name">Bangalore</div> cairoCity
    @FindBy(xpath = "//span[contains(text(),'Bangalore')]")
    private WebElement bangaloreCity;


    @FindBy(xpath = "//span[contains(text(),'Egypt')]")
    private WebElement egyptCity;

    @FindBy(xpath = "//span[contains(text(),'Vietnam')]")
    private WebElement vietNameCity;

    @FindBy(xpath = "(//span[contains(text(),'Cairo')])[1]")
    private WebElement  cairoCity;

    @FindBy(xpath = "(//span[contains(text(),'English')])[1]")
    private WebElement  languageName;

    @FindBy(xpath = "(//div[@class='multiselect__select'])[1]")
    private WebElement  selectCityFrom;

    @FindBy(xpath = "(//div[@class='multiselect__select'])[2]")
    private WebElement  selectCityTo;

    @FindBy(xpath = "(//div[@class='multiselect__select'])[3]")
    private WebElement  selectLanguage;

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmButton;
    

    //<a class="search" href="/bangalore/search" title="Start your wonderful journey">Start your wonderful journey</a>
    @FindBy(className = "search")
    private WebElement startYourJourney;

    public SelectCityPage clickBangaloreCity(){
       commonActions.clickElement(egyptCity);
       return this;
    }

    public void clickStartYourJouney(){

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        commonActions.clickElement(selectCityFrom);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("if come here");

        commonActions.clickElement(vietNameCity);


        System.out.println("if come here");
        commonActions.clickElement(selectCityFrom);
        commonActions.clickElement(egyptCity);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        commonActions.clickElement(selectCityTo);
        commonActions.clickElement(cairoCity);

        commonActions.clickElement(selectLanguage);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        commonActions.clickElement(languageName);
        commonActions.clickElement(confirmButton);
        //commonActions.clickElement(startYourJourney);
    }
}
