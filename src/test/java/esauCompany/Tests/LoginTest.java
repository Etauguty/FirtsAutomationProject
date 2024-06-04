package esauCompany.Tests;

import org.testng.annotations.Test;

import esauCompany.TestComponents.BaseTest;

public class LoginTest extends BaseTest{
	@Test(groups="Purchase")
	public void login() {
		loginPage.loginApplication("standard_user", "secret_sauce");
	}
}
