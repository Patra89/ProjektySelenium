package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinksFinder {

    public void findAllBrokenLinks(WebDriver driver) {
        String url;
        HttpURLConnection huc;
        int respCode;
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links) {
            url = link.getAttribute("href");

            if (url == null || url.isEmpty()) {
                //URL is either not configured for anchor tag or it is empty
                continue;
            }
            if (!url.startsWith(driver.getCurrentUrl())) {
                //URL belongs to another domain - skipping it.
                continue;
            }
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    Assert.fail(url + "is a broken link, because " + huc.getResponseMessage());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
