@sportingbet.co.za
Feature: VirtualSports sportingbet.co.za features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "sportingbet.co.za" site version url
    Then verify site version

  @healthcheck
  Scenario: Verifying health check
    Given enter  "sportingbet.co.za" health url
    Then verify color of Sitecore service
