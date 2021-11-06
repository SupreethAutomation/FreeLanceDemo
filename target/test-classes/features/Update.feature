Feature: Update User Feature

  @updateUser
  Scenario Outline: Validate the admin is able to update the user details succesfully
    Given the api is successfully running
      | api/users |
    When the admin updates the user "<employeeId>" , "<name>" and "<job>" details
    |CreateUser.json|
    Then Validate that the response is valid and "<name>" and "<job>" details are updated succesfully
Examples:
|employeeId|name|job|
| 2 | Jack | Captain |
| 8 | Jack1 | Captain1 |