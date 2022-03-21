@gamebookers
Feature: VirtualSports gamebookers features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "gamebookers" site version url
    Then verify site version
