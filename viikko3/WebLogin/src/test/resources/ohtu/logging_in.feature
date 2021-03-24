Feature: As a registered user can log in with valid username/password-combination

    Scenario: user can login with correct password
        Given login is selected
        When  correct username "jukka" and password "akkuj" are given
        Then  user is logged in

    Scenario: user can not login with incorrect password
        Given login is selected
        When  correct username "jukka" and incorrect password "wrong" are given
        Then  user is not logged in and error message for giving credentials is given

    Scenario: nonexistent user can not login to 
        Given login is selected
        When  nonexistent username "nonexistent" and password "akkuj" are given
        Then  user is not logged in and error message for giving credentials is given

    Scenario: user can login with successfully generated account
        Given user with username "lea" with password "salainen1" is successfully created
        And   login is selected
        When  correct username "lea" and correct password "salainen1" for generated account are given
        Then  user is logged in

    Scenario: user cannot login with account that is not successfully created
        Given user with username "aa" and password "bad" is tried to be created
        And   login is selected
        When  username "aa" and the password "bad" of a failed creation attempt are given
        Then  user is not logged in and two error messages are given 