package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void login(String user, String password) {
        this.user.sendKeys(user);
        this.password.sendKeys(password);
        this.login.click();
    }
}