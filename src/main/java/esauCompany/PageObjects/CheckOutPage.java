package esauCompany.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esauCompany.CommonComponent.CommonComponent;

public class CheckOutPage extends CommonComponent{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="first-name")
	WebElement nameField;
	@FindBy(id="last-name")
	WebElement lastnameField;
	@FindBy(id="postal-code")
	WebElement zipcodeField;
	@FindBy(css=".submit-button")
	WebElement submit;
	
	public void fillData(String name, String lastName, String zipcode) {
		nameField.sendKeys(name);
		lastnameField.sendKeys(lastName);
		zipcodeField.sendKeys(zipcode);		
	}
	
	public VerificationPage submitOrder()
	{
		submit.click();
		return new VerificationPage(driver);
	}
	
}
