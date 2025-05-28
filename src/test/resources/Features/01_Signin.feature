Feature: User Login
Registered user should be able to login to access the al-arabia features

Background: Given User navigates to Login page

@signin @signin01 @validcredentials @regression @sanity @all
Scenario Outline: Login with valid credentials
When User enters valid email address
And Enter valid password
And Clicks on Sign in button
Then dashboard should display successfully
  
@signin @signin02 @invalidemailandpassword @smoke @regression @all
Scenario Outline: Login with invalid credentials
When User enters invalid email address
And Enter invalid password
And Clicks on Sign in button
Then User should get a invalid credential error message 

@signin @signin03 @invalidpassword @smoke @regression @all
Scenario Outline: Login with valid email address and invalid password
When User enters valid email address
And Enter invalid password Password
And Clicks on Sign in button
Then User should get a invalid credential error message 

@signin @signin04 @invalidemail @smoke @regression @all
Scenario Outline: Login with invalid email address and valid password
When User enters invalid email address
And Enter valid password
And Clicks on Sign in button
Then User should get a invalid credential error message 

@signin @signin05 @invalidpassword @smoke @regression @all
Scenario Outline: Login with valid email address and invalid password without special character
When User enters valid email address
And Enter invalid password without special character
And Clicks on Sign in button
Then User should get a invalid credential error message 

@signin @signin06 @invalidpassword @smoke @regression @all
Scenario Outline: Login with valid email address and invalid password without special character
When User enters valid email address
And Enter invalid password without uppercase char
And Clicks on Sign in button
Then User should get a invalid credential error message 

@signin @signin07 @invalidpassword @smoke @regression @all
Scenario Outline: Login with valid email address and invalid password with short character
When User enters valid email address
And Enter invalid password with short char
And Clicks on Sign in button
Then User should get a invalid credential error message 

@signin @signin08 @invalidpassword @smoke @regression @all
Scenario Outline: Login without email address and password
When Click email field
And Click password field
And Clicks on Sign in button
Then User should get required error message 



