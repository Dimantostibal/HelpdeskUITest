import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.AbstractPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;

    @Before
    public void setup() throws IOException {
        // Читаем конфигурационный файл в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        // Создание экземпляра драйвера
        driver = new ChromeDriver();
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        AbstractPage.setDriver(driver);
    }

    @Test
    public void createTicketTest() throws IOException {
        String email = "TestTestTicker@example.com";
        String description = "Test описание тикера";

        driver.get(System.getProperty("site.url"));

        Select queueSelect = new Select(driver.findElement(By.xpath("//select[@name='queue']")));
        queueSelect.selectByVisibleText("Some Product");

        driver.findElement(By.id("id_title")).sendKeys("Test");
        driver.findElement(By.id("id_body")).sendKeys(description);

        Select prioritySelect = new Select(driver.findElement(By.xpath("//select[@name='priority']")));
        prioritySelect.selectByValue("2");

        driver.findElement(By.id("id_due_date")).sendKeys("2021-11-11 00:00:00");
        driver.findElement(By.id("id_submitter_email")).sendKeys(email);
        driver.findElement(By.xpath("//button[@type]")).click();
        driver.findElement(By.id("userDropdown")).click();

        // todo: чтение данных учетной записи пользователя из user.properties в System.properties
        LoginPage loginPage = new LoginPage(driver);
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        loginPage.login(System.getProperty("user"), System.getProperty("pass"));

        driver.findElement(By.id("search_query")).sendKeys("TestTest");
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        List<WebElement> elements = driver.findElements(By.xpath("//td[following-sibling::td][6]"));
        List<WebElement> el = driver.findElements(By.xpath("//div[contains(@class,'tickettitle')]/*[1]"));

        for (int i = 0; i < elements.size();) {
            String webElement;
            webElement = elements.get(i).getText();
            if (!webElement.contains(email)){
                i++;
            } else {
                el.get(i).click();
                break;
            }
        }
        // Создаем для проверки на соответсвие
        WebElement elemEmeil = driver.findElement(By.xpath("//tbody/*[2]//*[4]"));
        WebElement elemDescription = driver.findElement(By.xpath("//p[following-sibling::p][3]"));

        System.out.println(elemEmeil.getText());
        System.out.println(elemDescription.getText());

        // Проверяем, что нашли нужный тикер
        Assert.assertEquals("Email не совпадает", email, elemEmeil.getText());
        Assert.assertEquals("Описание не совпадает", description, elemDescription.getText());


        //Закрываем текущее окно браузера
        driver.close();
    }
}
