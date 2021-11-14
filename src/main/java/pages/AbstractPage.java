package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

// Элементы общие для всех страниц
public abstract class AbstractPage {

    private String emailAddress = "TestTestTicker@example.com";
    private String ticketDescription = "Test описание тикера";
    private String ticketTitle = "Test";
    private String ticketPrioritySelect = "2";
    private String ticketData = "2021-11-11 00:00:00";
    protected static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketPrioritySelect() {
        return ticketPrioritySelect;
    }

    public void setTicketPrioritySelect(String ticketPrioritySelect) {
        this.ticketPrioritySelect = ticketPrioritySelect;
    }

    public String getTicketData() {
        return ticketData;
    }

    public void setTicketData(String ticketData) {
        this.ticketData = ticketData;
    }
}