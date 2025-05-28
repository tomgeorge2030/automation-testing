package Stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import Hooks.Testhooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.PIMpage;
import pageobjects.Signinpage;
import resources.Base;

public class PIM extends Base {

    private static Logger LOGGER = LogManager.getLogger(PIM.class); 
    private WebDriver driver;
    private PIMpage pimpage;
    private Signinpage signinpage;

    public PIM(Testhooks testhooks) throws Exception {
        this.driver = testhooks.getDriver();
        this.pimpage = new PIMpage(driver);
        this.signinpage = new Signinpage(driver);
        LOGGER = LogManager.getLogger(PIM.class.getName());
    }


    @And("User mouse hovers over PIM and clicks on it")
    public void User_clicks_the_members_module() throws Exception {
        pimpage.PIMClick();
    }

    @And("User Onclicks Add button")
    public void User_Onclicks_Add_button() throws Exception {
        pimpage.Click_Add_Button();
    } 
    @And("User Add Employee")
    public void User_Add_Employee() throws Exception {
        pimpage.Add_employee();
    } 
    @And("User scroll down and verify the added employees")
    public void User_scroll_down_and_verify_the_added_employees() throws Exception {
        pimpage.Verify_employees();
    } 
    
    @Then("Successfully added three employees")
    public void Successfully_added_three_employees() throws Exception {
    } 
}

