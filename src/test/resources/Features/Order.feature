Feature: Creating order

  Scenario: Shipper authenticate himself and place the order
    Given The shipper is registered user in Ninja Van
    When Shipper verifies authentication
    And Shipper gets access token for creating order
    Then Shipper selects 'standard' service for 'parcel' with following details
      | Name                   | PhoneNumber  | Email         | Address1            | Address2 | Country | Postcode |
      | Ninja Sender Z64441290 | 082188889999 | ninja@test.co | 903 Toa Payoh North |          | SG      | 511200   |
    And Shipper ships to following address
      | Name        | PhoneNumber  | Email         | Address1            | Address2 | Country | Postcode |
      | Recipient X | 082188889999 | mickyself.com | 999 Toa Payoh North |          | SG      | 318993   |
    Then Shipper places orders with following details on '2022-02-09'
      | Name          | PhoneNumber  | Email                     | Address1            | Address2 | Country | Postcode | PickupStartTime | PickupEndTime | PickupTimeZone | DeliveryStartTime | DeliveryEndTime | DeliveryTimeZone |
      | reservation-2 | 082188881592 | reservation-1@ninjavan.co | 903 Toa Payoh North |          | SG      | 511200   | 12:00           | 15:00         | Asia/Singapore | 09:00             | 22:00           | Asia/Singapore   |