Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "testing" and password "g1ntsett" are given
        Then  system will respond with "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  username "pekka" and password "akkeptest8" are entered
        Then  system will respond with "new user not registered"