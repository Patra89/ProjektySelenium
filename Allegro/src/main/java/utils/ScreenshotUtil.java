package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String fileName)throws IOException {
        String path = "src\\reports\\screenshots\\"+fileName+".png";
        File destination = new File(path);
        destination.createNewFile();
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(sourceFile, destination);
        return destination.getAbsolutePath();
    }
}
