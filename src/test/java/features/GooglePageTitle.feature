
Feature: Google Homepage Title

  Scenario: Google title and search query
    Given init browser
    When browse to url
    Then get homepage title
    And search query in google

