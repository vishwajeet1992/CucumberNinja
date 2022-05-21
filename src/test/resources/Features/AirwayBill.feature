Feature: Open Dashboard and print Airway Bill

  Scenario: User Prints airway Bill
    Given Given Shipper opened the Ninja Dashboard login page
    Then Shipper navigates to order details of first order
    When User print airway bill from Orders page
    Then User verify tracking Id in pdf