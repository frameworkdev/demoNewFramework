@SbCom
Feature: VirtualSports sportingbet features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "sportingbet" site version url
    Then verify site version
