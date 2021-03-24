Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given  command new user is selected
        When   a valid username "liisa" and valid password "salainen1" and matching password confirmation are entered
        Then   a new user is created

    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  a too short username "li" and valid password "salainen1" and matching password confirmation are entered
        Then  user is not created and error message for too short username is reported

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When  a valid username "matti" and too short password "hei" are given
        Then  user is not created and error for too short password is reported

    Scenario: creation fails when password and password confirmation do not match
        Given command new user is selected
        When  a valid username "Maija" and valid password "testing1" and incorrect conformation "testaus1" are entered
        Then  user is not created and error for incompatible password and password confirmation is reported
