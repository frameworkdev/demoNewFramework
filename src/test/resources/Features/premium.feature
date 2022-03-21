@premium
Feature: VirtualSports premium features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "premium" site version url
    Then verify site version
