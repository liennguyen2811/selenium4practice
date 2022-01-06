package seleniumallstuff.devtoolstest;

import driver.CreateWebDriver;
import driver.LocalDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTestDev {
    private WebDriver driver = null;
    private CreateWebDriver createWebDriver = new CreateWebDriver();
    private Logger logger = LogManager.getLogger(BaseTestDev.class);
    public WebDriver threadLocalDriver = null;


    @BeforeTest(alwaysRun = true)
    @Parameters({"browserName"})
    public void beforeTest(@Optional(value = "chrome") String browserName) {
        driver = createWebDriver.getDriver(browserName);
        LocalDriverManager.setWebDriver(driver);
        LocalDriverManager.getDriver().manage().window().maximize();
        //LocalDriverManager.getDriver().get("https://www.zoomcar.com/bangalore/");
        LocalDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        if (LocalDriverManager.getDriver() != null) {
            LocalDriverManager.getDriver().quit();
            LocalDriverManager.removeWebDriver();
        }
    }
    public void sleepInSecond(long numberInSecond) {
        try {
            Thread.sleep(numberInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

