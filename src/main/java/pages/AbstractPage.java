package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// Элементы общие для всех страниц
public abstract class AbstractPage {

    protected static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}