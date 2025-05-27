package esauCompany.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.util.Assert;

import esauCompany.CommonComponent.CommonComponent;

import java.util.ArrayList;
import java.util.Collections;
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

	//Section to test the sort Name function
	@FindBy(className="product_sort_container")
	WebElement sortButton;
	@FindBy(className="inventory_item_name")
	List<WebElement> nameElements;

	public List<String> testSortName(String sortSelection) {
        Select sort = new Select(sortButton);
        sort.selectByVisibleText(sortSelection);

        List<String> actualNames = new ArrayList<>();
        for (WebElement e : nameElements) {
            actualNames.add(e.getText());
        }

		return actualNames;
        
    }

	//Section to test the sort Price function
	@FindBy(className="inventory_item_price")
	List<WebElement> priceElements;

	 public List<Double> testSortPrice(String sortSelection) {
        Select sort = new Select(sortButton);
        sort.selectByVisibleText(sortSelection);

        List<Double> actualPrices = new ArrayList<>();
        for (WebElement e : priceElements) {
            actualPrices.add(Double.parseDouble(e.getText().replace("$", "")));
        }

        return actualPrices;
    }

}
