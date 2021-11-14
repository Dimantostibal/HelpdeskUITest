package tools;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AbstractPage;
import ru.yandex.qatools.ashot.AShot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScreenshotHelper extends AbstractPage {

    public ScreenshotHelper(WebDriver driver) {
        super(driver);
    }

    @Attachment(value = "Скриншот")
    public static byte[] saveScreenshot(WebElement element) {
        BufferedImage screenshot = new AShot().takeScreenshot(driver, element).getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteOfScreenshot = baos.toByteArray();
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteOfScreenshot;
    }
}
