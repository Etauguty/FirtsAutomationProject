package esauCompany.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import esauCompany.PageObjects.ProductCatalog;
import esauCompany.PageObjects.CartPage;
import esauCompany.PageObjects.CheckOutPage;
import esauCompany.PageObjects.ConfirmationPage;
import esauCompany.TestComponents.BaseTest;
import esauCompany.PageObjects.VerificationPage;


public class ShoppingTest extends BaseTest{

	@Test(dataProvider="getData")
	public void shopping(HashMap<String,String> input) {
		//Login to the page
		ProductCatalog productCatalog = loginPage.loginApplication(input.get("email"), input.get("password"));
			 
		productCatalog.getProductsList();
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOutPage();
		
		checkOutPage.fillData(input.get("name"), input.get("lastName"), input.get("zipcode"));
		VerificationPage verificationPage = checkOutPage.submitOrder();
		
		String product = verificationPage.getProductName();
		Assert.assertTrue(product.equalsIgnoreCase(input.get("productName")));
		ConfirmationPage confirmationPage = verificationPage.finishOrder();
		
		String confMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confMessage.equalsIgnoreCase(input.get("confirmationMessage")));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\esauCompany\\Resources\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
