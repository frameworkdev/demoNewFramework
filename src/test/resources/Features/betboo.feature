@betboo
Feature: VirtualSports betboo features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "br.betboo" site version url
    Then verify site version
