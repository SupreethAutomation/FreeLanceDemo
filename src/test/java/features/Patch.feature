 Feature: Patch user data
 
 @patchUser
  Scenario Outline:. Validate the admin is able to patch the user details succesfully
    Given the api is successfully running
      | api/users |
    When the admin patches the user "<employeeId>" , "<name>" and "<job>" details
    Then Validate that the response is valid and "<name>" and "<job>" details are updated succesfully
Examples:
|employeeId|name|job|
| 2 | Jack | Captain |
| 8 | Jack1 | Captain1 |