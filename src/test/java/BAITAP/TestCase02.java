/*

Test Steps:

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/
package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
@Test
public class TestCase02 {

    public static void testCase02(String[] args) {

        WebDriver driver = driverFactory.getChromeDriver();

//        Step 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

//        2. Click on  MOBILE  menu
        driver.findElement(By.linkText("MOBILE")).click();


//      3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
        String XPerialPrice = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']")).getText();

//        4. Click on Sony Xperia mobile
        driver.findElement(By.id("product-collection-image-1")).click();

//        5. Read the Sony Xperia mobile from detail page.
        String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();

//        6. Compare Product value in list and details page should be equal ($100).
        AssertJUnit.assertEquals(XPerialPrice, detailPrice);
        if(Objects.equals(detailPrice, XPerialPrice)) {
            System.out.println("The price is " + detailPrice);
        }
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
        try {
            FileHandler.copy(screenshot, f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
