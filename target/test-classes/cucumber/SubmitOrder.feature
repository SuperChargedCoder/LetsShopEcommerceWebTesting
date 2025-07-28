Feature: Lets Shop purchase product 

Background: 
	Given I landed on Ecommerce Page 
	
@Regression 
Scenario Outline: scenario description 
	Given I logged in with "<email>" and "<password>" 
	When add the "<productName>" to cart 
	And Checkout "<productName>" and submit the order 
	Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage 
	
	Examples: 
		| email | password | productName |
		| anshika@gmail.com | Iamking@000 | ADIDAS ORIGINAL |
		