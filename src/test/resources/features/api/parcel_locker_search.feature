Feature: Search for Parcel Lockers for provided city/several cities

  @API
  Scenario Outline: Search for Parcel Lockers for provided city/several cities and save details to the file
    Given user sends a request to fetch Parcel Lockers for "<city>"
    Then the response should contain valid Parcel Lockers data
    And the data is saved to "parcellockers.<city>.json" file

    Examples:
      | city       |
      | Warszawa   |
      | Kraków     |
      | Barcelona  |
      | NON_EXISTS |
