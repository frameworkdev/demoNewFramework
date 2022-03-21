@PartyPoker
Feature: VirtualSports PartyPoker features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "partypoker" site version url
    Then verify site version
