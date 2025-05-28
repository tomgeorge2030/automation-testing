package Runner;
	
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features", glue = {"Hooks","Stepdefinitions"},
tags = "@signin or @forgotpassword or @members or @billboards or @profile", plugin = {"pretty",
		"html:target/CucumberReports/CucumberReport.html",
		"json:target/CucumberReports/CucumberReport.json",
		"junit:target/CucumberReports/CucumberReport.xml",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, publish = true, dryRun = false, monochrome = true)

public class TestNG_Runner extends AbstractTestNGCucumberTests {
public static void main(String[] args) {
    String html = "<html><body><div id='content'>Hello World!</div></body></html>";
    
    // Parse the HTML string into a Jsoup Document 
    Document document = Jsoup.parse(html);
    
    // Use selectFirst to find the first element matching a CSS selector
    Element element = document.selectFirst("#content");
    
    if (element != null) {
        // Print the text content of the selected element
        System.out.println("Content: " + element.text());
    } else {
        System.out.println("Element not found!");
    }
}
}
