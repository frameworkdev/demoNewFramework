@partycasino
Feature: VirtualSports partycasino features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "partycasino" site version url
    Then verify site version
