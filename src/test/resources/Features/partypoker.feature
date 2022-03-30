@PartyPoker @VirtualSports
Feature: VirtualSports PartyPoker features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "partypoker" site version url
    Then verify site version

  @healthcheck
  Scenario: Verifying health check
    Given launch browser
    And enter "partypoker" health url
    Then verify color of Sitecore service
    Then verify color of Content Templates
