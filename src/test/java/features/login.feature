Feature: Login Feature

  @login
  Scenario Outline:Outline Validate the user is not able to created successfully
    Given the api is successfully running
      |api/login|
    When the user authenticates with the credentials "<email>" and "<password>"
    |login.json|
    Then Validate that the response is valid and token "<token>" is generated succesfully
    But Validate that the response is Invalid and proper error message "<msg>" is generated succesfully 
    Examples:
    |email|password|token|msg|
    |eve.holt@reqres.in|pistol|QpwL5tke4Pnpja7X4||
    |eve.holt@reqres.in|||Missing password|
    ||pistol||Missing email or username|
 