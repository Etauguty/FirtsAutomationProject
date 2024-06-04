package esauCompany.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esauCompany.CommonComponent.CommonComponent;

public class ConfirmationPage extends CommonComponent{
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		//initialization 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".complete-header")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
}
