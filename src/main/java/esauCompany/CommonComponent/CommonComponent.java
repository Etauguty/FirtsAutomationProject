package esauCompany.CommonComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import esauCompany.PageObjects.CartPage;


public class CommonComponent {
	WebDriver driver;
	
	public CommonComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//*[@id=\"shopping_cart_container\"]/a")
	WebElement cartHeader;
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		return new CartPage(driver);
	}
	
	
	
}
