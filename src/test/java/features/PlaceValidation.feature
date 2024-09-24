Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"


    Examples:
      | name | language | address          |
      |Naviya|English   |World cross center|
      |Vishnu|Spanish   |Jira cross center |


    @DeletePlace @Regression
    Scenario: Verify if delete place is work
      Given Deleteplace Payload
      When user calls "DeletePlaceAPI" with "POST" http request
      Then the API call is success with status code 200
      And "status" in response body is "OK"
