package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tools.ScreenshotHelper;

import java.util.List;

public class MainPage extends AbstractPage {
    @FindBy(id = "search_query")
    private WebElement searchQuery;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonSearch;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Поиск созданного тикета")
    public void searchTickerTest() {
        searchQuery.sendKeys(getTicketTitle());
        buttonSearch.click();
        //После поиска собираем у всех найденых тикетов email'ы
        List<WebElement> ticketsEmail = driver.findElements(By.xpath("//td[following-sibling::td][6]"));
        //Складываем ссылки для открытия со всех тикетов
        List<WebElement> tickets = driver.findElements(By.xpath("//div[contains(@class,'tickettitle')]/*[1]"));

        for (int i = 0; i < ticketsEmail.size(); ) {
            String webElement = ticketsEmail.get(i).getText();
            if (!webElement.contains(getEmailAddress())) {
                i++;
            } else {
                tickets.get(i).click();
                break;
            }
        }
        WebElement tableOfTickets = driver.findElement(By.xpath("//div[@class='table-responsive']"));
        ScreenshotHelper.saveScreenshot(tableOfTickets);
    }
}