Feature: Error validation 

@ErrorValidation 
Scenario Outline: Title of your scenario outline 
	Given I landed on Ecommerce Page 
	When I logged in with "<email>" and "<password>" 
	Then "Incorrect email or password." message is displayed 
	
	Examples: 
		| email  	     |  password		   |
		| SC@gmail.com |  Selenium@1234      | 
