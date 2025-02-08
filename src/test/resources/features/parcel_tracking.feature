Feature: Parcel tracking functionality

  Scenario Outline: Tracking a parcel by its number and verifying its status
    Given user navigates to the InPost parcel tracking page
    When user enters the parcel number "<parcelNumber>"
    And user clicks on find button
    Then the status of the parcel should be "<expectedStatus>"

    Examples:
      | parcelNumber             | expectedStatus       |
      | 520113014230722029585646 | Doręczona            |
      | 520107010449991105638120 | Wydana do doręczenia |
      | 523000016696115042036670 | Anulowano etykietę   |
      | 520000011395200025754311 | Doreczona            |