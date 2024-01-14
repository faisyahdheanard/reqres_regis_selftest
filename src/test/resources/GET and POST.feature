@api
Feature: Test Automation for GET and POST

  @api1
  Scenario: Get User from page 3
    Given User hits the API to Get List User from page 3
    Then validate the status code is 200
    Then validate the response body of "Get List Users"
    Then validate the response JSON with JSONSchema "get_list_users.json"

  @api2
  Scenario: Get Single User with ID 10
    Given User hits the API to Get Single User with user id 10
    Then validate the status code is 200
    Then validate the response body of "Get Single Users"
    Then validate the response JSON with JSONSchema "get_single_user.json"

  # REGISTER SUCCESSFUL
  @api3
  Scenario: User Register with valid email and password
    Given User hits the API to Post User Registration with "valid" data
    Then validate the status code is 200
    Then validate the response body of "Post User Register"
    Then validate the response JSON with JSONSchema "post_register_successful.json"

  # REGISTER FAILED
  @api4
  Scenario Outline: User Register with various negative scenario
    #1. WITHOUT PASSWORD
    #2. WITHOUT EMAIL
    #3. WITH UNREGISTERED/WRONG EMAIL
    #4. WITHOUT USERNAME AND PASSWORD (BLANK)
    Given User hits the API to Post User Registration with "<invalidDataType>" data
    Then validate the status code is 400
    Then validate the response body should contain an error message said "<errorMessages>"
    Then validate the response JSON with JSONSchema "post_register_Unsuccessful.json"

    Examples:
      | invalidDataType    | errorMessages                                 |
      | no password        | Missing password                              |
      | no email           | Missing email or username                     |
      | unregistered email | Note: Only defined users succeed registration |
      | blank              | Missing email or username                     |


