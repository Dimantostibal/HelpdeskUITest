package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TicketsPage extends AbstractPage {

    private Select queueSelect = new Select(driver.findElement(By.xpath("//select[@name='queue']")));
    @FindBy(id = "id_title")
    private WebElement title;
    @FindBy(id = "id_body")
    private WebElement description;
    private Select prioritySelect = new Select(driver.findElement(By.xpath("//select[@name='priority']")));
    @FindBy(id = "id_due_date")
    private WebElement data;
    @FindBy(id = "id_submitter_email")
    private WebElement email;
    @FindBy(xpath = "//button[@type]")
    private WebElement submitTicket;
    @FindBy(id = "userDropdown")
    private WebElement logIn;

    public TicketsPage(WebDriver driver) {
        super(driver);
    }

    public void createTicket() {
        queueSelect.selectByVisibleText("Some Product");
        title.sendKeys(getTicketTitle());
        description.sendKeys(getTicketDescription());
        prioritySelect.selectByValue(getTicketPrioritySelect());
        data.sendKeys(getTicketData());
        email.sendKeys(getEmailAddress());
        submitTicket.click();
        logIn.click();
    }
}