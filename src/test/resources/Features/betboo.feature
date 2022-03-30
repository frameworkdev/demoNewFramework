@betboo @VirtualSports
Feature: VirtualSports betboo features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "br.betboo" site version url
    Then verify site version

  @healthcheck
  Scenario: Verifying health check
    Given launch browser
    And enter "br.betboo" health url
    Then verify color of Sitecore service
    Then verify color of Content Templates
