package seleniumallstuff.notetolearn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FindBrokenLink {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.amazon.com/");

        List<WebElement> links = webDriver.findElements(By.tagName("a"));
        System.out.println("Number of link is  " + links.size());

        List<String> urlList = new ArrayList<String>();
        String url;
        for(WebElement e: links){
            url = e.getAttribute("href");
            urlList.add(url);
            // checkBrokenLinks(url);
        }
        long stTime = System.currentTimeMillis();
        //urlList.stream().forEach(e->checkBrokenLinks(e)); //  it take more time
        urlList.parallelStream().forEach(e->checkBrokenLinks(e));
        long endTime = System.currentTimeMillis();
        System.out.println("total time is  " + (endTime-stTime));
        webDriver.quit();

    }
    public static void checkBrokenLinks(String linkUrl){

        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() >= 400){
                System.out.println(linkUrl + "----->" + httpURLConnection.getResponseMessage() +" is broken ");
            }else {
                System.out.println(linkUrl + "----->" + httpURLConnection.getResponseMessage());
            }


        }catch (Exception e){

        }
    }
}
