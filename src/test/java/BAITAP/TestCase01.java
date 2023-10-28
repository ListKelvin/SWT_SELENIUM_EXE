/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/

package BAITAP;

import driver.driverFactory;
import element.ElementController;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
@Test
public class TestCase01 {


    public static void testCase01(String[] args) {
        WebDriver driver = driverFactory.getChromeDriver();

        //Test Steps

        //Step 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");
        By titleSelector = By.xpath("//h2[1]");
        By mobileSelectLink = By.linkText("MOBILE");


        try {
            WebElement title = driver.findElement(titleSelector);
            //Step 2. Verify Title of the page
            AssertJUnit.assertEquals("THIS IS DEMO SITE FOR   ", title.getText());
            //debug purpose only
            Thread.sleep(2000);
            //Step 3. Click on -> MOBILE -> menu
            driver.findElement(mobileSelectLink).click();
            //debug purpose only
            Thread.sleep(2000);
            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"] "))).selectByVisibleText("Name");
            //Step 5. Verify all products are sorted by name
            String dirPath = "screenshots";
            File dir = new File(dirPath);
            //check dir exists
            if (!dir.exists()) {
                dir.mkdir();
            }
            //casting the driver object to the TakesScreenshot interface
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            // capture a screenshot and store it in a File object.
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
            File f = new File(dirPath + "/" + screenshot.getName());
            //copy the screenshot file to the destination specified by the f File object.
            FileHandler.copy(screenshot, f);
        } catch (Error e) {
            System.out.println("error: "+ e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //7. Quit browser session
        driver.quit();
    }




}
