package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static implementations.NotepadImplementation.driver;

public class TakeScreenshot {
    public static void screenshot(String fileName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + ConfigReader.getProperty("screenshotsPath") + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
