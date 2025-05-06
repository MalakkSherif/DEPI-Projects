package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ScreenShot {

    public static void takeScreenShot(WebDriver driver , String testCaseName)
    {
        Random rand = new Random();
        int randomNumber = rand.nextInt(10000000);

        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = "ScreenShots/"+testCaseName+randomNumber+".png";
        try {
            FileUtils.copyFile(screenShotFile , new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void takeScreenShot(WebDriver driver , String testCaseName , String browserName)
    {
        Random rand = new Random();
        int randomNumber = rand.nextInt(10000000);

        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = "ScreenShots/"+testCaseName+"_"+browserName+"_"+randomNumber+".png";
        try {
            FileUtils.copyFile(screenShotFile , new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

