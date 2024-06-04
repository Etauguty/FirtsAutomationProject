package esauCompany.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import esauCompany.CommonComponent.CommonComponent;

import java.util.List;

public class ProductCatalog extends CommonComponent{

	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".inventory_item")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".inventory_item");
	By addToCartBtn = By.cssSelector(".btn");
	
	public List<WebElement> getProductsList(){
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream().filter(product-> 
		product.findElement(By.cssSelector(".inventory_item_name ")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartBtn).click();
	}
}
