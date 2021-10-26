Feature: Search User Feature

  @searchUser
  Scenario Outline:. Validate the user is perfrom search operation and validate the response
    Given the api is successfully running
      |api/users|
    When the user searches for the particular user as parameter "<employeeID>"
    Then Validate that the response is valid and "<employeeID>" and "<listofDetails>" is searched successfully
 		Examples:
 	  |employeeID|listofDetails|
      |2|janet.weaver@reqres.in$Janet$Weaver$https://reqres.in/img/faces/2-image.jpg$https://reqres.in/#support-heading$To keep ReqRes free, contributions towards server costs are appreciated!|
      |8|lindsay.ferguson@reqres.in$Lindsay$Ferguson$https://reqres.in/img/faces/8-image.jpg$https://reqres.in/#support-heading$To keep ReqRes free, contributions towards server costs are appreciated!|
      |9|tobias.funke@reqres.in$Tobias$Funke$https://reqres.in/img/faces/9-image.jpg$https://reqres.in/#support-heading$To keep ReqRes free, contributions towards server costs are appreciated!|
      |18|tobias.funke@reqres.in$Tobias$Funke$https://reqres.in/img/faces/9-image.jpg$https://reqres.in/#support-heading$To keep ReqRes free, contributions towards server costs are appreciated!|
     
 