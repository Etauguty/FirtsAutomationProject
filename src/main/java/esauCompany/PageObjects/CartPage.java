package esauCompany.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esauCompany.CommonComponent.CommonComponent;

public class CartPage extends CommonComponent{
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".btn_action")
	WebElement checkoutEle;
	@FindBy(css=".inventory_item_name")
	List<WebElement> cartProducts;
	
	public boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOutPage()
	{
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
}
