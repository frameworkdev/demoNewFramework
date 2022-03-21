@BwinCom
Feature: VirtualSports bwin features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "bwin" site version url
    Then verify site version
