package esauCompany.Tests;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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


public class ProductPageTest extends BaseTest{

	@Test(dataProvider="getData")
	public void SortNameTestZToA(HashMap<String,String> input) {
		//Login to the page
		ProductCatalog productCatalog = loginPage.loginApplication(input.get("email"), input.get("password"));
			 
		List<String> actualNames = productCatalog.testSortName("Name (Z to A)");
		List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames, Collections.reverseOrder());

        Assert.assertEquals(actualNames, expectedNames, "Items not sorted Z to A.");
	}

	@Test(dataProvider="getData")
	public void SortNameTestAToZ(HashMap<String,String> input) {
		//Login to the page
		ProductCatalog productCatalog = loginPage.loginApplication(input.get("email"), input.get("password"));
			 
		List<String> actualNames = productCatalog.testSortName("Name (A to Z)");
		List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        Assert.assertEquals(actualNames, expectedNames, "Items not sorted A to Z.");
	}

	@Test(dataProvider="getData")
	public void SortPriceLowToHigh(HashMap<String,String> input) {
		//Login to the page
		ProductCatalog productCatalog = loginPage.loginApplication(input.get("email"), input.get("password"));
			 
		List<Double> actualPrices = productCatalog.testSortPrice("Price (low to high)");
		List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices, "Prices not sorted low to high.");
	}

	@Test(dataProvider="getData")
	public void SortPriceHighToLow(HashMap<String,String> input) {
		//Login to the page
		ProductCatalog productCatalog = loginPage.loginApplication(input.get("email"), input.get("password"));
			 
		List<Double> actualPrices = productCatalog.testSortPrice("Price (high to low)");
		List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());

        Assert.assertEquals(actualPrices, expectedPrices, "Prices not sorted high to low.");
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\esauCompany\\Resources\\PurchaseOrderSmoke.json");
		return new Object[][] {{data.get(0)}};
	}
}
