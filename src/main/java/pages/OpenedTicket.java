package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OpenedTicket extends AbstractPage {
    @FindBy(xpath = "//tbody/*[2]//*[4]")
    private WebElement elemEmeil;
    @FindBy(xpath = "//p[following-sibling::p][3]")
    private WebElement elemDescription;

    public OpenedTicket(WebDriver driver) {
        super(driver);
    }

    public void compareTickerFields() {
        System.out.println(elemEmeil.getText());
        System.out.println(elemDescription.getText());

        // Проверяем, что нашли нужный тикер
        Assert.assertEquals(getEmailAddress(), elemEmeil.getText(), "Email не совпадает");
        Assert.assertEquals(getTicketDescription(), elemDescription.getText(), "Описание не совпадает");
    }
}
