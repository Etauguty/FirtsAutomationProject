package esauCompany.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esauCompany.CommonComponent.CommonComponent;

public class LoginPage extends CommonComponent{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="user-name")
	WebElement userEmail;
	@FindBy(id="password")
	WebElement userPassword;
	@FindBy(id="login-button")
	WebElement submit;
	
	@FindBy(css=".error h3")
	WebElement errorMessage;
	
	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCatalog(driver);
	}
	
	public void goTo() {
		driver.get("https://www.saucedemo.com/");
	}
		
	public String errorMessageValidation() {
		 String errorMess = errorMessage.getText();
		 return errorMess;
	}
	
}

