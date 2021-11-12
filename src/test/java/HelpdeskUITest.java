import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AbstractPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private String email = "TestTestTicker@example.com";
    private String description = "Test описание тикера";
    private String title = "Test";

    private WebDriver driver;

    @BeforeClass
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

    @Test(priority = 1)
    public void createTicketTest() {
        driver.get(System.getProperty("site.url"));

        Select queueSelect = new Select(driver.findElement(By.xpath("//select[@name='queue']")));
        queueSelect.selectByVisibleText("Some Product");

        driver.findElement(By.id("id_title")).sendKeys(title);
        driver.findElement(By.id("id_body")).sendKeys(description);

        Select prioritySelect = new Select(driver.findElement(By.xpath("//select[@name='priority']")));
        prioritySelect.selectByValue("2");

        driver.findElement(By.id("id_due_date")).sendKeys("2021-11-11 00:00:00");
        driver.findElement(By.id("id_submitter_email")).sendKeys(email);
        driver.findElement(By.xpath("//button[@type]")).click();
        driver.findElement(By.id("userDropdown")).click();
    }

    //Чтение данных учетной записи пользователя из user.properties в System.properties
    @Test(priority = 2)
    public void loginUserTest() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
        loginPage.login(System.getProperty("user"), System.getProperty("pass"));
    }

    @Test(priority = 3)
    public void searchTickerTest() {
        driver.findElement(By.id("search_query")).sendKeys(title);
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        //После поиска собираем у всех найденых тикеров email'ы
        List<WebElement> tickersEmail = driver.findElements(By.xpath("//td[following-sibling::td][6]"));
        //Складываем ссылки для открытия со всех тикеров
        List<WebElement> tickers = driver.findElements(By.xpath("//div[contains(@class,'tickettitle')]/*[1]"));
        //Проверка до первого совпавшего тикера из списка по полю email
        for (int i = 0; i < tickersEmail.size(); ) {
            String webElement;
            webElement = tickersEmail.get(i).getText();
            if (!webElement.contains(email)) {
                i++;
            } else {
                tickers.get(i).click();
                break;
            }
        }
    }

    @Test(priority = 4)
    public void compareTickerFields() {
        // Создаем для проверки на соответсвие
        WebElement elemEmeil = driver.findElement(By.xpath("//tbody/*[2]//*[4]"));
        WebElement elemDescription = driver.findElement(By.xpath("//p[following-sibling::p][3]"));

        System.out.println(elemEmeil.getText());
        System.out.println(elemDescription.getText());

        // Проверяем, что нашли нужный тикер
        Assert.assertEquals(email, elemEmeil.getText(), "Email не совпадает");
        Assert.assertEquals(description, elemDescription.getText(), "Описание не совпадает");

        //Закрываем текущее окно браузера
        driver.close();
    }
}
