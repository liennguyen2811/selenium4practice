package seleniumallstuff.notetolearn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SVGMoving {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.google.com/search?q=covid+case+in+vietnam&ei=z4KgYeS7MdDhz7sPuZWhwA0&oq=Covid+case+in+&gs_lcp=Cgdnd3Mtd2l6EAEYAjIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQ6BwgAEEcQsAM6BwgAELADEEM6BwgAELEDEEM6BAgAEEM6BwgAEMkDEEM6CAgAEIAEELEDOgsIABCABBCxAxCDAToKCAAQsQMQgwEQQzoICAAQsQMQkQI6CwgAELEDEIMBEJECOgUIABCRAjoLCAAQgAQQsQMQyQM6BQgAEJIDSgUIPBIBMUoECEEYAEoECEYYAFCnBViuMmCfPWgBcAJ4AoABxQWIAaYjkgEOMC4xNC40LjEuMC4xLjGYAQCgAQHIAQrAAQE&sclient=gws-wiz");

        List<WebElement> graphList = webDriver.findElements(By.xpath("//*[local-name()='svg' and @class='uch-psvg']//*[name()='rect']"));
        Actions act = new Actions(webDriver);
        String numberDoc;
        for (WebElement e : graphList) {
            act.moveToElement(e).perform();
            numberDoc = webDriver.findElement(By.xpath("//div[@class='ExnoTd']")).getText();
            System.out.println(numberDoc);

        }
        webDriver.quit();
    }
}
