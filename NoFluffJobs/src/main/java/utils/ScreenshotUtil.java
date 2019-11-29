package utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ScreenshotUtil {

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public static InputStream takeScreenshot(WebDriver driver)throws IOException {
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        return FileUtils.openInputStream(sourceFile);
    }
}
