package allstuffs.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.io.File;

public class Fixtures {
    public static class SetUp {

        public static WebDriver initBrowser(String applicationURL) {
            BrowserControl bc = new BrowserControl();
            WebDriver browser = bc.newBrowser();
            maximizeWindow(browser);
            browser.get(applicationURL);
            return browser;
        }
    }

    public static class TearDown {

        public static void close(WebDriver browser) {
            if (browser != null) {
                browser.quit();
            }
        }
    }

    private static void maximizeWindow(WebDriver browser) {
        try {
            browser.manage().window().maximize();
        } catch (WebDriverException ex) {
            try {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenResolution = new Dimension((int)
                        toolkit.getScreenSize().getWidth(), (int)
                        toolkit.getScreenSize().getHeight());

                browser.manage().window().setSize(screenResolution);
            } catch (WebDriverException e) {
                ((JavascriptExecutor) browser).executeScript("window.moveTo(0,0); window.resizeTo(screen.width, screen.height);");
            }
        }
    }
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
