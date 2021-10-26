Feature: Create User Feature

  @createUser
  Scenario Outline: Validate the user is able to create new users
    Given the api is successfully running
      |api/users|
    When the user creates new Users with "<user>" and "<job>"
    Then Validate that the response is valid and new user with "<user>" and "<job>" is created succesfully
 
  Examples:
   |user |job|
   |Jack |Captain|
   |John |Sailor|
   |Mark |MasterChief|
   |James|Architect|