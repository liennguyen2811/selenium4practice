package seleniumallstuff.devtoolstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;
import org.openqa.selenium.devtools.v96.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class mockUrl {
    private WebDriver driver;
    private DevTools devTools;

    @BeforeMethod
    public void initialiseClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void userAgentTest() throws InterruptedException {
        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));
        devTools.addListener(Fetch.requestPaused(),request -> {

            if(request.getRequest().getUrl().contains("shetty")){
                String mockUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
                System.out.println(mockUrl);
                devTools.send(Fetch.continueRequest(request.getRequestId(),Optional.of(mockUrl),Optional.of(request.getRequest().getMethod()),Optional.empty(), Optional.empty()));
            }
            else {

                devTools.send(Fetch.continueRequest(request.getRequestId(),Optional.of(request.getRequest().getUrl()),Optional.of(request.getRequest().getMethod()),Optional.empty(), Optional.empty()));
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        Thread.sleep(300);
        driver.findElement(By.cssSelector("button[type='button'][routerlink='/library']")).click();
        Thread.sleep(500);
        System.out.println(driver.findElement(By.cssSelector("p")).getText());


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
