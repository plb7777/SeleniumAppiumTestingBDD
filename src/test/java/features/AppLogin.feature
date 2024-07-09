Feature: App Login

  Scenario: App login
    Given init mobile app
    When login with valid username and password
    Then check for home page
    
