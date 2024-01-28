Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlace API
    Given  Add place Payload with "<name>" "<address>" "<language>" "<phoneNumber>"
    When  user calls "AddPlaceAPI" with "Post" http request
    Then  the API call is suceess with status code  200
    And   "status" in response body is "OK"
    And   "scope" in response body is "APP"
    And   verify place_id created map to "<name>" using "getPlaceAPI"

    Examples:
      | name                 | address                     | language  | phoneNumber     |
      | Frontline house      | 29, side layout, cohen 09   | French-IN | +33 07780133042 |
      #| Bhakti Vedanta Manor | 25, Dharma Marg,Wartford,UK | English   | +44 07780145674 |

    @DeletePlace @Regression
    Scenario: Verify if Delete Place functionlaity is working fine or not
      Given DeletePlace Payload
      When  user calls "deletePlaceAPI" with "Post" http request
      Then  the API call is suceess with status code  200
      And   "status" in response body is "OK"
