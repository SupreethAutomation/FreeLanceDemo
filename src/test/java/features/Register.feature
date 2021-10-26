Feature: Register Feature

  @register
  Scenario Outline: Validate the user is able to successfully register and id and token are generated
    Given the api is successfully running
      |api/register|
    When user registers with the email "<email>" and password "<password>"
    Then Validate that the response is valid and token "<token>" is generated succesfully
    And verify the employee id "<id>"
    But Validate that the response is Invalid and proper error message "<msg>" is generated succesfully
      Examples:
      |email|password|token|id|msg|
      |eve.holt@reqres.in|pistol|QpwL5tke4Pnpja7X4|4||
      |michael.lawson@reqres.in|power|QpwL5tke4Pnpja7X7|7||
      |lindsay.ferguson@reqres.in||||Missing password|