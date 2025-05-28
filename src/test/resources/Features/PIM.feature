Feature: User Login
Registered user should be able to login to access the al-arabia features

@PIM_01
Scenario Outline: To verify Navigate to the PIM module
When User enters valid email address
And Enter valid password
And Clicks on Sign in button
And User mouse hovers over PIM and clicks on it

@PIM_02
Scenario Outline: To verify Add Employees one by one
When User enters valid email address
And Enter valid password
And Clicks on Sign in button
And User mouse hovers over PIM and clicks on it
And User Onclicks Add button
And User Add Employee
And User mouse hovers over PIM and clicks on it
And User Onclicks Add button
And User Add Employee
And User mouse hovers over PIM and clicks on it
And User Onclicks Add button
And User Add Employee
And User mouse hovers over PIM and clicks on it
And User scroll down and verify the added employees
Then Successfully added three employees