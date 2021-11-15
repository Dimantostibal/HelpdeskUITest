import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {
        // Читаем конфигурационный файл в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        // Чтение данных учетной записи пользователя из user.properties в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
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
    @Story("Тест Helpdesk")
    @Description(value = "Тест проверяет создание тикера в Helpdesk")
    @Owner(value = "Еськов Дмитрий")
    public void createTicketTest() throws IOException {
        driver.get(System.getProperty("site.url"));

        //Заполнение полей тикета
        TicketsPage ticketsPage = new TicketsPage(driver);
        ticketsPage.createTicket();

        //Авторизация
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(System.getProperty("user"), System.getProperty("pass"));

        //Поиск созданного тикета
        MainPage mainPage = new MainPage(driver);
        mainPage.searchTickerTest();

        OpenedTicket openedTicket = new OpenedTicket(driver);
        openedTicket.compareTickerFields();
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
