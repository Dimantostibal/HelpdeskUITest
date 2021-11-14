package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tools.ScreenshotHelper;

public class LoginPage extends AbstractPage {
    // Обычный поиск элемента
    private WebElement user = driver.findElement(By.id("username"));
    // Поиск элемента через аннотацию
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement login;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Авторизация")
    public void login(String user, String password) {
        this.user.sendKeys(user);
        this.password.sendKeys(password);
        this.login.click();
        WebElement loginElem = driver.findElement(By.xpath("//nav"));
        ScreenshotHelper.saveScreenshot(loginElem);
    }
}