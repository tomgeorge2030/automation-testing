package Stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import Hooks.Testhooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.Signinpage;
import resources.Base;

public class Signin extends Base {

    private Logger LOGGER = LogManager.getLogger(Signin.class); 
    private WebDriver driver;
    private Signinpage signinpage;

    public Signin(Testhooks testhooks) throws Exception {
        this.driver = testhooks.getDriver();
        this.signinpage = new Signinpage(driver);
        LOGGER = LogManager.getLogger(Signin.class.getName());
    }

    @Given("User navigates to Login page")
    public void user_navigates_to_login_page() throws Exception {
        LOGGER.info("User navigates to the login page");
    }

    @When("^User enters valid email address$")
    public void user_enters_valid_email_address() {
        signinpage.enterValidEmail();
    }

    @When("^Enter invalid password without special character$")
    public void enter_invalid_password_without_special_character() {
        signinpage.enterInvalidPasswordWithoutSpecialCharacter();
    }

    @And("^Enter invalid password with short char$")
    public void enter_invalid_password_with_short_char() {
        signinpage.enterInvalidPasswordShortCharacter();
    }

    @When("^Enter valid password$")
    public void enter_valid_password() {
        signinpage.enterValidPassword();
    }

    @When("Clicks on Sign in button")
    public void clicks_on_sign_in_button() {
        signinpage.clickSignInButton();
    }

//    @Then("User should login successfully")
//    public void user_should_login_successfully() {
//        signinpage.checkDashboardDisplayed();
//    }
//
    @Then("dashboard should display successfully")
    public void dashboard_should_display_successfully() {
        signinpage.checkDashboardDisplayed();
    }

    @When("^User enters invalid email address$")
    public void user_enters_invalid_email_address() {
        signinpage.enterInvalidEmail();
    } 

    @When("^Enter invalid password$")
    public void enter_invalid_password() {
        signinpage.enterInvalidPassword();
    }
  
    @When("^Enter invalid password Password$")
    public void enter_invalid_password_password() {
        signinpage.enterPasswordWithoutNumber();
    } 
 
    @Then("User should get a invalid credential error message")
    public void User_should_get_a_invalid_credential_error_message() {
        signinpage.checkUserPasswordErrorDisplayed();
    }


    @And("Enter invalid password without uppercase char")
    public void enter_invalid_password_without_uppercase_char() {
        signinpage.enterInvalidPasswordWithoutUpperCharacter();
    }


    @Then("User should get required error message")
    public void user_should_get_required_error_message() {
        signinpage.checkEmailRequiredErrorDisplayed();
    }

    @And("Click password field")
    public void click_password_field() {
        signinpage.clickPasswordField();
    }

    @When("Click email field")
    public void click_email_field() {
        signinpage.clickEmailField();
    }
}

