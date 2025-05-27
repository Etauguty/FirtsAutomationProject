package esauCompany.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import esauCompany.TestComponents.BaseTest;

public class LoginTest extends BaseTest{

	@Test(groups="Login")
	public void positiveLogin() {
		loginPage.loginApplication("standard_user", "secret_sauce");
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contentEquals("https://www.saucedemo.com/inventory.html"));
	}
	@Test(groups="Login")
	public void negativeLogin() {
		loginPage.loginApplication("locked_out_user", "secret_sauce");
		Assert.assertEquals(loginPage.errorMessageValidation(), "Epic sadface: Sorry, this user has been locked out.") ;
	}
}
