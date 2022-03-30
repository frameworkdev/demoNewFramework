@SbCom @VirtualSports
Feature: VirtualSports sportingbet features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "sportingbet" site version url
    Then verify site version

  @healthcheck
  Scenario: Verifying health check
    Given launch browser
    And enter "sportingbet" health url
    Then verify color of Sitecore service
    Then verify color of Content Templates
