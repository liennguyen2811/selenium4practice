package seleniumallstuff.devtoolstest;

import driver.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumallstuff.BaseTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GeoMockingTest extends BaseTestDev {
    private WebDriver localWebDriver;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = LocalDriverManager.getDriver();
        System.out.println("if come here threadLocalDriver--" + localWebDriver);
    }

    @Test
    public void mockLocation(){
       // DevTools devTools = ((ChromeDriver)localWebDriver).getDevTools();
        ChromeDriver chromeDriver = new ChromeDriver();
        DevTools devTools = chromeDriver.getDevTools();
        devTools.createSession();
        Map<String,Object> coordinates = new HashMap<>();
        coordinates.put("latitude",39.904202);
        coordinates.put("longitude", 116.407394);
        coordinates.put("accuracy", 1);
//        devTools.send(Emulation.setGeolocationOverride(
//                Optional.of(48.8584),
//                Optional.of(2.2945),
//                Optional.of(100)));

        chromeDriver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
       // System.out.println(coordinates.toString());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chromeDriver.get("https://www.zoomcar.com");


        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chromeDriver.get("https://www.google.com/");

//        chromeDriver.findElement(By.name("q")).sendKeys("nextflix", Keys.ENTER);
//        chromeDriver.findElements(By.cssSelector(".LC20lb")).get(0).click();
//        String title = localWebDriver.findElement(By.cssSelector(".our_story_card_title")).getText();
//        System.out.println(title);

    }
}
