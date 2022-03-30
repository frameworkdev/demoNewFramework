@BwinCom @VirtualSports
Feature: VirtualSports bwin features

  @siteVersion
  Scenario: Verifying Site Version
    Given launch browser
    And enter "bwin" site version url
    Then verify site version

  @healthcheck
  Scenario: Verifying health check
    Given launch browser
    And enter "bwin" health url
    Then verify color of Sitecore service
    Then verify color of Content Templates