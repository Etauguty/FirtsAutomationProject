package esauCompany.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esauCompany.CommonComponent.CommonComponent;

public class VerificationPage extends CommonComponent{
	WebDriver driver;
	
	public VerificationPage(WebDriver driver)
	{
		super(driver);
		//initialization 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".inventory_item_name")
	WebElement product;
	@FindBy(css=".btn_action")
	WebElement submit;
	
	
	public String getProductName()
	{
		return product.getText();
	}
	
	public ConfirmationPage finishOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
