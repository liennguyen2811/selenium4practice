package seleniumallstuff.notetolearn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CavasTesting {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.online-calculator.com/full-screen-calculator/");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("fullframe")));
        // https://www.youtube.com/watch?v=NebFQ31NFtE to learn
        // get center x y, then divide x : 3 Y:4, then get area of button by multiply relatively

        WebElement canv = webDriver.findElement(By.xpath("//canvas[@id='canvas']"));
        System.out.println("size " + canv.getSize());
        int getWidthCenter = canv.getSize().getWidth()/2;
        int getHighCenter = canv.getSize().getHeight()/2;
        System.out.println("getWidthCenter + getHighCenter " + getWidthCenter + "  " + getHighCenter);
        int fourButtonX = - getWidthCenter/3*2;
        int fourButtonY = getHighCenter/4;
        int fiveButtonX = - getWidthCenter/3;
        int fiveButtonY = getHighCenter/4;
        int sixButtonX = 0;
        int sixButtonY = getHighCenter/4;
        int xButtonX = getWidthCenter/3;
        int yButtonY = getHighCenter/4;
        int percentButtonX = getWidthCenter/3*2;
        int percentButtonY = getHighCenter/4;
        int nineButtonX = 0;
        int nineButtonY = - getHighCenter/4;
        int threeButtonX = 0;
        int threeButtonY = getHighCenter/4*2;
        int plusMinusButtonX = 0;
        int plusMinusButtonY = getHighCenter/4*3;

        new Actions(webDriver).moveToElement(canv, 0, 0).moveByOffset(plusMinusButtonX,plusMinusButtonY).click().build().perform();
        //new Actions(webDriver).moveToElement(canv, 0, 0).moveByOffset(threeButtonX,threeButtonY).click().build().perform();

    }

}
